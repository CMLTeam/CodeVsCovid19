package com.cmlteam.codevscovid19.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Slots {

    private Integer id;
    private Integer targetId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer freeCapacity;

}
