package com.archit.parkinglot.service;

import com.archit.parkinglot.exception.ParkingSpaceNotFound;
import com.archit.parkinglot.model.ParkingSlotType;
import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.model.request.CreateParkingLotReq;

public interface ParkingLotService {
    ParkingSpace findParkingSpace(Integer parking_lot_id, SlotType slotType) throws ParkingSpaceNotFound;

    void freeParkingSpace(Integer parking_lot_id, Integer floor, Integer parkingSpaceId);
    void addParkingLotToDB(CreateParkingLotReq createParkingLotReq);
}
