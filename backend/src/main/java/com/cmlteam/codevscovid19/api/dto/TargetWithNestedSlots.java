package com.cmlteam.codevscovid19.api.dto;

import com.cmlteam.codevscovid19.Constants;
import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.models.Target;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetWithNestedSlots {

    private static final Random R = new Random();

    private Integer id;
    private String type;
    private String name;
    private Integer distance;
    private Integer maxPeopleCapacity;
    private String address;
    private Double latitude;
    private Double longitude;
    private String workingTime;
    private String pictureUrl;
    private List<Slot> slots = new ArrayList<>();

    public TargetWithNestedSlots(Target target, List<Slot> slots) {
        this.id = target.getId();
        this.type = target.getType().name();
        this.name = target.getName();
        this.distance = R.nextInt(1500) + 500;
        this.maxPeopleCapacity = (int) (target.getArea() * Constants.MAX_PEOPLE_PER_SQ_METER);
        this.address = target.getAddress();
        this.latitude = target.getLatitude();
        this.longitude = target.getLongitude();
        this.workingTime = target.getWorkingTime();
        this.pictureUrl = target.getPictureUrl();
        this.slots = slots;
    }
}
