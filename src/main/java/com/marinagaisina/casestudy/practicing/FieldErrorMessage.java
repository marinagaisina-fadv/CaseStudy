package com.marinagaisina.casestudy.practicing;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.management.ConstructorParameters;

@Getter
@Setter
public class FieldErrorMessage {
    private String field;
    private Object rejectedValue;
    private String message;

    public FieldErrorMessage(String field, Object rejectedValue, String message) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    @Override
    public String toString() {
        return "FieldErrorMessage{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
