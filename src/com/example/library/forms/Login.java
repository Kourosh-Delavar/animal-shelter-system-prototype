package com.example.library.forms;

import com.example.library.Main;
import com.example.library.Users;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Login extends JFrame {
    private JButton loginButton;
    private JPasswordField txtFieldPassword;
    private JTextField txtFieldUsername;
    private JPanel Root;

    public Login() {
        setTitle("Second Chance - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String inputUser = txtFieldUsername.getText();
                String inputPass = new String(txtFieldPassword.getPassword());
                boolean loginSuccessful = false;

                for (Users user : Main.users) {
                    if (Objects.equals(user.username, inputUser) && Objects.equals(user.password, inputPass)) {
                        loginSuccessful = true;
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.doShow(user);
                        dispose();
                        break;
                    }
                }

                if (!loginSuccessful) {
                    JOptionPane.showMessageDialog(Root,
                            "Invalid username or password. Please try again.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);

                    txtFieldPassword.setText("");
                    txtFieldUsername.requestFocus();
                }
            }
        });
    }

    public void doShow() {
        setContentPane(Root);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}