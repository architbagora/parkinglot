# parkinglot
Selecting a basic SQL DB based on below assumptions

Memory estimation :
Most of memory will be taken by the parking spot details in the system.


PARKING_SPOT_ID -> BIGINT 8 bytes
parkingSpot -> smallint UNSIGNED (Range  0 - 65535)  2 Bytes
floor -> tinyint UNSIGNED (Range 0 - 255) 1 Byte
ParkingLotId (FK) -> Int (Range -2147483648 to 2147483647) 4 bytes
slotType -> tinyint UNSIGNED (Range 0 - 255) 1 Byte
isAvailable -> Boolean 1 Byte

total : 16 Byte

1 week 100 spaces added with 1000 spots will create
52 * 100 * 1000 * 16  = 26000000 bytes  = 83.2 MB /Year
Over an year.

This assumption of 10 mil will take 1923 ((10mil/100)/52) years to reach which is mentioned in req.
10 Million * 1000 (Spots) * 16 byte  = 160 GB memory will be occupied by the system at that time.
