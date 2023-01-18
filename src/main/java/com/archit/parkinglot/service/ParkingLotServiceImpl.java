package com.archit.parkinglot.service;

import com.archit.parkinglot.exception.ParkingSpaceNotFound;
import com.archit.parkinglot.model.ParkingLot;
import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.model.request.CreateParkingLotReq;
import com.archit.parkinglot.repository.ParkingLotRepository;
import com.archit.parkinglot.repository.ParkingSpaceRespository;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    private ParkingSpaceRespository parkingSpaceRespository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Override
    @Transactional
    public ParkingSpace findParkingSpace(Integer parkingLotId, SlotType slotType) throws ParkingSpaceNotFound {
        try {
            ParkingSpace parkingSpace = parkingSpaceRespository.findParkingSpace(parkingLotId, slotType);
            parkingSpace.setAvailable(false);
            return parkingSpace;
        } catch (EmptyResultDataAccessException ex) {
            log.error("No parking space found for Lot : {} Type {}", parkingLotId, slotType);
            throw new ParkingSpaceNotFound("No parking space found for slot type"+ slotType
                    , parkingLotId, slotType, ex);
        } catch (RuntimeException exception) {
            log.error("Exception occured during parking space fetch parkingLotId : {} Type {}", parkingLotId, slotType);
            throw new ParkingSpaceNotFound("Exception occured during parking space fetch", parkingLotId,
                    slotType, exception);
        }
    }

    @Override
    public void freeParkingSpace(Integer parking_lot_id, Integer floor, Integer parkingSpaceId) {
        parkingSpaceRespository.freeParkingSpace(parking_lot_id,floor,parkingSpaceId);
    }

    @Override
    public void addParkingLotToDB(CreateParkingLotReq createParkingLotReq) {
        ParkingLot parkingLot = parkingLotMapper.toParkingLot(createParkingLotReq);
        parkingLotRepository.save(parkingLot);
    }
}
