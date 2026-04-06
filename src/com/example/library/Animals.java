package com.example.library;

public class Animals {

    private int shelterId;
    private String name;
    private String breed;
    private int age;
    private String microChipNmbr;
    private String colorAndOrMarkings;
    private String species;
    private String sex;
    private String city;
    private String intakeReason;
    private String initLocation;
    private String ownerName;
    private String ownerPhoneNmbr;
    private String medicalNote;
    private String generalNote;
    private String initHealthCheck;

    public int getShelterId() {
        return shelterId;
    }
    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getMicroChipNmbr() {
        return microChipNmbr;
    }
    public void setMicroChipNmbr(String microChipNmbr) {
        this.microChipNmbr = microChipNmbr;
    }

    public String getColorAndOrMarkings() {
        return colorAndOrMarkings;
    }
    public void setColorAndOrMarkings(String colorAndOrMarkings) {
        this.colorAndOrMarkings = colorAndOrMarkings;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String speciesIndex) {
        this.species = speciesIndex;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sexIndex) {
        this.sex = sexIndex;
    }

    public String getCity() {return city;}
    public void setCity(String cityIndex) {this.city = cityIndex;}

    public String getIntakeReason() {return intakeReason;}
    public void setIntakeReason(String intakeReasonIndex) {this.intakeReason = intakeReasonIndex;}

    public String getInitLocation() {return initLocation;}
    public void setInitLocation(String initLocationIndex) {this.initLocation = initLocationIndex;}

    public String getOwnerName() {return ownerName;}
    public void setOwnerName(String ownerNameIndex) {this.ownerName = ownerNameIndex;}

    public String getOwnerPhoneNmbr() {return ownerPhoneNmbr;}
    public void setOwnerPhoneNmbr(String ownerPhoneNmbrIndex) {this.ownerPhoneNmbr = ownerPhoneNmbrIndex;}

    public String getMedicalNote() {return medicalNote;}
    public void setMedicalNote(String medicalNoteIndex) {this.medicalNote = medicalNoteIndex;}

    public String getGeneralNote() {return generalNote;}
    public void setGeneralNote(String generalNoteIndex) {this.generalNote = generalNoteIndex;}

    public String getInitHealthCheck() {return initHealthCheck;}
    public void setInitHealthCheck(String initHealthCheckIndex) {this.initHealthCheck = initHealthCheckIndex;}

    @Override
    public String toString() {
        String cityColor;
        String currentCity = (getCity() != null) ? getCity() : "Unknown";

        switch (currentCity) {
            case "Deventer": cityColor = "#2E8B57"; break;
            case "Zutphen": cityColor = "#4169E1"; break;
            case "Apeldoorn": cityColor = "#8B0000"; break;
            default: cityColor = "#333333"; break;
        }

        return "<html>" +
                "<div style='width: 300px; margin: 5px; padding: 10px; border: 1px solid #CCC; background-color: #F9F9F9;'>" +
                "<b style='font-size: 12px; color: darkGreen;'>Name: " + getName() + "</b><br>" +
                "<span style='color: #555;'>ID: " + getShelterId() + "</span> | " +
                "<span style='color: " + cityColor + ";'><b>" + currentCity + "</b></span><br>" +
                "<span style='color: gray;'>Species: " + getSpecies() + "</span>" +
                "<br><br>" +
                "<div style='text-align: right; font-size: 8px; color: #999; border-top: 0.5px solid #EEE; padding-top: 3px;'>" +
                "<i>Double-click to Open</i>" +
                "</div>" +
                "</div>" +
                "</html>";
    }
}
