package com.cmlteam.codevscovid19.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
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
        analysis,
        ill
    }

}
