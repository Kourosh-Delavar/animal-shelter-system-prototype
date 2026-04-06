package com.example.library.forms;

import com.example.library.Adoption;
import com.example.library.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdoptionList extends JFrame {
    private JPanel header;
    private JButton btnBack;
    private JPanel AdoptionMainPage;
    private JButton btnAddAdoption;
    private JList adoptionList;
    private JButton btnDelete;

    public AdoptionList() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Second Chance - Adoption Applications");

        try {
            btnBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    SwingUtilities.getWindowAncestor(btnBack).dispose();
                }
            });
        } catch (Exception e) {
            System.out.println("Back button errored: " + e.getMessage());
        }

        try {
            btnAddAdoption.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    AnimalAdoption adoptionForm = new AnimalAdoption();
                    adoptionForm.doShow();
                    SwingUtilities.getWindowAncestor(btnAddAdoption).dispose();
                }
            });
        } catch (Exception e) {
            System.out.println("Error opening adoption form page: " + e.getMessage());
        }

        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Adoption selectedAdoption = (Adoption) adoptionList.getSelectedValue();

                if (selectedAdoption == null) {
                    JOptionPane.showMessageDialog(null, "Please select an application to delete.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete the application for " + selectedAdoption.getName() + "?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean removed = Main.deleteAdoption(selectedAdoption);

                    if (removed) {
                        loadAdoptionForms();
                        JOptionPane.showMessageDialog(null, "Application removed successfully.");
                    }
                }
            }
        });

        adoptionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // double-click
                    Adoption selectedAdoption = (Adoption) adoptionList.getSelectedValue();
                    if (selectedAdoption != null) {

                        AdoptionFormEdit editForm = new AdoptionFormEdit();
                        editForm.doShow(selectedAdoption);
                        SwingUtilities.getWindowAncestor(adoptionList).dispose();
                    }
                }
            }
        });
    }

    public void doShow() {
        setContentPane(AdoptionMainPage);
        setSize(1280, 720);
        loadAdoptionForms();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadAdoptionForms() {
        DefaultListModel<Adoption> model = new DefaultListModel<>();

        for (Adoption adop : Main.adoptionsList) {
            model.addElement(adop);
        }

        adoptionList.setModel(model);

        adoptionList.setFixedCellHeight(115); // Gives space for the card height
        adoptionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        adoptionList.setSelectionBackground(new Color(210, 225, 240));
        adoptionList.setSelectionForeground(Color.BLACK);

        adoptionList.setToolTipText("Double-click an application to view full details.");
    }
}
