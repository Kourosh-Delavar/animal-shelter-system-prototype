package com.example.library.forms;

import com.example.library.Adoption;
import com.example.library.Animals;
import com.example.library.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class AnimalsList extends JFrame {
    private JPanel panel1;
    private JButton btnBack;

    private JButton addAnimalButton;
    private JCheckBox deventerCheckBox;
    private JCheckBox zutphenCheckBox;
    private JCheckBox apeldoornCheckBox;
    private JCheckBox dogCheckBox;
    private JCheckBox catCheckBox;
    private JCheckBox rabbitCheckBox;
    private JCheckBox otherCheckBox;
    private JButton filterButton;
    private JTextField textField1;
    private JButton searchButton;
    private JList<Animals> AL;
    private JScrollPane scrollPane;


    public AnimalsList() {
        scrollPane.setViewportView(AL);

        addAnimalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AnimalAdd animalAdd = new AnimalAdd();
                animalAdd.doShow();
                SwingUtilities.getWindowAncestor(addAnimalButton).dispose();
            }
        });
        try {
            btnBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    dispose();
            }
        });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }
        try {
            searchButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    updateList();
                }
            });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }
        try {
            filterButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    updateList();
                }
            });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }

        AL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && !evt.isConsumed()) {
                    evt.consume();

                    int index = AL.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        Animals selectedAnimal = AL.getModel().getElementAt(index);

                        AnimalInfo animInfo = new AnimalInfo();
                        animInfo.doShow(selectedAnimal);

                        SwingUtilities.getWindowAncestor(AL).dispose();
                    }
                }
            }
        });
    }

    private void updateList() {
        DefaultListModel<Animals> model = new DefaultListModel<>();
        String searchText = textField1.getText().trim().toLowerCase();

        ArrayList<String> selectedCities = new ArrayList<>();
        if (deventerCheckBox.isSelected()) selectedCities.add("Deventer");
        if (zutphenCheckBox.isSelected()) selectedCities.add("Zutphen");
        if (apeldoornCheckBox.isSelected()) selectedCities.add("Apeldoorn");

        ArrayList<String> selectedSpecies = new ArrayList<>();
        if (dogCheckBox.isSelected()) selectedSpecies.add("Dog");
        if (catCheckBox.isSelected()) selectedSpecies.add("Cat");
        if (rabbitCheckBox.isSelected()) selectedSpecies.add("Rabbit");
        if (otherCheckBox.isSelected()) selectedSpecies.add("Other");

        for (Animals animal : Main.animalList) {
            boolean matchesSearch = true;
            boolean matchesCity = true;
            boolean matchesSpecies = true;

            if (!searchText.isEmpty()) {
                matchesSearch = animal.getName().toLowerCase().contains(searchText);
            }

            if (!selectedCities.isEmpty()) {
                matchesCity = selectedCities.contains(animal.getCity());
            }

            if (!selectedSpecies.isEmpty()) {
                matchesSpecies = selectedSpecies.contains(animal.getSpecies());
            }

            if (matchesSearch && matchesCity && matchesSpecies) {
                model.addElement(animal);
            }
        }

        AL.setFixedCellHeight(110);
        AL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        AL.setSelectionBackground(new Color(200, 220, 240));
        AL.setSelectionForeground(Color.BLACK);

        AL.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        AL.setModel(model);
        AL.setBackground(Color.LIGHT_GRAY);
        AL.setForeground(Color.BLACK);
    }


    public void doShow() {
        setContentPane(panel1);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        updateList();
        setVisible(true);
    }
}