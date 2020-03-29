package com.cmlteam.codevscovid19.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Slot {

    private Integer id;
    private Integer targetId;
    private String asString;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer freeCapacity;

}
