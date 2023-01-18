package com.archit.parkinglot;

import com.archit.parkinglot.model.ParkingSpace;
import com.archit.parkinglot.model.SlotType;
import com.archit.parkinglot.model.request.CreateParkingLotReq;
import com.archit.parkinglot.model.request.FloorPlan;
import com.archit.parkinglot.model.request.SlotCount;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ParkinglotApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    EntityManager entityManager;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    @DirtiesContext
    public void testBookParkingSlot() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/getslot/1001/SMALL", HttpMethod.GET,
                new HttpEntity<String>(null, headers), String.class);
        String actual = response.getBody();
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals("{\"parkingSpot\":12,\"floor\":3,\"parkingSlotType\":{\"slotType\":\"SMALL\"}}", actual);
    }

    @Test
    public void testBookParkingSlotParkingIsFull() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/getslot/1002/MEDIUM", HttpMethod.GET,
                new HttpEntity<String>(null, headers), String.class);
        String actual = response.getBody();
        Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        Assertions.assertEquals("{\"status\":\"NOT_FOUND\",\"" +
                "message\":\"No parking space found for slot typeMEDIUM\",\"" +
                "parkingLot\":1002,\"" +
                "slotType\":\"MEDIUM\"}", actual);
    }

    @Test
    public void testBookParkingSlotDoesntExist() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/getslot/100500/SMALL", HttpMethod.GET,
                new HttpEntity<String>(null, headers), String.class);
        String actual = response.getBody();
        Assertions.assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        Assertions.assertEquals("{\"" +
                "status\":\"NOT_FOUND\",\"" +
                "message\":\"No parking space found for slot typeSMALL\",\"" +
                "parkingLot\":100500,\"" +
                "slotType\":\"SMALL\"}", actual);
    }

    @Test
    @DirtiesContext
    public void testBookParkingSlotRaceCondition() throws Exception {
        for (int i = 0; i < 8; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ResponseEntity<String> response = restTemplate.exchange(
                            "http://localhost:" + port + "/getslot/1001/SMALL", HttpMethod.GET,
                            new HttpEntity<String>(null, headers), String.class);

                    if (!response.getStatusCode().is2xxSuccessful()) {
                        log.error("FAILED {} response {}", Thread.currentThread(), response.getBody());
                    } else {
                        log.info("SUCCESS " + Thread.currentThread() + " RESPONSE " + response.getBody());
                    }
                }
            }
            ).start();
        }
        Thread.sleep(2000);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/getslot/1001/SMALL", HttpMethod.GET,
                new HttpEntity<String>(null, headers), String.class);

        Assertions.assertTrue(response.getStatusCode().is4xxClientError());
        Assertions.assertEquals("{\"status\":\"NOT_FOUND\",\"message\":" +
                "\"No parking space found for slot typeSMALL\",\"parkingLot\":1001," +
                "\"slotType\":\"SMALL\"}", response.getBody());
    }

    @Test
    @DirtiesContext
    public void testReleaseParkingSlot() throws Exception {
        ResponseEntity<ParkingSpace> response = restTemplate.exchange(
                "http://localhost:" + port + "/getslot/1001/SMALL", HttpMethod.GET,
                new HttpEntity<String>(null, headers), ParkingSpace.class);
        ParkingSpace acquiredParkingSpace = response.getBody();
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        ResponseEntity<String> result = restTemplate.exchange(
                "http://localhost:" + port + "/freeslot/1001/"
                        + acquiredParkingSpace.getFloor() + "/"
                        + acquiredParkingSpace.getParkingSpot()
                , HttpMethod.GET,
                new HttpEntity<String>(null, headers), String.class);

        Assertions.assertEquals("Slot " + acquiredParkingSpace.getParkingSpot()
                + " Is free on floor " + acquiredParkingSpace.getFloor()
                + " in 1001 parkinglot", result.getBody());
    }

    @Test
    @DirtiesContext
    public void testBuildParkingSlot() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/parkinglot", HttpMethod.POST,
                new HttpEntity<CreateParkingLotReq>(CreateParkingLotReq.builder()
                        .parkingLotName("My parking slot")
                        .floorPlanList(List.of(FloorPlan.builder().floorNumber((short) 1)
                                .numberOfSlots(Set.of(SlotCount.builder().slotType(SlotType.SMALL).count(2).build(),
                                        SlotCount.builder().slotType(SlotType.MEDIUM).count(2).build(),
                                        SlotCount.builder().slotType(SlotType.LARGE).count(2).build(),
                                        SlotCount.builder().slotType(SlotType.XLARGE).count(2).build()))
                                .build())).build(), headers),
                String.class);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Assertions.assertEquals("Successfully created", response.getBody());

        List<ParkingSpace> parkingSpaceList =
                entityManager.createQuery("SELECT ps FROM ParkingSpace ps WHERE ps.parkingLot.parkingLotId=1", ParkingSpace.class).getResultList();
        Assertions.assertEquals(8, parkingSpaceList.size());
    }

}
