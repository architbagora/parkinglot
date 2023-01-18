package com.archit.parkinglot.model.request;


import com.archit.parkinglot.model.SlotType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlotCount {
    private Integer count;
    private SlotType slotType;
}