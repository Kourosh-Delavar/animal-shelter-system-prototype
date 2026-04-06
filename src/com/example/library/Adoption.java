package com.example.library;

public class Adoption {
    // Personal information
    private String name;
    private String address;
    private String cityAndPostalCode;
    private String phoneNmbr;
    private String emailAddr;
    private String dateOfBirth;

    // Living situation
    private String typeOfHome;
    private String homeOwnership; // Rent or Owned
    private String isPetAllowed;
    private String yardExist;
    private String fencesExist;

    // Household
    private int adultsNmbr; // Number of adults in the household
    private int childrenNmbr;
    private String childrenAges;
    private String commonAgreement; // Do all members of the household agree upon the adoption?

    // Current pets
    private String petAlreadyExist;
    private String petType; // What type of pet do you have?
    private String isVaccinated;
    private String isMicroChipped;

    // Experience
    private String thisPetTypeExp; // Do you have experience with this type of pet?
    private double hoursPerDaySomeoneIsHome; // How many hours is someone home?
    private String caretakerDuringVacation;

    // Desired animal
    private String chosenAnimalName;
    private int chosenAnimalShelterId;
    private String adoptionReason;

    // Declarations
    private String adoptionInfoAck;
    private String homeVisitAck;
    private String adoptionFormDate;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityAndPostalCode() {
        return cityAndPostalCode;
    }
    public void setCityAndPostalCode(String cityAndPostalCode) {
        this.cityAndPostalCode = cityAndPostalCode;
    }

    public String getPhoneNmbr() {
        return phoneNmbr;
    }
    public void setPhoneNmbr(String phoneNmbr) {
        this.phoneNmbr = phoneNmbr;
    }

    public String getEmailAddr() {
        return emailAddr;
    }
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTypeOfHome() {
        return typeOfHome;
    }
    public void setTypeOfHome(String typeOfHome) {
        this.typeOfHome = typeOfHome;
    }

    public String getHomeOwnership() {
        return homeOwnership;
    }
    public void setHomeOwnership(String homeOwnership) {
        this.homeOwnership = homeOwnership;
    }

    public String isPetAllowed() {
        return isPetAllowed;
    }
    public void setPetAllowed(String isPetAllowed) {
        this.isPetAllowed = isPetAllowed;
    }

    public String isYardExist() {
        return yardExist;
    }
    public void setYardExist(String yardExist) {
        this.yardExist = yardExist;
    }

    public String isFencesExist() {
        return fencesExist;
    }
    public void setFencesExist(String fencesExist) {
        this.fencesExist = fencesExist;
    }

    public int getAdultsNmbr() {
        return adultsNmbr;
    }
    public void setAdultsNmbr(int adultsNmbr) {
        this.adultsNmbr = adultsNmbr;
    }

    public int getChildrenNmbr() {
        return childrenNmbr;
    }
    public void setChildrenNmbr(int childrenNmbr) {
        this.childrenNmbr = childrenNmbr;
    }

    public String getChildrenAges() {
        return childrenAges;
    }
    public void setChildrenAges(String childrenAges) {
        this.childrenAges = childrenAges;
    }

    public String isCommonAgreement() {
        return commonAgreement;
    }
    public void setCommonAgreement(String commonAgreement) {
        this.commonAgreement = commonAgreement;
    }

    public String isPetAlreadyExist() {
        return petAlreadyExist;
    }
    public void setPetAlreadyExist(String petAlreadyExist) {
        this.petAlreadyExist = petAlreadyExist;
    }

    public String getPetType() {
        return petType;
    }
    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String isVaccinated() {
        return isVaccinated;
    }
    public void setVaccinated(String isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public String isMicroChipped() {
        return isMicroChipped;
    }
    public void setMicroChipped(String isMicroChipped) {
        this.isMicroChipped = isMicroChipped;
    }

    public String isThisPetTypeExp() {
        return thisPetTypeExp;
    }
    public void setThisPetTypeExp(String thisPetTypeExp) {
        this.thisPetTypeExp = thisPetTypeExp;
    }

    public double getHoursPerDaySomeoneIsHome() {
        return hoursPerDaySomeoneIsHome;
    }
    public void setHoursPerDaySomeoneIsHome(double hoursPerDaySomeoneIsHome) {
        this.hoursPerDaySomeoneIsHome = hoursPerDaySomeoneIsHome;
    }

    public String getCaretakerDuringVacation() {
        return caretakerDuringVacation;
    }
    public void setCaretakerDuringVacation(String caretakerDuringVacation) {
        this.caretakerDuringVacation = caretakerDuringVacation;
    }

    public String getChosenAnimalName() {
        return chosenAnimalName;
    }
    public void setChosenAnimalName(String chosenAnimalName) {
        this.chosenAnimalName = chosenAnimalName;
    }

    public int getChosenAnimalShelterId() {
        return chosenAnimalShelterId;
    }
    public void setChosenAnimalShelterId(int chosenAnimalShelterId) {this.chosenAnimalShelterId = chosenAnimalShelterId;}

    public String getAdoptionReason() {
        return adoptionReason;
    }
    public void setAdoptionReason(String adoptionReason) {
        this.adoptionReason = adoptionReason;
    }

    public String isAdoptionInfoAck() {
        return adoptionInfoAck;
    }
    public void setAdoptionInfoAck(String adoptionInfoAck) {
        this.adoptionInfoAck = adoptionInfoAck;
    }

    public String isHomeVisitAck() {
        return homeVisitAck;
    }
    public void setHomeVisitAck(String homeVisitAck) {
        this.homeVisitAck = homeVisitAck;
    }

    public String getAdoptionFormDate() {
        return adoptionFormDate;
    }
    public void setAdoptionFormDate(String adoptionFormDate) {
        this.adoptionFormDate = adoptionFormDate;
    }

    @Override
    public String toString() {

        String statusColor = "#4169E1";

        return "<html>" +
                "<div style='width: 300px; margin: 5px; padding: 12px; border: 1px solid #CCC; background-color: #FDFDFD;'>" +
                "<b style='font-size: 13px; color: #2E8B57;'>Adopter: " + getName() + "</b><br>" +
                "<span style='color: #555;'>Applying for: <b>" + getChosenAnimalName() + "</b></span><br>" +
                "<span style='color: #777;'>ID: " + getChosenAnimalShelterId() + " | Date: " + getAdoptionFormDate() + "</span>" +
                "<br><br>" +
                "<div style='text-align: right; font-size: 8px; color: #AAA; border-top: 0.5px solid #EEE; padding-top: 4px;'>" +
                "<i>Double-click to Review Application</i>" +
                "</div>" +
                "</div>" +
                "</html>";
    }
}