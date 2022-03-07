package com.marinagaisina.casestudy.beans;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateItemBean {

    private Integer id;

    @Length(min = 1, max = 45,
            message = "Item Name must be between 1 and 45 characters in length.")
    private String name;

    private String description;

    @NotEmpty(message = "Input available quantity.")
    private Integer availQuantity;

    @NotEmpty(message = "Input the item's quantity that are required for being added to a package.")
    private Integer quantityToPackage;

    //** TODO implement load from PC pictures for Items' profiles
    private String imageUrl;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

