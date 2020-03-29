package com.cmlteam.codevscovid19.models;

import lombok.Data;

import java.util.Set;

@Data
public class Doctor {

    private Integer id;
    private String name;
    private Set<Long> customers;
}
