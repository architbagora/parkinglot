package com.archit.parkinglot.controller;

import com.archit.parkinglot.model.ParkingLot;
import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.repository.ParkingLotRepository;
import com.archit.parkinglot.service.ParkingLotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ParkingController {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping(value = "/{parkingLotId}")
    public ResponseEntity<ParkingLot> getParkingLot(@PathVariable String parkingLotId) {
        log.info("Request recieved for "+ parkingLotId);
        ParkingLot parkingLot = parkingLotRepository.findById(Integer.valueOf(parkingLotId));
        return ResponseEntity.ok().body(parkingLot);
    }


    @GetMapping(value = "/getslot/{parkingLotId}/{size}")
    public ResponseEntity<ParkingSpace> getParkingSlot(@PathVariable String parkingLotId,
                                                     @PathVariable String size) {
        log.info("Request recieved for {} and Size {}", parkingLotId ,size);

        ParkingSpace parkingSpace = parkingLotService.findParkingSpace(Integer.valueOf(parkingLotId)
            ,SlotType.valueOf(size));

        return ResponseEntity.ok().body(parkingSpace);
    }


}
