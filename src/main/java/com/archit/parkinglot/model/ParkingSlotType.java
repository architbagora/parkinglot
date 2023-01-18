package com.archit.parkinglot.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private short slotId;
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private SlotType slotType;
}
