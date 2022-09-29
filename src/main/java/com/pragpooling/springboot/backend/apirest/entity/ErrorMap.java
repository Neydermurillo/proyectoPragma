package com.pragpooling.springboot.backend.apirest.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ErrorMap implements Serializable {

    public int statusCode;
    public String cause;
    public String message;

}
