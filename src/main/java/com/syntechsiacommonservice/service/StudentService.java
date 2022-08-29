package com.syntechsiacommonservice.service;

import com.syntechsiacommonservice.entity.StudentEntity;
import com.syntechsiacommonservice.model.request.PagingRequestDto;
import com.syntechsiacommonservice.model.request.StudentRegisterDto;
import com.syntechsiacommonservice.model.response.GlobalReponseDto;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<StudentEntity> list(PagingRequestDto request);

    GlobalReponseDto<StudentEntity> save(StudentRegisterDto request);

    Object update(Long id);

    Object delete(Long id);
}
