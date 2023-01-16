package com.archit.parkinglot.model;


import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ParkingSlotType {
    @Id
    @Column(name = "id")
    private short slotId;
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private SlotType slotType;
}
