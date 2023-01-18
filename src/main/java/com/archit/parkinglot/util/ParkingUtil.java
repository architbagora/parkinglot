package com.archit.parkinglot.util;

import com.archit.parkinglot.model.ParkingSlotType;
import com.archit.parkinglot.model.SlotType;

import java.util.ArrayList;
import java.util.List;

public class ParkingUtil {

    static final ParkingSlotType smallParkingSLotType = new ParkingSlotType(Short.parseShort("1"), SlotType.SMALL);
    static final ParkingSlotType mediumParkingSLotType = new ParkingSlotType(Short.parseShort("2"), SlotType.MEDIUM);
    static final ParkingSlotType largeParkingSLotType = new ParkingSlotType(Short.parseShort("3"), SlotType.LARGE);
    static final ParkingSlotType xlargeParkingSLotType = new ParkingSlotType(Short.parseShort("4"), SlotType.XLARGE);


    public static List<SlotType> findApplicableSlots(SlotType slotType) {
        List<SlotType> applicableSlotTypes = new ArrayList<>();
        boolean isApplicable = false;
        if(SlotType.SMALL.equals(slotType)){
            applicableSlotTypes.add(SlotType.SMALL);
            isApplicable =true;
        }
        if(SlotType.MEDIUM.equals(slotType) || isApplicable){
            applicableSlotTypes.add(SlotType.MEDIUM);
            isApplicable =true;
        }
        if(SlotType.LARGE.equals(slotType) || isApplicable){
            applicableSlotTypes.add(SlotType.LARGE);
            isApplicable =true;
        }
        if(SlotType.XLARGE.equals(slotType) || isApplicable){
            applicableSlotTypes.add(SlotType.XLARGE);
        }
        return applicableSlotTypes;
    }


    public static ParkingSlotType findSlotType(SlotType slotType) {
        switch (slotType) {
            case SMALL:
                return smallParkingSLotType;
            case MEDIUM:
                return mediumParkingSLotType;
            case LARGE:
                return largeParkingSLotType;
            case XLARGE:
                return xlargeParkingSLotType;
        }
        return null;
    }
}
