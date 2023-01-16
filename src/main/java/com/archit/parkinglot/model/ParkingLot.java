package com.archit.parkinglot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ParkingLot {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer parkingLotId;

    private String parkingLotName;

    @Getter
    @Setter
    //SINCE THIS IS NON OWNING SIDE OF THE RELATION (NO COLUMN IN ParkingLot TABLE) HENCE MAPPEDBY
    @OneToMany(mappedBy = "parkingLot")
    private List<ParkingSpace> parkingSpaces = new ArrayList<>();

    @Override
    public String toString() {
        return "ParkingLot{" +
                "parkingLotId=" + parkingLotId +
                ", parkingLotName='" + parkingLotName + '\'' +
                ", parkingSpaces=" + parkingSpaces +
                '}';
    }
}

