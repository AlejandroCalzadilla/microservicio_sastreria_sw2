package com.accounts.cliente.modelos;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Telephone {


    @Field
    private String number;
    private String type;

    public String getNumber() {
        return number;
    }



    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

