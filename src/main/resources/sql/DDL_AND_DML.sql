DROP TABLE OBJTYPE CASCADE CONSTRAINTS;
DROP TABLE ATTRTYPE CASCADE CONSTRAINTS;
DROP TABLE LISTS CASCADE CONSTRAINTS;
DROP TABLE OBJECTS CASCADE CONSTRAINTS;
DROP TABLE ATTRIBUTES CASCADE CONSTRAINTS;
DROP TABLE OBJREFERENCE CASCADE CONSTRAINTS;

/*������� �������� ��������� ����� */

CREATE TABLE OBJTYPE (
	OBJECT_TYPE_ID NUMBER(20),
	PARENT_ID      NUMBER(20),
	NAME           VARCHAR2(200),
	DESCRIPTION    VARCHAR2(1000)
);

ALTER TABLE OBJTYPE ADD CONSTRAINT OBJTYPE_PK PRIMARY KEY (OBJECT_TYPE_ID);
ALTER TABLE OBJTYPE ADD CONSTRAINT OBJTYPE_NAME_UNIQUE UNIQUE (NAME);
ALTER TABLE OBJTYPE MODIFY (NAME NOT NULL);
ALTER TABLE OBJTYPE ADD CONSTRAINT OBJTYPE_FK FOREIGN KEY (PARENT_ID) REFERENCES OBJTYPE(OBJECT_TYPE_ID);



/*������� �������� ���������� ����� */
CREATE TABLE ATTRTYPE (
    ATTR_ID      		NUMBER(20),
    OBJECT_TYPE_ID 		NUMBER(20),
    NAME         		VARCHAR2(200)
);

ALTER TABLE ATTRTYPE ADD CONSTRAINT ATTRTYPE_PK PRIMARY KEY (ATTR_ID);
ALTER TABLE ATTRTYPE MODIFY (NAME NOT NULL);
ALTER TABLE ATTRTYPE ADD CONSTRAINT ATTRTYPE_OBJECT_TYPE_ID_FK FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID);



/*������� ��� �������� �������� �������� */
CREATE TABLE LISTS (
	ATTR_ID NUMBER(10),
	LIST_VALUE_ID NUMBER(10),
	VALUE VARCHAR(4000)
);

ALTER TABLE LISTS ADD CONSTRAINT LISTS_PK PRIMARY KEY (LIST_VALUE_ID);
ALTER TABLE LISTS ADD CONSTRAINT LISTS_ATTR_ID_FK FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE(ATTR_ID);


/* ������� �������� ����������� �������� */
CREATE TABLE OBJECTS (
	OBJECT_ID      NUMBER(20),
	PARENT_ID      NUMBER(20),
	OBJECT_TYPE_ID NUMBER(20),
	NAME           VARCHAR2(2000),
	DESCRIPTION    VARCHAR2(4000)
);

ALTER TABLE OBJECTS ADD CONSTRAINT OBJECTS_PK PRIMARY KEY (OBJECT_ID);
ALTER TABLE OBJECTS MODIFY (NAME NOT NULL);
ALTER TABLE OBJECTS ADD CONSTRAINT OBJECTS_PARENT_ID_FK FOREIGN KEY (PARENT_ID) REFERENCES OBJECTS (OBJECT_ID);
ALTER TABLE OBJECTS ADD CONSTRAINT OBJECTS_OBJECT_TYPE_ID_FK FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID);


/* ������� �������� �������� ��������� ����������� �������� */
CREATE TABLE ATTRIBUTES (
	ATTR_ID NUMBER(10),
	OBJECT_ID NUMBER(20),
	VALUE VARCHAR2(4000),
	DATE_VALUE DATE,
	LIST_VALUE_ID NUMBER(10)
);

ALTER TABLE ATTRIBUTES ADD CONSTRAINT ATTRIBUTES_PK PRIMARY KEY (ATTR_ID, OBJECT_ID);
ALTER TABLE ATTRIBUTES ADD CONSTRAINT ATTRIBUTES_LIST_VALUE_ID_FK FOREIGN KEY (LIST_VALUE_ID) REFERENCES LISTS (LIST_VALUE_ID);
ALTER TABLE ATTRIBUTES ADD CONSTRAINT ATTRIBUTES_ATTR_ID_FK FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID);
ALTER TABLE ATTRIBUTES ADD CONSTRAINT ATTRIBUTES_OBJECT_ID_FK FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS (OBJECT_ID);


