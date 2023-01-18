package com.archit.parkinglot.model.request;

import com.archit.parkinglot.model.SlotType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CreateParkingLotReq {
    private String parkingLotName;
    private List<FloorPlan> floorPlanList;

    public void addFloorPlan(FloorPlan floorPlan){
        this.floorPlanList.add(floorPlan);
    }
}



