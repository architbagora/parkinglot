package com.archit.parkinglot.repository;

import com.archit.parkinglot.model.ParkingLot;
import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.util.ParkingUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Slf4j
public class ParkingSpaceRespository {
    @Autowired
    EntityManager entityManager;

    public ParkingSpace findParkingSpace(Integer parkingLotID, SlotType slotType) {
        TypedQuery<ParkingSpace> query = entityManager.createQuery(
                " SELECT ps FROM ParkingSpace ps WHERE ps.parkingLot.parkingLotId=:parkingLotID\n" +
                        " AND ps.parkingSlotType.slotType IN (:slotTypes) " +
                        " AND ps.isAvailable=TRUE ORDER BY ps.parkingSlotType LIMIT 1",
                ParkingSpace.class);
        query.setParameter("parkingLotID", parkingLotID);
        query.setParameter("slotTypes", List.of(SlotType.SMALL,SlotType.MEDIUM,SlotType.LARGE));
        return query.getSingleResult();
    }
}
