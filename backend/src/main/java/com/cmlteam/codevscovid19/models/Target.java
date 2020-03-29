package com.cmlteam.codevscovid19.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Target {

    private Integer id;
    private TargetType type = TargetType.shop;
    private String name;
    private Long area;
    private String address;
    private Double latitude;
    private Double longitude;
    private String workingTime;
    private String pictureUrl;
    private List<Integer> slots = new ArrayList<>();

    public enum TargetType {
        shop,
        pharmacy,
        postalOffice
    }

}
