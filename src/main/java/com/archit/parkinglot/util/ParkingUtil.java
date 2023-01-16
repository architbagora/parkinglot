package com.archit.parkinglot.util;

import com.archit.parkinglot.model.ParkingSlotType;
import com.archit.parkinglot.model.SlotType;

import java.util.ArrayList;
import java.util.List;

public class ParkingUtil {

    public static List<SlotType> findApplicableSlots(SlotType slotType) {
        List<SlotType> applicableSlotTypes = new ArrayList<>();
        switch (slotType) {
            case SMALL -> applicableSlotTypes.add(SlotType.SMALL);
            case MEDIUM -> applicableSlotTypes.add(SlotType.MEDIUM);
            case LARGE -> applicableSlotTypes.add(SlotType.LARGE);
            case XLARGE -> applicableSlotTypes.add(SlotType.XLARGE);
        }
        return applicableSlotTypes;
    }


}
