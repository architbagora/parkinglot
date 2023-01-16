package com.archit.parkinglot.service;

import com.archit.parkinglot.model.ParkingSlotType;
import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;

public interface ParkingLotService {
    public ParkingSpace findParkingSpace(Integer parking_lot_id, SlotType slotType);

}
