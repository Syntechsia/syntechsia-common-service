package com.syntechsiacommonservice.service.impl;

import com.syntechsiacommonservice.config.RestConfig;
import com.syntechsiacommonservice.entity.StudentEntity;
import com.syntechsiacommonservice.model.request.EmailRequest;
import com.syntechsiacommonservice.model.request.PagingRequestDto;
import com.syntechsiacommonservice.model.request.StudentRegisterDto;
import com.syntechsiacommonservice.model.response.EmailResponse;
import com.syntechsiacommonservice.model.response.GlobalReponseDto;
import com.syntechsiacommonservice.repository.StudentRepository;
import com.syntechsiacommonservice.service.StudentService;
import com.syntechsiacommonservice.util.ConstantUtil;
import com.syntechsiacommonservice.util.JsonUtil;
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

    private final RestConfig restConfig;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, RestConfig restConfig) {
        this.studentRepository = studentRepository;
        this.restConfig = restConfig;
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
        GlobalReponseDto<StudentEntity> response = null;
        StudentEntity studentEntity;
        try {
            studentEntity = studentRepository.findByNik(studentRegisterDto.getNik());
            if (ObjectUtils.isEmpty(studentEntity)) {
                studentEntity = studentRepository.save(new StudentEntity(studentRegisterDto));
                if (!ObjectUtils.isEmpty(studentEntity)) {
                    EmailRequest request = new EmailRequest(studentEntity, ConstantUtil.REGISTER_TEMPLATE);
                    log.info("start send to email api {}", JsonUtil.getString(request));
                    EmailResponse emailResponse = (EmailResponse) restConfig.sendEmail(request);
                    if (emailResponse.getStatus().equals(ConstantUtil.SUCCESS_STATUS)) {
                        studentEntity.setStatus(ConstantUtil.ACTIVATE);
                        studentRepository.save(studentEntity);
                        response = new GlobalReponseDto<>(ConstantUtil.SUCCESS_STATUS, ConstantUtil.SUCCESS, studentEntity);
                    } else {
                        log.info("failed send email for request {}", JsonUtil.getString(request));
                    }
                } else {
                    response = new GlobalReponseDto<>(ConstantUtil.FAILED_STATUS, ConstantUtil.FAILED, studentEntity);
                }
            } else {
                response = new GlobalReponseDto<>(ConstantUtil.FAILED_STATUS, ConstantUtil.FAILED, studentEntity);
            }
        } catch (Exception e) {
            log.error("error save student {}", e.getMessage());
            return new GlobalReponseDto<>(ConstantUtil.FAILED_STATUS, "error save student ", null);
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
