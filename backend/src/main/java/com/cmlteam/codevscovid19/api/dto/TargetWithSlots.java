package com.cmlteam.codevscovid19.api.dto;

import com.cmlteam.codevscovid19.Common;
import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.models.Target;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TargetWithSlots {

    private TargetDto target;
    private List<Slot> slots;

    public TargetWithSlots(Target target, List<Slot> slots) {
        this.target = new TargetDto(target);
        this.slots = slots;
    }

    @Data
    @NoArgsConstructor
    public static class TargetDto {

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

        TargetDto(Target target) {
            this.id = target.getId();
            this.type = target.getType().name();
            this.name = target.getName();
            this.distance = Common.getAppropriateDistance();
            this.maxPeopleCapacity = (int) (target.getArea() * Common.MAX_PEOPLE_PER_SQ_METER);
            this.address = target.getAddress();
            this.latitude = target.getLatitude();
            this.longitude = target.getLongitude();
            this.workingTime = target.getWorkingTime();
            this.pictureUrl = target.getPictureUrl();
        }
    }
}
