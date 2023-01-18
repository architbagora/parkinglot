package com.archit.parkinglot.service;

import com.archit.parkinglot.model.ParkingLot;
import com.archit.parkinglot.model.ParkingSlotType;
import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.model.request.CreateParkingLotReq;
import com.archit.parkinglot.model.request.FloorPlan;
import com.archit.parkinglot.util.ParkingUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ParkingLotMapper {


    public ParkingLot toParkingLot(CreateParkingLotReq createParkingLotReq){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotName(createParkingLotReq.getParkingLotName());
        parkingLot.setParkingSpaces(convertToParkingSpaces(createParkingLotReq.getFloorPlanList(),parkingLot));
        return parkingLot;
    }

    private List<ParkingSpace> convertToParkingSpaces(List<FloorPlan> floorPlanList,ParkingLot parkingLot) {
        return floorPlanList.stream().map(
                floorPlan -> toParkingSpace(floorPlan, parkingLot)
        ).flatMap(List::stream).collect(Collectors.toList());
    }

    private List<ParkingSpace> toParkingSpace(FloorPlan floorPlan,ParkingLot parkingLot) {
        AtomicInteger parkingSpotNumber = new AtomicInteger(0);
        return floorPlan.getNumberOfSlots().stream().map(slotCount -> {
            return IntStream.rangeClosed(1, slotCount.getCount())
                    .mapToObj(i -> ParkingSpace.builder()
                                        .floor(floorPlan.getFloorNumber())
                                        .parkingSlotType(ParkingUtil.findSlotType(slotCount.getSlotType()))
                                        .parkingSpot((short)parkingSpotNumber.incrementAndGet())
                                        .isAvailable(true)
                                        .parkingLot(parkingLot)
                                        .build()
                    ).collect(Collectors.toList());
        }).flatMap(List::stream).collect(Collectors.toList());
    }


}
