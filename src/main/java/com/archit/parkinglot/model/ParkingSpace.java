package com.archit.parkinglot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class ParkingSpace {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long parkingSpaceId;

    private Short parkingSpot;

    private Short floor;

    @OneToOne
    private ParkingSlotType parkingSlotType;

    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ParkingLot parkingLot;

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "parkingSpot=" + parkingSpot +
                ", floor=" + floor +
                ", parkingSlotType=" + parkingSlotType +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
