package com.safetynet.alerts.dto;


import java.util.Date;
import java.util.List;

public class PersonDTO {

    private String firstName;
    private String lastName;
    private AddressDTO addressDTO;
    private String phone;
    private String email;
    private Date birthdate;
    private Integer age;
    private MedicalRecordDTO medicalRecordsDTO;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public MedicalRecordDTO getMedicalRecordsDTO() {
        return medicalRecordsDTO;
    }

    public void setMedicalRecordsDTO(MedicalRecordDTO medicalRecordsDTO) {
        this.medicalRecordsDTO = medicalRecordsDTO;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressDTO=" + addressDTO +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", age=" + age +
                ", medicalRecordsDTO=" + medicalRecordsDTO +
                '}';
    }
}
