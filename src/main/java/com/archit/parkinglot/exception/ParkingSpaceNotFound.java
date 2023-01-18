package com.archit.parkinglot.exception;

import com.archit.parkinglot.model.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ParkingSpaceNotFound extends RuntimeException {
    private Integer parkingLotId;
    private SlotType requestedSlotType;


    public ParkingSpaceNotFound(String message, Integer parkingLotId, SlotType slotType, Throwable cause) {
        super(message, cause);
        this.parkingLotId = parkingLotId;
        this.requestedSlotType = slotType;
    }
}