/*������� �������� ������ "����������� ����������" ����� ������������ �������� */
CREATE TABLE OBJREFERENCE (
	ATTR_ID   NUMBER(20),
	REFERENCE NUMBER(20),
	OBJECT_ID NUMBER(20)
);

ALTER TABLE OBJREFERENCE ADD CONSTRAINT OBJREFERENCE_PK PRIMARY KEY (ATTR_ID,REFERENCE,OBJECT_ID);
ALTER TABLE OBJREFERENCE ADD CONSTRAINT OBJREFERENCE_REFERENCE_FK FOREIGN KEY(REFERENCE) REFERENCES OBJECTS(OBJECT_ID);
ALTER TABLE OBJREFERENCE ADD CONSTRAINT OBJREFERENCE_OBJECT_ID_FK FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS(OBJECT_ID);
ALTER TABLE OBJREFERENCE ADD CONSTRAINT OBJREFERENCE_ATTR_ID_FK FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID);

--������������������
DROP SEQUENCE LIST_VALUE_ID_SEQ;
DROP SEQUENCE OBJECT_ID_SEQ;
DROP SEQUENCE ATTR_ID_SEQ;
DROP SEQUENCE OBJECT_TYPE_ID_SEQ;

CREATE SEQUENCE OBJECT_TYPE_ID_SEQ START WITH 1;
CREATE SEQUENCE ATTR_ID_SEQ START WITH 1;
CREATE SEQUENCE OBJECT_ID_SEQ START WITH 1;
CREATE SEQUENCE LIST_VALUE_ID_SEQ START WITH 1;

ALTER SESSION SET NLS_DATE_FORMAT = 'dd/mm/yyyy hh24:mi';

INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, NULL, 'Assembly', NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID, PARENT_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, NULL, 'Motherboard', NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, NULL, 'Cpu', NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, NULL, 'Gpu', NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, NULL, 'Ram', NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, 1, 'Overclock', NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, NULL, 'User', NULL);
INSERT INTO OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, 1, 'Comment', NULL);

--------------------------------------------------------------------
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Author_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Overclock_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Motherboard_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Cpu_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Gpu_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Ram_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Comments');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,1,'Score');

INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,2,'Chipset_manufacturer'); --11
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,2,'Chipset');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,2,'Socket'); --13

INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Manufacturer'); --15
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Family');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Socket');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Generation'); --18
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Number_of_cores');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Number_of_threads');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Frequency');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,3,'Voltage');

INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,4,'Chip_manufacturer'); --24
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,4,'Chip'); --25
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,4,'Memory_capacity'); 
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,4,'Core_frequency');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,4,'Memory_frequency');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,4,'Voltage');

INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,5,'Capacity');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,5,'Frequency');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,5,'Timings');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,5,'Voltage');


INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Cpu_frequency');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Cpu_voltage');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Gpu_core_frequency');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Gpu_memory_frequency');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Gpu_voltage');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Ram_voltage');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Ram_timings');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,6,'Ram_frequency');

INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,7,'Is_Active');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,7,'Password');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,7,'Email');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,7,'Registration_date');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,7,'Role');

INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,8,'Message');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,8,'Author_id');
INSERT INTO ATTRTYPE (ATTR_ID,OBJECT_TYPE_ID,NAME)
	VALUES (ATTR_ID_SEQ.NEXTVAL,8,'Date_of_comment');

--------------------------------------------------------------------

--Motherboard chipset manufacturer
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(9, LIST_VALUE_ID_SEQ.NEXTVAL, 'Intel');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(9, LIST_VALUE_ID_SEQ.NEXTVAL, 'AMD');

--Motherboard chipset
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'H310');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'B360');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'Z390');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'H410');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'B460');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'Z490');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'H510');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'B560');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'Z590');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'A320');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'B350');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'X370');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'B450');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'X470');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'A520');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'B550');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(10, LIST_VALUE_ID_SEQ.NEXTVAL, 'X570');

--Motherboard socket
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(11, LIST_VALUE_ID_SEQ.NEXTVAL, 'Soc1151');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(11, LIST_VALUE_ID_SEQ.NEXTVAL, 'Soc1200');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(11, LIST_VALUE_ID_SEQ.NEXTVAL, 'AM4');

