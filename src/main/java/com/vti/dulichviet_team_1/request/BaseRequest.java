package com.vti.dulichviet_team_1.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BaseRequest {

    @Min(value = 0, message = "số trang phải lớn hơn 0")
    protected int page;

    @Min(value = 0, message = "Size phải lớn hơn 0")
    protected int size;

    protected String sortField;

    protected String sortType;

}
