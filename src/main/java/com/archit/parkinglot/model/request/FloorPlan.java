package com.archit.parkinglot.model.request;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FloorPlan {
    private Short floorNumber;
    private Set<SlotCount> numberOfSlots;

    public void addToSlot(SlotCount slotCount){
        this.numberOfSlots.add(slotCount);
    }
}