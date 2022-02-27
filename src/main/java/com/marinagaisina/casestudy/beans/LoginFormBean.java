package com.marinagaisina.casestudy.beans;

import com.marinagaisina.casestudy.validation.EmailUnique;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.FieldError;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LoginFormBean {

    // making sure the username is not null and is not empty as in ""
    @NotEmpty(message = "Username is required.")
//    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
//    @EmailUnique(message = "Email must be unique")
    private String username;

    /*// these annotations work on integer values only
    @Min(value=3, message="Age must be at least 3")
    @Max(value=10, message="Age must be at most 10")
    private Integer age;
*/
    @NotEmpty(message = "Password is required.")
    private String password;

    // this list is populated by the controller with all error messages
    // in the binding result."
    private List<String> errorMessages = new LinkedList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
