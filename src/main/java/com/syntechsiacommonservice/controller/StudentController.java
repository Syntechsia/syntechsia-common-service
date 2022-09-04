package com.syntechsiacommonservice.controller;

import com.syntechsiacommonservice.entity.StudentEntity;
import com.syntechsiacommonservice.model.request.PagingRequestDto;
import com.syntechsiacommonservice.model.request.StudentRegisterDto;
import com.syntechsiacommonservice.model.response.GlobalReponseDto;
import com.syntechsiacommonservice.service.StudentService;
import com.syntechsiacommonservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = {
        "https://syntechsia.com",
        "http://103.214.112.174:3030",
        "http://103.214.112.174:8081"
})
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public GlobalReponseDto<StudentEntity> studentRegister(@RequestBody StudentRegisterDto studentRegisterDto) {
        log.info("incoming request register student with request {}", JsonUtil.getString(studentRegisterDto));
        return studentService.save(studentRegisterDto);
    }

    @PostMapping("get-all")
    public Page<StudentEntity> studentGetAll(@RequestBody PagingRequestDto request) {
        log.info("incoming request get all student with request {}", JsonUtil.getString(request));
        return studentService.list(request);
    }
}
