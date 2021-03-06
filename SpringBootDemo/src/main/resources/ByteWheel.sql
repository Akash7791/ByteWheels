CREATE TABLE BYTEWHEELS(
    ID VARCHAR2(5) NOT NULL,
    NAME VARCHAR2(50),
    CATEGORY VARCHAR2(50),
    RATE INT
);

INSERT ALL
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W1','Ford Fiesta','Compact',20)
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W2','Ford Focus','Compact',20)
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W3','Chevrolet Malibu','Full',30)
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W4','Volkswagen Jetta','Full',30)
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W5','Ford Egde','Large',40)
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W6','Ford Escape','Large',40)
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W7','BMW 328i','Luxury',50)
INTO BYTEWHEELS(ID,NAME,CATEGORY,RATE) VALUES ('W8','BMW X5','Luxury',50)
SELECT * FROM DUAL;

commit;

CREATE TABLE USERS(
    ID INT ,
    NAME VARCHAR2(100),
    EMAILID VARCHAR2(50),
    CONTACT_NUMBER INT 
);

ALTER TABLE USERS
 ADD CONSTRAINT users_pk PRIMARY KEY (ID);

CREATE TABLE AVAILWHEELS(
    UNIQUEWID VARCHAR2(5) NOT NULL,
    WNAME VARCHAR2(50),
    WCAT VARCHAR2(50),
    ISAVAIL VARCHAR2(10) DEFAULT 'Y',
    BOOKED_DATE_FROM VARCHAR2(50),
    BOOKED_DATE_TO VARCHAR2(50)
);

INSERT INTO AVAILWHEELS(UNIQUEWID,WNAME,WCAT)
SELECT CONCAT(ID,'_1'),NAME,CATEGORY FROM BYTEWHEELS;

INSERT INTO AVAILWHEELS(UNIQUEWID,WNAME,WCAT)
SELECT CONCAT(ID,'_2'),NAME,CATEGORY FROM BYTEWHEELS;

commit;

CREATE TABLE BOOKWHEEL(
    BID INT NOT NULL,
    BWHEELID VARCHAR2(10),
    BNAME VARCHAR2(50),
    BFROM VARCHAR2(50),
    BTO VARCHAR2(50),
    AMOUNT_TOBE_PAID LONG
);