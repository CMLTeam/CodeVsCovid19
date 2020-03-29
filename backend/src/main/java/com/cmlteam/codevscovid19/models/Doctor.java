package com.cmlteam.codevscovid19.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Doctor {

    private Integer id;
    private String name;
    private List<Integer> customers = new ArrayList<>();
}
