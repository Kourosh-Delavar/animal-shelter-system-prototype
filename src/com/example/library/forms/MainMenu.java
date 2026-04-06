package com.example.library.forms;

import com.example.library.Users;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

public class MainMenu extends JFrame{

    private JPanel mainPanel;
    private JButton goToAnimalsButton1;
    private JButton goToAdoptionsButton;
    private JButton goToReportsButton1;
    private JLabel Date;
    private JLabel userAndRole;

    private JLabel reportsImageLabel;
    private JLabel animalsImageLabel;
    private JLabel adoptionsImageLabel;

    private JPanel animalsPanel;
    private JPanel ReportsPanel;
    private JPanel AdoptionsPanel;

    public MainMenu(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("MainMenu");

        Date.setText(
                "<html><nobr>"
                + "<span style='color:yellow;'>Date: </span> "
                + LocalDate.now()
                + "</nobr></html>");

        try {
            String reportsImagePath = "library/images/reports.jpg";
            String animalsImagePath = "library/images/animals.jpg";
            String adoptionsImagePath = "library/images/adoptions.png";

            int imgWidth = 200;
            int imgHeight = 300;

            ImageIcon reportsIcon = new ImageIcon(getClass().getResource(reportsImagePath));
            reportsIcon = new ImageIcon(reportsIcon.getImage().getScaledInstance(imgWidth, imgHeight, java.awt.Image.SCALE_SMOOTH));
            reportsImageLabel = new JLabel(reportsIcon);

            ImageIcon animalsIcon = new ImageIcon(getClass().getResource(animalsImagePath));
            animalsIcon = new ImageIcon(animalsIcon.getImage().getScaledInstance(imgWidth, imgHeight, java.awt.Image.SCALE_SMOOTH));
            animalsImageLabel = new JLabel(animalsIcon);

            ImageIcon adoptionsIcon = new ImageIcon(getClass().getResource(adoptionsImagePath));
            adoptionsIcon = new ImageIcon(adoptionsIcon.getImage().getScaledInstance(imgWidth, imgHeight, java.awt.Image.SCALE_SMOOTH));
            adoptionsImageLabel = new JLabel(adoptionsIcon);

            JPanel reportsPanel = new JPanel();
            reportsPanel.setLayout(new BoxLayout(reportsPanel, BoxLayout.Y_AXIS));
            reportsPanel.add(reportsImageLabel);
            reportsPanel.add(goToReportsButton1);

            JPanel animalsPanel = new JPanel();
            animalsPanel.setLayout(new BoxLayout(animalsPanel, BoxLayout.Y_AXIS));
            animalsPanel.add(animalsImageLabel);
            animalsPanel.add(goToAnimalsButton1);

            JPanel adoptionsPanel = new JPanel();
            adoptionsPanel.setLayout(new BoxLayout(adoptionsPanel, BoxLayout.Y_AXIS));
            adoptionsPanel.add(adoptionsImageLabel);
            adoptionsPanel.add(goToAdoptionsButton);

            reportsPanel.add(reportsPanel);
            animalsPanel.add(animalsPanel);
            adoptionsPanel.add(adoptionsPanel);


        } catch (Exception e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        MainMenu.this,
                        "Are you sure you want to quit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });

        goToAnimalsButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AnimalsList animalsList = new AnimalsList();
                animalsList.doShow();
            }
        });

        goToAdoptionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AdoptionList adopList = new AdoptionList();
                adopList.doShow();
            }
        });

        goToReportsButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AnimalReport animalReport = new AnimalReport();
                animalReport.doShow();
            }
        });
    }

    public void doShow(Users loggedInUser){
        pack();
        setSize(1280,720);
        setContentPane(mainPanel);
        userAndRole.setText(
                "<html><nobr>" +
                        "<span style='color:yellow;'>User:</span> "
                        + loggedInUser.username
                        + "<span style='color:black;'> ----- </span> " +
                        "<span style='color:yellow;'>Role:</span> "
                        + loggedInUser.authLevel +
                        "</nobr></html>"
        );
        setLocationRelativeTo(null);
        setVisible(true);
    }
}