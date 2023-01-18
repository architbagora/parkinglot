package com.archit.parkinglot.repository;

import com.archit.parkinglot.model.ParkingLot;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Slf4j
public class ParkingLotRepository {

    @Autowired
    EntityManager entityManager;
    public void save(ParkingLot parkingLot){
        parkingLot.getParkingSpaces().stream().forEach(parkingSpace -> entityManager.persist(parkingSpace));
        entityManager.persist(parkingLot);
    }
}
