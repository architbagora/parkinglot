INSERT INTO PARKING_SLOT_TYPE(ID,SLOT_TYPE) VALUES (1,'SMALL');
INSERT INTO PARKING_SLOT_TYPE(ID,SLOT_TYPE) VALUES (2,'MEDIUM');
INSERT INTO PARKING_SLOT_TYPE(ID,SLOT_TYPE) VALUES (3,'LARGE');
INSERT INTO PARKING_SLOT_TYPE(ID,SLOT_TYPE) VALUES (4,'XLARGE');


INSERT INTO PARKING_LOT(ID, PARKING_LOT_NAME) VALUES (1001,'BT parking lot');
INSERT INTO PARKING_LOT(ID, PARKING_LOT_NAME) VALUES (1002,'LOWES parking lot');
INSERT INTO PARKING_LOT(ID, PARKING_LOT_NAME) VALUES (1003,'WALMART parking lot');



INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(101,3,12,true,1,1001);
INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(102,3,13,true,2,1001);
INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(103,3,14,true,4,1001);
INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(104,3,15,true,3,1001);

INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(105,2,12,true,1,1001);
INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(106,2,13,true,2,1001);
INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(107,2,14,true,4,1001);
INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(108,2,15,true,3,1001);


INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(109,3,12,true,1,1002);
INSERT INTO PARKING_SPACE(ID,FLOOR,PARKING_SPOT,IS_AVAILABLE,PARKING_SLOT_TYPE_ID ,PARKING_LOT_ID) VALUES(1010,3,12,true,1,1003);

