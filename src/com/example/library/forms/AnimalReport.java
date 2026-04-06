package com.example.library.forms;

import javax.swing.*;

import com.example.library.Adoption;
import com.example.library.Animals;
import com.example.library.Main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AnimalReport extends JFrame {
    private JPanel reportPanel;
    private JButton btnBack;
    private JTextArea txtAreaReport;

    public AnimalReport() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Shelter Analytics - Second Chance");


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
    }


    private String generateStat() {
        ArrayList<Animals> animals = Main.animalList;
        ArrayList<Adoption> adoptions = Main.adoptionsList;

        if (animals == null) return "Error: Animal data not initialized.";

        int totalAnimals = animals.size();
        int dogs = 0, cats = 0, rabbits = 0, others = 0;
        int found = 0, seized = 0, givenUp = 0;

        for (Animals a : animals) {
            String sp = a.getSpecies().toLowerCase();
            if (sp.contains("dog")) dogs++;
            else if (sp.contains("cat")) cats++;
            else if (sp.contains("rabbit")) rabbits++;
            else others++;

            String reason = a.getIntakeReason().toLowerCase();
            if (reason.contains("found")) found++;
            else if (reason.contains("seized")) seized++;
            else if (reason.contains("given up")) givenUp++;
        }

        int totalAdoptions = (adoptions != null) ? adoptions.size() : 0;

        double demandRatio = totalAnimals > 0 ? (double) totalAdoptions / totalAnimals : 0;

        StringBuilder sb = new StringBuilder();
        sb.append(" ==========================================\n");
        sb.append("        SHELTER OPERATIONAL REPORT         \n");
        sb.append(" ==========================================\n\n");

        sb.append(String.format("   Total Animals in Care:   %d\n", totalAnimals));
        sb.append(String.format("   Active Applications:     %d\n", totalAdoptions));
        sb.append(String.format("   Adoption Demand Ratio:   %.2f apps/animal\n\n", demandRatio));

        sb.append(" SPECIES DISTRIBUTION\n");
        sb.append(" --------------------\n");
        sb.append(String.format("    Dogs:    %d\n", dogs));
        sb.append(String.format("    Cats:    %d\n", cats));
        sb.append(String.format("    Rabbits: %d\n", rabbits));
        sb.append(String.format("    Others:  %d\n\n", others));

        sb.append(" INTAKE REASONS\n");
        sb.append(" --------------\n");
        sb.append(String.format("    Found/Stray: %d\n", found));
        sb.append(String.format("    Legal Seizure: %d\n", seized));
        sb.append(String.format("    Surrendered:   %d\n\n", givenUp));

        sb.append(" SYSTEM STATUS\n");
        sb.append(" -------------\n");
        sb.append(" Shelter Capacity:  ")
                .append(totalAnimals > 1200 ? "   ⚠️ HIGH OCCUPANCY" : "✅ NORMAL")
                .append("\n");

        return sb.toString();
    }


    public void show_report() {
        String reportData = generateStat();

        txtAreaReport.setText(reportData);
        txtAreaReport.setEditable(false);

        txtAreaReport.setFont(new Font("Monospaced", Font.PLAIN, 16));

        txtAreaReport.setLineWrap(true);
        txtAreaReport.setWrapStyleWord(true);
    }

    public void doShow() {
        setContentPane(reportPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        show_report();
        setVisible(true);
    }
}
