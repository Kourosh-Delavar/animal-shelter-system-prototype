package com.example.library.forms;

import com.example.library.Animals;
import com.example.library.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimalAdd extends JFrame{
    private JPanel MainPannel;
    private JPanel header;
    private JPanel AddAnimalMenu;
    private JPanel firstRow;
    private JPanel secondRow;
    private JPanel thirdRow;
    private PlaceholderTextField txtBoxShelterId;
    private PlaceholderTextField txtBoxAge;
    private PlaceholderTextField txtBoxBreed;
    private PlaceholderTextField txtBoxAnimalName;
    private PlaceholderTextField txtBoxMicrochipNmbr;
    private PlaceholderTextField txtBoxColorOrMarking;
    private JComboBox cmbBoxSpecies;
    private JComboBox cmbBoxSex;
    private JComboBox cmbBoxCity;
    private JComboBox cmbBoxIntakeReason;
    private JButton btnBack;
    private JButton btnSave;
    private JTextArea medicalNote;
    private JTextArea generalNote;
    private JTextField initLocation;
    private JTextField ownerName;
    private JTextField ownerPhoneNmbr;
    private JTextArea initHealthCheck;
    private JButton checkButton;
    private JLabel checkMessage;

    public AnimalAdd(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("AnimalAdd");

        loadValues();

        // Save button
        try {
            btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (saveAnimal()) {
                    System.out.println("Animal saved.");
                    AnimalsList animalsList = new AnimalsList();
                    animalsList.doShow();
                    SwingUtilities.getWindowAncestor(btnSave).dispose();
                }
            }
        });
        } catch (Exception e) {
            System.out.println("Error submitting new animal: " + e.getMessage());
        }

        // Check PetBase button
        try {
            checkButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    boolean checkResult = checkPetBase(txtBoxMicrochipNmbr.getText());
                    if (checkResult) {
                        checkMessage.setText("Checked: Mirco-chip number is registered in the PetBase");
                        checkMessage.setForeground(Color.GREEN);
                    } else {
                        checkMessage.setText("Checked: Micro-chip number is NOT registered in the PetBase");
                        checkMessage.setForeground(Color.RED);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }

        // Back button
        try {
            btnBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    AnimalsList animalsList = new AnimalsList();
                    animalsList.doShow();
                    SwingUtilities.getWindowAncestor(btnBack).dispose();
                }
            });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }

    }

    private String validateFields() {
        StringBuilder errors = new StringBuilder();

        try {
            int id = Integer.parseInt(txtBoxShelterId.getText());
            if (id <= 0) errors.append("- Shelter ID must be greater than 0.\n");
        } catch (NumberFormatException e) {
            errors.append("- Shelter ID must be a valid number.\n");
        }

        if (txtBoxAnimalName.getText().trim().isEmpty()) {
            errors.append("- Animal Name is required.\n");
        }

        try {
            int age = Integer.parseInt(txtBoxAge.getText());
            if (age < 0) errors.append("- Age cannot be negative.\n");
        } catch (NumberFormatException e) {
            errors.append("- Age must be a valid number.\n");
        }

        if (txtBoxMicrochipNmbr.getText().trim().isEmpty()) {
            errors.append("- Micro-chip Number is required.\n");
        }

        return errors.toString();
    }

    public boolean checkPetBase(String mircoChipNmbr) {
        return mircoChipNmbr.equalsIgnoreCase("test");
    }

    public void doShow(){
        pack();
        setSize(1280,720);
        setContentPane(MainPannel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        txtBoxShelterId = new PlaceholderTextField("Shelter ID");
        txtBoxAnimalName = new PlaceholderTextField("Animal Name (If Known)");
        txtBoxBreed = new PlaceholderTextField("Breed...");
        txtBoxAge = new PlaceholderTextField("Age...");
        txtBoxMicrochipNmbr = new PlaceholderTextField("Micro-chip Number...");
        txtBoxColorOrMarking = new PlaceholderTextField("Color/Marking");
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

    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean saveAnimal() {
        String errorLog = validateFields();

        if (!errorLog.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "The following errors were found:\n" + errorLog,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Animals animal = new Animals();
        animal.setShelterId(parseIntSafe(txtBoxShelterId.getText()));
        animal.setName(txtBoxAnimalName.getText());
        animal.setBreed(txtBoxBreed.getText());
        animal.setAge(parseIntSafe(txtBoxAge.getText()));
        animal.setMicroChipNmbr(txtBoxMicrochipNmbr.getText());
        animal.setColorAndOrMarkings(txtBoxColorOrMarking.getText());
        animal.setInitLocation(initLocation.getText());
        animal.setOwnerName(ownerName.getText());
        animal.setOwnerPhoneNmbr(ownerPhoneNmbr.getText());
        animal.setMedicalNote(medicalNote.getText());
        animal.setGeneralNote(generalNote.getText());

        if (cmbBoxSpecies.getSelectedItem() != null)
            animal.setSpecies(cmbBoxSpecies.getSelectedItem().toString());
        if (cmbBoxSex.getSelectedItem() != null)
            animal.setSex(cmbBoxSex.getSelectedItem().toString());
        if (cmbBoxCity.getSelectedItem() != null)
            animal.setCity(cmbBoxCity.getSelectedItem().toString());
        if (cmbBoxIntakeReason.getSelectedItem() != null)
            animal.setIntakeReason(cmbBoxIntakeReason.getSelectedItem().toString());

        animal.setInitHealthCheck(initHealthCheck.getText());

        Main.animalList.add(animal);
        return true;
    }
}
