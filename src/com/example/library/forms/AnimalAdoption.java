package com.example.library.forms;

import com.example.library.Adoption;
import com.example.library.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimalAdoption extends JFrame {
    private JPanel Main1;
    private JPanel header;
    private JButton btnBack;
    private JButton btnSave;
    private JPanel AdoptionMenu;
    private JPanel firstRow;
    private JPanel secondRow;
    private JPanel thirdRow;
    private JTextField address;
    private JTextField cityAndPostalCode;
    private JTextField phoneNumber;
    private JTextField emailAddress;
    private JTextField dateOfBirth;
    private JComboBox cmbHomeType;
    private JComboBox cmbOwnership;
    private JComboBox cmbIsPetAllowed;
    private JComboBox cmbYardExists;
    private JTextField childrenNmbr;
    private JTextField childrenAges;
    private JComboBox cmbCommonAgreement;
    private JComboBox cmbAlreadyHavePet;
    private JComboBox cmbIsVaccinated;
    private JComboBox cmbIsMicroChipped;
    private JComboBox cmbThisTypeOfPetExp;
    private JTextField hoursPerDayIsSomeoneHome;
    private JTextField caretakerDuringVacation;
    private JTextField animalName;
    private JTextField shelterId;
    private JTextField adoptionReason;
    private JCheckBox boxDeclare;
    private JTextField adoptionFormDate;
    private JTextField adultsNmbr;
    private JTextField petType;
    private JTextField adopterName;
    private JCheckBox boxAgree;
    private JComboBox cmbFenceExist;

    public AnimalAdoption() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("AnimalAdoption");

        initCmbValues();

        try {
            btnSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    if (validateFields()) {
                        saveAdoptionForm();
                        System.out.println("Adoption form saved.");
                        AdoptionList backAdopList = new AdoptionList();
                        backAdopList.doShow();
                        SwingUtilities.getWindowAncestor(btnSave).dispose();
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Error saving animal adoption form: " + e.getMessage());
        }

        // Back button
        try {
            btnBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    AdoptionList backAdopList = new AdoptionList();
                    backAdopList.doShow();
                    SwingUtilities.getWindowAncestor(btnBack).dispose();
                }
            });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }
    }

    private boolean validateFields() {
        StringBuilder errorLog = new StringBuilder();

        // String checks
        if (adopterName.getText().trim().isEmpty()) errorLog.append("- Adopter name is required.\n");
        if (address.getText().trim().isEmpty()) errorLog.append("- Address is required.\n");
        if (emailAddress.getText().trim().isEmpty() || !emailAddress.getText().contains("@")) {
            errorLog.append("- A valid email address is required.\n");
        }

        // Integer checks
        try {
            int adults = Integer.parseInt(adultsNmbr.getText());
            if (adults < 1) errorLog.append("- There must be at least 1 adult in the household.\n");
        } catch (NumberFormatException e) {
            errorLog.append("- Adults number must be a valid whole number.\n");
        }

        try {
            int sId = Integer.parseInt(shelterId.getText());
            if (sId <= 0) errorLog.append("- Shelter ID must be a positive number.\n");
        } catch (NumberFormatException e) {
            errorLog.append("- Shelter ID must be a valid whole number.\n");
        }

        // Double check
        try {
            double hours = Double.parseDouble(hoursPerDayIsSomeoneHome.getText());
            if (hours < 0 || hours > 24) errorLog.append("- Hours per day must be between 0 and 24.\n");
        } catch (NumberFormatException e) {
            errorLog.append("- Hours per day must be a valid decimal number.\n");
        }

        // Required acknowledgments
        if (!boxDeclare.isSelected() || !boxAgree.isSelected()) {
            errorLog.append("- You must check both declaration boxes to proceed.\n");
        }

        if (errorLog.length() > 0) {
            JOptionPane.showMessageDialog(this,
                    "Please fix the following errors:\n" + errorLog.toString(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void doShow(){
        pack();
        setSize(1280,720);
        setContentPane(Main1);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initCmbValues() {
        // ... existing init logic ...
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

    private void saveAdoptionForm() {
        try {
            Adoption adoption = new Adoption();

            adoption.setName(adopterName.getText());
            adoption.setAddress(address.getText());
            adoption.setCityAndPostalCode(cityAndPostalCode.getText());
            adoption.setPhoneNmbr(phoneNumber.getText());
            adoption.setEmailAddr(emailAddress.getText());
            adoption.setDateOfBirth(dateOfBirth.getText());
            adoption.setPetType(petType.getText());
            adoption.setCaretakerDuringVacation(caretakerDuringVacation.getText());
            adoption.setChosenAnimalName(animalName.getText());
            adoption.setAdoptionReason(adoptionReason.getText());
            adoption.setAdoptionFormDate(adoptionFormDate.getText());

            adoption.setTypeOfHome(cmbHomeType.getSelectedItem().toString());
            adoption.setHomeOwnership(cmbOwnership.getSelectedItem().toString());
            adoption.setPetAllowed(cmbIsPetAllowed.getSelectedItem().toString());
            adoption.setYardExist(cmbYardExists.getSelectedItem().toString());
            adoption.setFencesExist(cmbFenceExist.getSelectedItem().toString());
            adoption.setCommonAgreement(cmbCommonAgreement.getSelectedItem().toString());
            adoption.setPetAlreadyExist(cmbAlreadyHavePet.getSelectedItem().toString());
            adoption.setVaccinated(cmbIsVaccinated.getSelectedItem().toString());
            adoption.setMicroChipped(cmbIsMicroChipped.getSelectedItem().toString());
            adoption.setThisPetTypeExp(cmbThisTypeOfPetExp.getSelectedItem().toString());

            adoption.setAdultsNmbr(Integer.parseInt(adultsNmbr.getText().trim()));
            adoption.setChildrenNmbr(Integer.parseInt(childrenNmbr.getText().trim()));
            adoption.setHoursPerDaySomeoneIsHome(Double.parseDouble(hoursPerDayIsSomeoneHome.getText().trim()));
            adoption.setChosenAnimalShelterId(Integer.parseInt(shelterId.getText().trim()));

            adoption.setAdoptionInfoAck(boxDeclare.isSelected() ? "Yes" : "No");
            adoption.setHomeVisitAck(boxAgree.isSelected() ? "Yes" : "No");

            Main.adoptionsList.add(adoption);

        } catch (Exception e) {
            System.out.println("Critical Error during save: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Internal Save Error: " + e.getMessage());
        }
    }
}