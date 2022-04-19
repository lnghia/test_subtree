package com.example.demo.dto.responses;

import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;

@Builder(toBuilder = true)
public class ResponseBodyDTO {
    private Object data;
    private HashMap<Object, Object> errors;

    public ResponseBodyDTO(){
        data = null;
        errors = new HashMap<>();
    }

    public ResponseBodyDTO(Object data, HashMap<Object, Object> errors) {
        this.data = data;
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HashMap<Object, Object> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<Object, Object> errors) {
        this.errors = errors;
    }
}