--CPU manufacturer
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(12, LIST_VALUE_ID_SEQ.NEXTVAL, 'Intel');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(12, LIST_VALUE_ID_SEQ.NEXTVAL, 'AMD');

--CPU family
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Corei3');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Corei5');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Corei7');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Corei9');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Ryzen3');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Ryzen5');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Ryzen7');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(13, LIST_VALUE_ID_SEQ.NEXTVAL, 'Ryzen9');

--CPU socket
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(14, LIST_VALUE_ID_SEQ.NEXTVAL, 'Soc1151');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(14, LIST_VALUE_ID_SEQ.NEXTVAL, 'Soc1200');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(14, LIST_VALUE_ID_SEQ.NEXTVAL, 'AM4');

--CPU generation
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'EightGenIntel');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'NineGenIntel');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'TenGenIntel');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'ElevenGenIntel');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'Zen');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'ZenPlus');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'ZenTwo');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(15, LIST_VALUE_ID_SEQ.NEXTVAL, 'ZenThree');

--GPU chip manufacturer
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(20, LIST_VALUE_ID_SEQ.NEXTVAL, 'Nvidia');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(20, LIST_VALUE_ID_SEQ.NEXTVAL, 'AMD');

--GPU chip
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GT_1030');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1050');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1050_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1060');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1070');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1070_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1080');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1080_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1650');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1650_Super');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1660');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1660_Super');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_GTX_1660_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_2060');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_2060_Super');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_2070');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_2070_Super');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_2080');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_2080_Super');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_2080_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_3060');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_3060_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_3070');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_3070_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_3080');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_3080_Ti');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'GeForce_RTX_3090');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_550');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_560');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_570');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_590');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_5500_XT');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_5600_XT');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_5700');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_5700_XT');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_6600');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_6600_XT');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_6700_XT');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_6800');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_6800_XT');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(21, LIST_VALUE_ID_SEQ.NEXTVAL, 'RX_6900_XT');

INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(42, LIST_VALUE_ID_SEQ.NEXTVAL, 'User');
INSERT INTO LISTS(ATTR_ID, LIST_VALUE_ID, VALUE)
	VALUES(42, LIST_VALUE_ID_SEQ.NEXTVAL, 'Admin');

--Assemblies
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL, 1, 'Assembly1', NULL);
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, NULL, 1, 'Assembly2', NULL);
	

--Motherboards
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, NULL, 2, 'Asus Prime B560M-A',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, NULL, 2, 'Gigabyte B450 Aorus Pro',NULL);

--CPU's
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,3,'Intel Core i7',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,3,'AMD Ryzen 3',NULL);

--GPU's
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,4,'Asus ROG STRIX GTX1080 A8G GAMING',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,4,'MSI PCI-Ex Radeon RX 6900 XT Gaming',NULL);

--RAM's
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,5,'HyperX DDR4-2666 16384MB PC4-21300',NULL);
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL,5,'DDR4 RAM 8GB AMD 2400 MHz Radeon R7 Performance',NULL);

--Overclock
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,1,6,'overclock1',NULL);

--Users
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL, 7, 'user1', NULL);
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, NULL, 7, 'admin1', NULL);

--Comments
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, 1, 8, 'Comment1', NULL);
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, 1, 8, 'Comment2', NULL);

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, 2, 8, 'Comment3', NULL);
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, 2, 8, 'Comment4', NULL);
	
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,NULL, 1, 'Assembly3', NULL);
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL, NULL, 1, 'Assembly4', NULL);

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
	VALUES (OBJECT_ID_SEQ.NEXTVAL,18,6,'overclock2',NULL);

--Motherboard
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(9, 3, 1);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(10, 3, 10);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(11, 3, 21);

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(9, 4, 2);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(10, 4, 15);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(11, 4, 22);


--CPU
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(12, 5, 23);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(13, 5, 27);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(14, 5, 34);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(15, 5, 38);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(16, 5, '8');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(17, 5, '16');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(18, 5, '3.8');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(19, 5, '1.1');

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(12, 6, 24);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(13, 6, 31);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(14, 6, 35);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(15, 6, 41);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(16, 6, '3');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(17, 6, '6');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(18, 6, '3.2');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(19, 6, '1.3');

--GPU
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(20, 7, 44);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(21, 7, 52);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(22, 7, '8');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(23, 7, '1695');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(24, 7, '10010');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(25, 7, '2.1');

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(20, 8, 47);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(21, 8, 86);
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(22, 8, '16');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(23, 8, '1800');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(24, 8, '16000');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(25, 8, '1.6');

