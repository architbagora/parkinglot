package com.archit.parkinglot.service;

import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.repository.ParkingLotRepository;
import com.archit.parkinglot.repository.ParkingSpaceRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    private ParkingSpaceRespository parkingSpaceRespository;

    @Override
    public ParkingSpace findParkingSpace(Integer parkingLotId, SlotType slotType) {
        ParkingSpace parkingSpace = parkingSpaceRespository.findParkingSpace(parkingLotId, slotType);
        if(parkingSpace == null) {
            log.error("No parking space found for Lot : {} Type {}" , parkingLotId , slotType);
            return  null;
        }
        return parkingSpace;
    }
}
