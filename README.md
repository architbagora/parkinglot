# parkinglot

1) 95% coverage with integration test cases.
2) All scenarios mentioned with condition are handled.
3) All Exceptions are handled in Controller with appropriate response contract 
4) This project used in-mem H2 DB so can checkout and literally run it in few mins.
5) Solution is thread safe in horizontal scaling scenario too(Have test case to test same "testBookParkingSlotRaceCondition").
6) All the endpoint are added curls below
      Create parking Lot : http://localhost:8080/parkinglot  
               "curl -X POST \
                  http://localhost:8080/parkinglot \
                  -H 'cache-control: no-cache' \
                  -H 'content-type: application/json' \
                  -H 'postman-token: cc317d15-d7ed-a3cc-bbd4-e6c572e19de9' \
                  -d '{
                  "parkingLotName": "LOT OF MINE",
                  "floorPlanList": [{
                  "floorNumber": 1,
                  "numberOfSlots": [{
                  "count": 1,
                  "slotType": "MEDIUM"
                  }, {
                  "count": 1,
                  "slotType": "XLARGE"
                  }, {
                  "count": 1,
                  "slotType": "SMALL"
                  }, {
                  "count": 1,
                  "slotType": "LARGE"
                  }]
                  }]
                  }'
               "
   Get a slot:  http://localhost:8080/getslot/{parking_lot_id}/{size}  -> "curl -X GET \
                http://localhost:8080/getslot/1001/SMALL \
                -H 'cache-control: no-cache' \
                -H 'content-type: application/json' \
                -H 'postman-token: c61ca8fe-fe39-ef65-d5ab-9e71bf64b90c'"
         
   Release a slot :  http://localhost:8080/freeslot/{parking_lot_id}/{floor}/{parkingspot} -> "curl -X GET \
       http://localhost:8080/freeslot/1001/3/13 \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: a974cb56-7147-d52c-1b26-a1abe9a181d7'"
        

# DB Selection analysis
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