--RAM
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(26, 9, '16');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(27, 9, '2666');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(28, 9, '16-18-18-36');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(29, 9,'1.2');

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(26, 10, '8');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(27, 10, '2400');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(28, 10, '15-16-16-32');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(29, 10, '1.2');

--Overclock
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(30, 11, '5.0');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(31, 11, '2.1');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(32, 11, '1100');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(33, 11, '5000');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(34, 11, '2.1');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(35, 11, '1.4');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(36, 11, '20-22-22-44');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(37, 11, '4400');
	
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(30, 20, '5.0');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(31, 20, '2.1');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(32, 20, '1100');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(33, 20, '5000');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(34, 20, '2.1');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(35, 20, '1.4');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(36, 20, '20-20-20-40');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(37, 20, '4400');

--USER
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(38, 12, '1');
-- password1
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(39, 12, '114835e56fb4f7c52d055108a6d293b3d8e7188b114cfbe70479efa9156598253d83ec49894b08b2');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(40, 12, 'user1@gmail.com');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
	VALUES(41, 12, '12/10/2021 00:00');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(42, 12, 87);

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(38, 13, '1');
-- password2
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(39, 13, '94adc62a482ac32e635b108c3eef3dec1870a0b71bee88e86eeb57a3c6ebc4bda35cb02677f3596c');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(40, 13, 'admin1@gmail.com');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
	VALUES(41, 13, '12/10/2021 00:00');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, LIST_VALUE_ID)
	VALUES(42, 13, 88);


--Comment
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(43, 14, 'Good!');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
	VALUES(45, 14, '12/10/2021 17:34');

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(43, 15, 'OMG!');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
	VALUES(45, 15, '12/10/2021 17:34');

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(43, 16, 'Hello world');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
	VALUES(45, 16, '10/08/2020 10:10');
	
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(43, 17, 'Cool assembly2!');
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, DATE_VALUE)
	VALUES(45, 17, '14/12/2019 13:21');

--Assembly
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)	
	VALUES(8, 1, '3500');

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(8, 2, '1500');
	
INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)	
	VALUES(8, 18, '6000');

INSERT INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
	VALUES(8, 19, '1600');



--OBJREFERENCE
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (1, 12, 1);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (2, 11, 1);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (3, 3, 1);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (4, 5, 1);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (5, 7, 1);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (6, 9, 1);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (7, 14, 1);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (7, 15, 1);

INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (1, 12, 2);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (3, 4, 2);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (4, 6, 2);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (5, 8, 2);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (6, 10, 2);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (7, 16, 2);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (7, 17, 2);

INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (1, 12, 18);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (2, 20, 18);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (3, 3, 18);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (4, 5, 18);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (5, 7, 18);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (6, 9, 18);

INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (1, 12, 19);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (3, 3, 19);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (4, 5, 19);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (5, 7, 19);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (6, 9, 19);

INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (44, 12, 14);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (44, 12, 15);

INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (44, 12, 16);
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE) VALUES (44, 12, 17);

--password reset token
INSERT INTO OBJTYPE (OBJECT_TYPE_ID, PARENT_ID, NAME, DESCRIPTION)
    VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, 7, 'ResetPasswordToken', NULL);

INSERT INTO ATTRTYPE (ATTR_ID, OBJECT_TYPE_ID, NAME)
    VALUES (ATTR_ID_SEQ.NEXTVAL, 9, 'resetPasswordToken');

--refresh token
INSERT INTO OBJTYPE (OBJECT_TYPE_ID, PARENT_ID, NAME, DESCRIPTION)
    VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, 7, 'RefreshToken', NULL);

INSERT INTO ATTRTYPE (ATTR_ID, OBJECT_TYPE_ID, NAME)
VALUES (ATTR_ID_SEQ.NEXTVAL, 10, 'refreshToken');

--activate account token
INSERT INTO OBJTYPE (OBJECT_TYPE_ID, PARENT_ID, NAME, DESCRIPTION)
VALUES (OBJECT_TYPE_ID_SEQ.NEXTVAL, 7, 'ActivateAccountToken', NULL);

INSERT INTO ATTRTYPE (ATTR_ID, OBJECT_TYPE_ID, NAME)
VALUES (ATTR_ID_SEQ.NEXTVAL, 11, 'activateAccountToken');
