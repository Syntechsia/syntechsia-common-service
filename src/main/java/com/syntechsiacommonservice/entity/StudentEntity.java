package com.syntechsiacommonservice.entity;

import com.syntechsiacommonservice.model.request.StudentRegisterDto;
import com.syntechsiacommonservice.util.ConstantUtil;
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
    private String statusSendEmail;

    @PrePersist
    void onSave() {
        this.createdDate = new Date();
        this.status = 0;
    }

    @PostUpdate
    void onUpdate() {
        this.updatedDate = new Date();
    }

    public StudentEntity(StudentRegisterDto studentRegisterDto, String statusSendEmail) {
        this.nik = studentRegisterDto.getNik();
        this.name = studentRegisterDto.getName();
        this.gender = studentRegisterDto.getGender();
        this.dateOfBirth = DateUtil.stringToDate(studentRegisterDto.getDateOfBirth(), ConstantUtil.YYYMMDD);
        this.phoneNumber = studentRegisterDto.getPhoneNumber();
        this.email = studentRegisterDto.getEmail();
        this.education = studentRegisterDto.getEducation();
        this.address = studentRegisterDto.getAddress();
        this.payment = studentRegisterDto.getPayment();
        this.batch = studentRegisterDto.getBatch();
        this.program = studentRegisterDto.getProgram();
        this.statusSendEmail = statusSendEmail;
    }

}
