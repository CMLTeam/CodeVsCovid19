package com.cmlteam.codevscovid19.models;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private Integer id;
    private String phoneNumber;
    private String documentId;
    private String name;
    private long illnessRate;
    private CustomerStatus status = CustomerStatus.normal;
    private String address;
    private String pictureUrl;
    private List<Integer> closeCommunicationWith;
    private List<Integer> bookedSlots;

    public enum CustomerStatus {
        normal,
        required_doctor_visit,
        civid19_positive
    }

}
