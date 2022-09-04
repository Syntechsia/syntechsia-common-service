package com.syntechsiacommonservice.model.request;

import com.syntechsiacommonservice.entity.StudentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailRequest {

    private String emailTo;
    private String studentName;
    private String nik;
    private String programName;
    private String templateName;

    public EmailRequest(StudentEntity studentEntity, String templateName) {
        this.emailTo = studentEntity.getEmail();
        this.studentName = studentEntity.getName();
        this.nik = studentEntity.getNik();
        this.programName = studentEntity.getProgram();
        this.templateName = templateName;
    }
}
