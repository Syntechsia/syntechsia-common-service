package com.syntechsiacommonservice.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingRequestDto {
    private Integer pageNumber;
    private Integer limit;

    public PagingRequestDto(Integer pageNumber, Integer limit) {
        this.pageNumber = pageNumber;
        this.limit = limit;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
