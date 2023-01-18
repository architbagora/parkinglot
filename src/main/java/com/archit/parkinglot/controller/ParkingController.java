package com.archit.parkinglot.controller;

import com.archit.parkinglot.exception.ParkingSpaceNotFound;
import com.archit.parkinglot.model.ErrorResponse;
import com.archit.parkinglot.model.ParkingLot;
import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.model.request.CreateParkingLotReq;
import com.archit.parkinglot.repository.ParkingLotRepository;
import com.archit.parkinglot.service.ParkingLotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestController
@Slf4j
public class ParkingController {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    ObjectMapper objectMapper;


    @GetMapping(value = "/getslot/{parkingLotId}/{size}")
    public ResponseEntity<ParkingSpace> getParkingSlot(@PathVariable Integer parkingLotId,
                                                       @PathVariable String size) {
        log.info("Request recieved for {} and Size {}", parkingLotId, size);
        ParkingSpace parkingSpace = parkingLotService.findParkingSpace(parkingLotId
                , SlotType.valueOf(size.toUpperCase()));
        return ResponseEntity.ok().body(parkingSpace);
    }

    @GetMapping(value = "/freeslot/{parkingLotId}/{floor}/{slotid}")
    public ResponseEntity<String> freeParkingSlot(@PathVariable Integer parkingLotId,
                                                  @PathVariable Integer floor,
                                                  @PathVariable Integer slotid) {
        log.info("Free parking lot received for {} and Floor {} and slotid {}", parkingLotId, floor, slotid);
        parkingLotService.freeParkingSpace(parkingLotId
                , floor, slotid);
        return ResponseEntity.ok().body("Slot " + slotid + " Is free on floor "
                + floor + " in " + parkingLotId + " parkinglot");
    }


    @PostMapping(value = "/parkinglot")
    public ResponseEntity<String> buildParkingSlot(@RequestBody CreateParkingLotReq createParkingLotReq) throws JsonProcessingException {
        parkingLotService.addParkingLotToDB(createParkingLotReq);
        return ResponseEntity.ok().body("Successfully created");
    }


    @ExceptionHandler(ParkingSpaceNotFound.class)
    protected ResponseEntity<ErrorResponse> handleParkingNotFoundEx(ParkingSpaceNotFound ex,
                                                                    WebRequest request) {
        log.error("Exception occured at URI {}", ((ServletWebRequest) request).getRequest().getRequestURI()
                , ex);

        ErrorResponse errorResponse = ErrorResponse.builder().message(ex.getMessage())
                .parkingLot(ex.getParkingLotId())
                .slotType(ex.getRequestedSlotType())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest webRequest) {
        log.error("Exception occured at URI {}", ((ServletWebRequest) webRequest).getRequest().getRequestURI()
                , ex);
        ErrorResponse errorResponse = ErrorResponse.builder().message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

}
