package com.example.demo.dto.Response;

import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;

@Builder(toBuilder = true)
public class ResponseBodyDTO {
    private Object data;
    private ArrayList<String> errors;

    public ResponseBodyDTO(){
        data = null;
        errors = new ArrayList<>();
    }

    public ResponseBodyDTO(Object data, ArrayList<String> errors) {
        this.data = data;
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}
