package com.syntechsiacommonservice.service.impl;

import com.syntechsiacommonservice.entity.StudentEntity;
import com.syntechsiacommonservice.model.request.PagingRequestDto;
import com.syntechsiacommonservice.model.request.StudentRegisterDto;
import com.syntechsiacommonservice.model.response.GlobalReponseDto;
import com.syntechsiacommonservice.repository.StudentRepository;
import com.syntechsiacommonservice.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<StudentEntity> list(PagingRequestDto request) {
        log.info("start get data list students");
        Page<StudentEntity> response;
        try {
            Pageable pageable = PageRequest.of(request.getPageNumber(), request.getLimit(), Sort.by("createdDate").descending());
            response = studentRepository.findAll(pageable);
        } catch (Exception e) {
            log.error("error get data list student {}", e.getMessage());
            response = null;
        }
        log.info("end get data list students");
        return response;
    }

    @Override
    public GlobalReponseDto<StudentEntity> save(StudentRegisterDto studentRegisterDto) {
        GlobalReponseDto<StudentEntity> response;
        StudentEntity studentEntity;
        try {
            studentEntity = studentRepository.findByNik(studentRegisterDto.getNik());
            if (ObjectUtils.isEmpty(studentEntity)) {
                studentEntity = studentRepository.save(new StudentEntity(studentRegisterDto));
                response = new GlobalReponseDto<>("00", "Success", studentEntity);
            } else {
                response = new GlobalReponseDto<>("01", "Failed", studentEntity);
            }
        } catch (Exception e) {
            log.error("error save student {}", e.getMessage());
            return new GlobalReponseDto<>("01", "error save student ".concat(e.getMessage()), null);
        }
        log.info("end save student");
        return response;
    }

    @Override
    public Object update(Long id) {
        return null;
    }

    @Override
    public Object delete(Long id) {
        return null;
    }
}
