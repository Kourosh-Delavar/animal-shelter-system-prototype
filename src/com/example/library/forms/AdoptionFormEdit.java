package com.example.library.forms;

import com.example.library.Adoption;
import com.example.library.Main;

import javax.naming.InitialContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdoptionFormEdit extends JFrame {

    private JPanel formEditMainPage;
    private JPanel header;
    private JButton btnBack;
    private JButton btnCancelEditing;
    private JPanel AdoptionMenu;
    private JPanel firstRow;
    private JTextField adopterName;
    private JTextField address;
    private JTextField cityAndPostalCode;
    private JTextField phoneNumber;
    private JTextField emailAddress;
    private JTextField dateOfBirth;
    private JComboBox cmbHomeType;
    private JComboBox cmbOwnership;
    private JComboBox cmbIsPetAllowed;
    private JComboBox cmbYardExists;
    private JComboBox cmbFenceExist;
    private JTextField adultsNmbr;
    private JTextField childrenNmbr;
    private JTextField childrenAges;
    private JComboBox cmbCommonAgreement;
    private JPanel secondRow;
    private JTextField petType;
    private JComboBox cmbAlreadyHavePet;
    private JComboBox cmbIsMicroChipped;
    private JComboBox cmbIsVaccinated;
    private JComboBox cmbThisTypeOfPetExp;
    private JTextField hoursPerDayIsSomeoneHome;
    private JTextField caretakerDuringVacation;
    private JPanel thirdRow;
    private JTextField animalName;
    private JTextField shelterId;
    private JTextField adoptionReason;
    private JCheckBox boxDeclare;
    private JCheckBox boxAgree;
    private JTextField adoptionFormDate;
    private JButton btnEdit;

    private Adoption bufferedSelectedAdop;

    public AdoptionFormEdit() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("AdoptionFormEdit");

        try {
            btnBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    AdoptionList adopList = new AdoptionList();
                    adopList.doShow();
                    dispose();
                }
            });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }

        try {
            btnEdit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    btnCancelEditing.setVisible(true);
                    btnEdit.setVisible(false);
                    enableEditing();
                }
            });
        } catch (Exception e) {
            System.out.println("Error enable editing: " + e.getMessage());
        }

        try {
            btnCancelEditing.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    // Use the validation logic below before saving
                    if (validateFields()) {
                        btnEdit.setBackground(Color.GREEN);
                        btnEdit.setForeground(Color.BLACK);
                        btnCancelEditing.setVisible(false);
                        btnEdit.setVisible(true);
                        disableEditing();
                        saveModifiedData(bufferedSelectedAdop, getModifiedAdop());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Error disable editing: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        StringBuilder errorLog = new StringBuilder();

        if (adopterName.getText().trim().isEmpty()) errorLog.append("- Adopter name cannot be empty.\n");
        if (address.getText().trim().isEmpty()) errorLog.append("- Address is required.\n");
        if (emailAddress.getText().trim().isEmpty() || !emailAddress.getText().contains("@")) {
            errorLog.append("- Please enter a valid email address.\n");
        }

        try {
            int adults = Integer.parseInt(adultsNmbr.getText());
            if (adults < 1) errorLog.append("- There must be at least 1 adult in the household.\n");
        } catch (NumberFormatException e) {
            errorLog.append("- Number of adults must be a valid whole number.\n");
        }

        try {
            int children = Integer.parseInt(childrenNmbr.getText());
            if (children < 0) errorLog.append("- Children number cannot be negative.\n");
        } catch (NumberFormatException e) {
            errorLog.append("- Number of children must be a valid whole number.\n");
        }

        try {
            double hours = Double.parseDouble(hoursPerDayIsSomeoneHome.getText());
            if (hours < 0 || hours > 24) {
                errorLog.append("- Hours per day must be between 0 and 24.\n");
            }
        } catch (NumberFormatException e) {
            errorLog.append("- Hours per day someone is home must be a valid decimal number.\n");
        }

        try {
            int sid = Integer.parseInt(shelterId.getText());
            if (sid <= 0) errorLog.append("- Chosen Animal Shelter ID must be greater than 0.\n");
        } catch (NumberFormatException e) {
            errorLog.append("- Shelter ID must be a valid whole number.\n");
        }

        if (errorLog.length() > 0) {
            JOptionPane.showMessageDialog(this,
                    "The following errors must be corrected before saving:\n\n" + errorLog.toString(),
                    "Invalid Information",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void doShow(Adoption selectedAdop) {
        setContentPane(formEditMainPage);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        bufferSelection(selectedAdop);
        btnCancelEditing.setVisible(false);
        loadData(selectedAdop);
        disableEditing();
        setVisible(true);
    }

    private void bufferSelection(Adoption selectedAdop) {
        bufferedSelectedAdop = selectedAdop;
    }

    private Adoption getModifiedAdop() {
        Adoption modifiedAdop = new Adoption();

        modifiedAdop.setName(adopterName.getText());
        modifiedAdop.setAddress(address.getText());
        modifiedAdop.setCityAndPostalCode(cityAndPostalCode.getText());
        modifiedAdop.setPhoneNmbr(phoneNumber.getText());
        modifiedAdop.setEmailAddr(emailAddress.getText());
        modifiedAdop.setDateOfBirth(dateOfBirth.getText());
        modifiedAdop.setTypeOfHome(cmbHomeType.getSelectedItem().toString());
        modifiedAdop.setHomeOwnership(cmbOwnership.getSelectedItem().toString());
        modifiedAdop.setPetAllowed(cmbIsPetAllowed.getSelectedItem().toString());
        modifiedAdop.setYardExist(cmbYardExists.getSelectedItem().toString());
        modifiedAdop.setFencesExist(cmbFenceExist.getSelectedItem().toString());
        modifiedAdop.setAdultsNmbr(Integer.parseInt(adultsNmbr.getText()));
        modifiedAdop.setChildrenNmbr(Integer.parseInt(childrenNmbr.getText()));
        modifiedAdop.setChildrenAges(childrenAges.getText());
        modifiedAdop.setCommonAgreement(cmbCommonAgreement.getSelectedItem().toString());
        modifiedAdop.setPetAlreadyExist(cmbAlreadyHavePet.getSelectedItem().toString());
        modifiedAdop.setPetType(petType.getText());
        modifiedAdop.setVaccinated(cmbIsVaccinated.getSelectedItem().toString());
        modifiedAdop.setMicroChipped(cmbIsMicroChipped.getSelectedItem().toString());
        modifiedAdop.setThisPetTypeExp(cmbThisTypeOfPetExp.getSelectedItem().toString());
        modifiedAdop.setHoursPerDaySomeoneIsHome(Double.parseDouble(hoursPerDayIsSomeoneHome.getText()));
        modifiedAdop.setCaretakerDuringVacation(caretakerDuringVacation.getText());
        modifiedAdop.setChosenAnimalName(animalName.getText());
        modifiedAdop.setChosenAnimalShelterId(Integer.parseInt(shelterId.getText()));
        modifiedAdop.setAdoptionReason(adoptionReason.getText());

        modifiedAdop.setAdoptionInfoAck(bufferedSelectedAdop.isAdoptionInfoAck());
        modifiedAdop.setHomeVisitAck(bufferedSelectedAdop.isHomeVisitAck());
        modifiedAdop.setAdoptionFormDate(bufferedSelectedAdop.getAdoptionFormDate());

        return modifiedAdop;
    }

    private void saveModifiedData(Adoption selectedAdop, Adoption modifiedAdop) {
        try {
            for (int i = 0; i < Main.adoptionsList.size(); i++) {
                Adoption adopItem = Main.adoptionsList.get(i);
                if (adopItem.getChosenAnimalShelterId() == selectedAdop.getChosenAnimalShelterId()) {
                    Main.adoptionsList.set(i, modifiedAdop);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving modified data: " + e);
        }
    }

    private void loadData(Adoption selectedAdop) {
        adopterName.setText(selectedAdop.getName());
        address.setText(selectedAdop.getAddress());
        cityAndPostalCode.setText(selectedAdop.getCityAndPostalCode());
        phoneNumber.setText(selectedAdop.getPhoneNmbr());
        emailAddress.setText(selectedAdop.getEmailAddr());
        adultsNmbr.setText("" + selectedAdop.getAdultsNmbr());
        childrenNmbr.setText("" + selectedAdop.getChildrenNmbr());
        childrenAges.setText(selectedAdop.getChildrenAges());
        petType.setText(selectedAdop.getPetType());
        hoursPerDayIsSomeoneHome.setText("" + selectedAdop.getHoursPerDaySomeoneIsHome());
        caretakerDuringVacation.setText(selectedAdop.getCaretakerDuringVacation());
        animalName.setText(selectedAdop.getChosenAnimalName());
        shelterId.setText("" + selectedAdop.getChosenAnimalShelterId());
        adoptionReason.setText(selectedAdop.getAdoptionReason());
        adoptionFormDate.setText(selectedAdop.getAdoptionFormDate());

        initCmbValues();
        cmbHomeType.setSelectedItem(selectedAdop.getTypeOfHome());
        cmbOwnership.setSelectedItem(selectedAdop.getHomeOwnership());
        cmbIsPetAllowed.setSelectedItem(selectedAdop.isPetAllowed());
        cmbYardExists.setSelectedItem(selectedAdop.isYardExist());
        cmbFenceExist.setSelectedItem(selectedAdop.isFencesExist());
        cmbCommonAgreement.setSelectedItem(selectedAdop.isCommonAgreement());
        cmbAlreadyHavePet.setSelectedItem(selectedAdop.isPetAlreadyExist());
        cmbIsVaccinated.setSelectedItem(selectedAdop.isVaccinated());
        cmbIsMicroChipped.setSelectedItem(selectedAdop.isMicroChipped());
        cmbThisTypeOfPetExp.setSelectedItem(selectedAdop.isThisPetTypeExp());

        boxDeclare.setSelected(selectedAdop.isAdoptionInfoAck().equalsIgnoreCase("Yes"));
        boxAgree.setSelected(selectedAdop.isHomeVisitAck().equalsIgnoreCase("Yes"));
    }

    private void initCmbValues() {

        for (String homeTypeItem : Main.homeType) cmbHomeType.addItem(homeTypeItem);
        for (String ownershipItem : Main.houseOwnership) cmbOwnership.addItem(ownershipItem);
        for (String petAllowedItem : Main.isPetAllowed) cmbIsPetAllowed.addItem(petAllowedItem);
        for (String yardExistsItem : Main.yardExists) cmbYardExists.addItem(yardExistsItem);
        for (String fenceExistItem : Main.fenceExists) cmbFenceExist.addItem(fenceExistItem);
        for (String commonAgreementItem : Main.commonAgreement) cmbCommonAgreement.addItem(commonAgreementItem);
        for (String alreadyHavePetItem : Main.alreadyHavePet) cmbAlreadyHavePet.addItem(alreadyHavePetItem);
        for (String isVaccinatedItem : Main.isVaccinated) cmbIsVaccinated.addItem(isVaccinatedItem);
        for (String isMicroChippedItem : Main.isMiroChipped) cmbIsMicroChipped.addItem(isMicroChippedItem);
        for (String thisTypeOfPetExpItem : Main.thisTypeOfPetExp) cmbThisTypeOfPetExp.addItem(thisTypeOfPetExpItem);
    }

    private void enableEditing() {
        adopterName.setEditable(true);
        address.setEditable(true);
        cityAndPostalCode.setEditable(true);
        phoneNumber.setEditable(true);
        emailAddress.setEditable(true);
        cmbHomeType.setEnabled(true);
        cmbOwnership.setEnabled(true);
        cmbIsPetAllowed.setEnabled(true);
        cmbYardExists.setEnabled(true);
        cmbFenceExist.setEnabled(true);
        adultsNmbr.setEditable(true);
        childrenNmbr.setEditable(true);
        childrenAges.setEditable(true);
        cmbCommonAgreement.setEnabled(true);
        cmbAlreadyHavePet.setEnabled(true);
        petType.setEditable(true);
        cmbIsVaccinated.setEnabled(true);
        cmbIsMicroChipped.setEnabled(true);
        cmbThisTypeOfPetExp.setEnabled(true);
        hoursPerDayIsSomeoneHome.setEditable(true);
        caretakerDuringVacation.setEditable(true);
        animalName.setEditable(true);
        shelterId.setEditable(true);
        adoptionReason.setEditable(true);

        boxDeclare.setEnabled(false);
        boxAgree.setEnabled(false);
        adoptionFormDate.setEditable(false);
    }

    private void disableEditing() {
        Component[] components = {adopterName, address, cityAndPostalCode, phoneNumber, emailAddress, adultsNmbr, childrenNmbr, childrenAges, petType, hoursPerDayIsSomeoneHome, caretakerDuringVacation, animalName, shelterId, adoptionReason, adoptionFormDate};
        for (Component c : components) {
            if (c instanceof JTextField) ((JTextField) c).setEditable(false);
        }

        JComboBox[] combos = {cmbHomeType, cmbOwnership, cmbIsPetAllowed, cmbYardExists, cmbFenceExist, cmbCommonAgreement, cmbAlreadyHavePet, cmbIsVaccinated, cmbIsMicroChipped, cmbThisTypeOfPetExp};
        for (JComboBox cb : combos) {
            cb.setEnabled(false);
        }

        boxDeclare.setEnabled(false);
        boxAgree.setEnabled(false);
    }
}