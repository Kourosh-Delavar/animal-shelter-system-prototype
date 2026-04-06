package com.example.library;

import com.example.library.forms.Login;
import com.example.library.forms.MainMenu;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Animals> animalList = new ArrayList<>();
    public static ArrayList<Users> users = new ArrayList<>();
    public static ArrayList<Adoption> adoptionsList = new ArrayList<>();

    public static String[] species = {"Other", "Dog","Cat","Rabbit"};
    public static String[] sex = {"Unknown", "Male","Female"};
    public static String[] city = {"Unknown", "Deventer", "Zutphen", "Apeldoorn"};
    public static String[] intakeReason = {"found", "seized", "given up"};

    public static String[] homeType = {"Apartment", "Townhouse", "Semi-detached", "Detached"};
    public static String[] houseOwnership = {"Rent", "Owned"};
    public static String[] isPetAllowed = {"Yes", "No"};
    public static String[] yardExists = {"Yes", "No"};
    public static String[] fenceExists = {"Yes", "No"};
    public static String[] commonAgreement = {"Yes", "No"};
    public static String[] alreadyHavePet = {"Yes", "No"};
    public static String[] isVaccinated = {"Yes", "No"};
    public static String[] isMiroChipped = {"Yes", "No"};
    public static String[] thisTypeOfPetExp = {"Yes", "No"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setUsers();
                Login login = new Login();
                login.doShow();
            }
        });
    }

    private static void setUsers(){
        Users tempUser = new Users();
        tempUser.username = "admin";
        tempUser.password = "admin";
        tempUser.authLevel = Users.AuthorizationLevels.administrator;

        users.add(tempUser);

        Users tempUser2 = new Users();
        tempUser2.username = "user";
        tempUser2.password = "user";
        tempUser2.authLevel = Users.AuthorizationLevels.caretaker;

        users.add(tempUser2);
    }

    public static boolean deleteAdoption(Adoption adoption) {
        if (adoptionsList.contains(adoption)) {
            return adoptionsList.remove(adoption);
        }
        return false;
    }
}
