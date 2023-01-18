package com.archit.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ErrorResponse implements Serializable {

    private HttpStatus status;
    private String message;
    private Integer parkingLot;
    private SlotType slotType;
}
