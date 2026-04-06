package com.example.library.forms;

import com.example.library.Animals;
import com.example.library.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimalInfo extends JFrame {
    private JComboBox cmbBoxSpecies;
    private JComboBox cmbBoxCity;
    private JPanel MainPannel;
    private JPanel header;
    private JButton btnBack;
    private JButton btnCancelEditing;
    private JPanel AddAnimalMenu;
    private JPanel firstRow;
    private PlaceholderTextField txtBoxShelterId;
    private PlaceholderTextField txtBoxAge;
    private PlaceholderTextField txtBoxBreed;
    private PlaceholderTextField txtBoxAnimalName;
    private PlaceholderTextField txtBoxMicrochipNmbr;
    private PlaceholderTextField txtBoxColorOrMarking;
    private JComboBox cmbBoxSex;
    private JComboBox cmbBoxIntakeReason;
    private JTextField initLocation;
    private JTextField ownerName;
    private JTextField ownerPhoneNmbr;
    private JPanel secondRow;
    private JTextArea initHealthCheck;
    private JTextArea generalNote;
    private JPanel thirdRow;
    private JTextArea medicalNote;
    private JButton btnEditing;
    private JPanel Root;

    private Animals bufferedSelectedAnim;

    public AnimalInfo() {
        setTitle("Animal Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            btnEditing.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    btnCancelEditing.setVisible(true);
                    btnEditing.setVisible(false);
                    setEditableTrue();
                    loadValues();
                }
            });
        } catch (Exception e) {
            System.out.println("Error enabling editing: " + e.getMessage());
        }

        try {
            btnCancelEditing.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    // Reset UI appearance
                    btnEditing.setBackground(Color.GREEN);
                    btnEditing.setForeground(Color.BLACK);
                    btnCancelEditing.setVisible(false);
                    btnEditing.setVisible(true);

                    setEditableFalse();
                    saveModifiedData(bufferedSelectedAnim, getModifiedAnim());
                }
            });
        } catch (Exception e) {
            System.out.println("Error disabling editing: " + e.getMessage());
        }

        try {
            btnBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    AnimalsList animList = new AnimalsList();
                    animList.doShow();
                    SwingUtilities.getWindowAncestor(btnBack).dispose();
                }
            });
        } catch (Exception e) {
            System.out.println("Error on back button: " + e.getMessage());
        }
    }

    public void doShow(Animals anim) {
        setContentPane(Root);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        bufferSelection(anim);
        loadAnimalData(anim);
        setEditableFalse();
        btnCancelEditing.setVisible(false);

        setVisible(true);
    }


    private void bufferSelection(Animals selectedAnim) {
        this.bufferedSelectedAnim = selectedAnim;
    }


    private void loadAnimalData(Animals currentAnimal) {
        txtBoxAnimalName.setText(currentAnimal.getName());
        txtBoxBreed.setText(currentAnimal.getBreed());
        txtBoxAge.setText(String.valueOf(currentAnimal.getAge()));
        txtBoxShelterId.setText(String.valueOf(currentAnimal.getShelterId()));
        txtBoxMicrochipNmbr.setText(currentAnimal.getMicroChipNmbr());
        txtBoxColorOrMarking.setText(currentAnimal.getColorAndOrMarkings());

        cmbBoxSex.setSelectedItem(currentAnimal.getSex());
        cmbBoxSpecies.setSelectedItem(currentAnimal.getSpecies());
        cmbBoxCity.setSelectedItem(currentAnimal.getCity());
        cmbBoxIntakeReason.setSelectedItem(currentAnimal.getIntakeReason());

        initLocation.setText(currentAnimal.getInitLocation());
        ownerName.setText(currentAnimal.getOwnerName());
        ownerPhoneNmbr.setText(currentAnimal.getOwnerPhoneNmbr());
        initHealthCheck.setText(currentAnimal.getInitHealthCheck());
        generalNote.setText(currentAnimal.getGeneralNote());
        medicalNote.setText(currentAnimal.getMedicalNote());
    }


    private Animals getModifiedAnim() {
        Animals modified = new Animals();

        modified.setName(txtBoxAnimalName.getText());
        modified.setBreed(txtBoxBreed.getText());

        try {
            if (!txtBoxAge.getText().isEmpty()) {
                modified.setAge(Integer.parseInt(txtBoxAge.getText()));
            }
            modified.setShelterId(Integer.parseInt(txtBoxShelterId.getText()));
        } catch (NumberFormatException e) {
            System.out.println("Numeric parsing error: " + e.getMessage());
        }

        modified.setMicroChipNmbr(txtBoxMicrochipNmbr.getText());
        modified.setColorAndOrMarkings(txtBoxColorOrMarking.getText());

        if (cmbBoxSex.getSelectedItem() != null) modified.setSex(cmbBoxSex.getSelectedItem().toString());
        if (cmbBoxSpecies.getSelectedItem() != null) modified.setSpecies(cmbBoxSpecies.getSelectedItem().toString());
        if (cmbBoxCity.getSelectedItem() != null) modified.setCity(cmbBoxCity.getSelectedItem().toString());
        if (cmbBoxIntakeReason.getSelectedItem() != null) modified.setIntakeReason(cmbBoxIntakeReason.getSelectedItem().toString());

        modified.setInitLocation(initLocation.getText());
        modified.setOwnerName(ownerName.getText());
        modified.setOwnerPhoneNmbr(ownerPhoneNmbr.getText());
        modified.setInitHealthCheck(initHealthCheck.getText());
        modified.setGeneralNote(generalNote.getText());
        modified.setMedicalNote(medicalNote.getText());

        return modified;
    }


    private void saveModifiedData(Animals selectedAnim, Animals modifiedAnim) {
        try {
            for (int i = 0; i < Main.animalList.size(); i++) {
                Animals animItem = Main.animalList.get(i);

                if (animItem.getShelterId() == selectedAnim.getShelterId()) {
                    Main.animalList.set(i, modifiedAnim);
                    System.out.println("Animal record updated successfully.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving modified data: " + e);
        }
    }

    private void loadValues(){
        for (String speciesItem : Main.species){
            cmbBoxSpecies.addItem(speciesItem);
        }

        for (String sexItem : Main.sex){
            cmbBoxSex.addItem(sexItem);
        }

        for (String cityItem : Main.city){
            cmbBoxCity.addItem(cityItem);
        }

        for (String intakeReasonItem : Main.intakeReason){
            cmbBoxIntakeReason.addItem(intakeReasonItem);
        }
    }

    private void setEditableTrue() {
        txtBoxShelterId.setEditable(true);
        txtBoxAge.setEditable(true);
        txtBoxBreed.setEditable(true);
        txtBoxAnimalName.setEditable(true);
        txtBoxMicrochipNmbr.setEditable(true);
        txtBoxColorOrMarking.setEditable(true);
        cmbBoxSex.setEnabled(true);
        cmbBoxSpecies.setEnabled(true);
        cmbBoxCity.setEnabled(true);
        cmbBoxIntakeReason.setEnabled(true);
        initLocation.setEditable(true);
        ownerName.setEditable(true);
        ownerPhoneNmbr.setEditable(true);
        initHealthCheck.setEditable(true);
        generalNote.setEditable(true);
        medicalNote.setEditable(true);
    }

    private void setEditableFalse() {
        txtBoxShelterId.setEditable(false);
        txtBoxAge.setEditable(false);
        txtBoxBreed.setEditable(false);
        txtBoxAnimalName.setEditable(false);
        txtBoxMicrochipNmbr.setEditable(false);
        txtBoxColorOrMarking.setEditable(false);
        cmbBoxSex.setEnabled(false);
        cmbBoxSpecies.setEnabled(false);
        cmbBoxCity.setEnabled(false);
        cmbBoxIntakeReason.setEnabled(false);
        initLocation.setEditable(false);
        ownerName.setEditable(false);
        ownerPhoneNmbr.setEditable(false);
        initHealthCheck.setEditable(false);
        generalNote.setEditable(false);
        medicalNote.setEditable(false);
    }

    private void createUIComponents() {
        txtBoxShelterId = new PlaceholderTextField("Shelter ID");
        txtBoxAnimalName = new PlaceholderTextField("Animal Name (If Known)");
        txtBoxBreed = new PlaceholderTextField("Breed...");
        txtBoxAge = new PlaceholderTextField("Age...");
        txtBoxMicrochipNmbr = new PlaceholderTextField("Micro-chip Number...");
        txtBoxColorOrMarking = new PlaceholderTextField("Color/Marking");
    }
}