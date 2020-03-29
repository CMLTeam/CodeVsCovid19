package com.cmlteam.codevscovid19.models;

import lombok.Data;

@Data
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

    public enum TargetType {
        shop,
        pharmacy,
        postalOffice
    }

}
