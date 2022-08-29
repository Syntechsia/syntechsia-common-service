package com.syntechsiacommonservice.entity;

import com.syntechsiacommonservice.model.request.StudentRegisterDto;
import com.syntechsiacommonservice.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "student")
@Data
@NoArgsConstructor
public class StudentEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Column(name = "nik", columnDefinition = "NIK | NIS | NIM | PASSPORT")
    private String nik;
    private String name;
    //    @Column(name = "gender", columnDefinition = "1=MALE | 2=FEMALE")
    private Integer gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String education;
    private String address;
    private String payment;
    private String batch;
    private String program;
    //    @Column(name = "status", columnDefinition = "0=NEW | 1=active | 2=inactive")
    private Integer status;
    private Date createdDate;
    private Date updatedDate;

    @PrePersist
    void onSave() {
        this.createdDate = new Date();
        this.status = 0;
    }

    @PostUpdate
    void onUpdate() {
        this.updatedDate = new Date();
    }

    public StudentEntity(StudentRegisterDto studentRegisterDto) {
        this.nik = studentRegisterDto.getNik();
        this.name = studentRegisterDto.getName();
        this.gender = studentRegisterDto.getGender();
        this.dateOfBirth = DateUtil.stringToDate(studentRegisterDto.getDateOfBirth(), "dd-MM-yyyy");
        this.phoneNumber = studentRegisterDto.getPhoneNumber();
        this.email = studentRegisterDto.getEmail();
        this.education = studentRegisterDto.getEducation();
        this.address = studentRegisterDto.getAddress();
        this.payment = studentRegisterDto.getPayment();
        this.batch = studentRegisterDto.getBatch();
        this.program = studentRegisterDto.getProgram();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
