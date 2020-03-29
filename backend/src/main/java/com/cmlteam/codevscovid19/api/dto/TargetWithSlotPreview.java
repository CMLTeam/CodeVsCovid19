package com.cmlteam.codevscovid19.api.dto;

import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.models.Target;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TargetWithSlotPreview {

    private Integer id;
    private Target.TargetType type = Target.TargetType.shop;
    private String name;
    private Long area;
    private String address;
    private Double latitude;
    private Double longitude;
    private String workingTime;
    private String pictureUrl;
    private List<SlotPreview> slots = new ArrayList<>();

    @Getter
    @Setter
    public static class SlotPreview {
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer freeCapacity;

        public static SlotPreview fromSLot(Slot slot) {
            SlotPreview slotPreview = new SlotPreview();
            slotPreview.setStartDate(slot.getStartDate());
            slotPreview.setEndDate(slot.getEndDate());
            slotPreview.setFreeCapacity(slot.getFreeCapacity());
            return slotPreview;
        }
    }


}
