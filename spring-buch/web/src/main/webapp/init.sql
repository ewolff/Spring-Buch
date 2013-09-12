DROP TABLE DUAL_HIBERNATE_SEQUENCE IF EXISTS
DROP TABLE BESTELLUNGDETAIL IF EXISTS
DROP TABLE BESTELLUNG_BESTELLPOSITION IF EXISTS
DROP TABLE BESTELLPOSITION IF EXISTS
DROP TABLE BESTELLUNG IF EXISTS
DROP TABLE KUNDE IF EXISTS
DROP TABLE WARE IF EXISTS
DROP TABLE SEQUENCE IF EXISTS

CREATE MEMORY TABLE SEQUENCE(SEQ_NAME VARCHAR(50) NOT NULL PRIMARY KEY,SEQ_COUNT NUMERIC(38))
CREATE MEMORY TABLE BESTELLUNG(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,ID_KUNDE INTEGER)
CREATE MEMORY TABLE BESTELLUNGDETAIL(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,ANZAHL INTEGER,ID_WARE INTEGER,ID_BESTELLUNG INTEGER,CONSTRAINT FK9F09B39ECEA19CB8 FOREIGN KEY(ID_BESTELLUNG) REFERENCES BESTELLUNG(ID))
CREATE MEMORY TABLE KUNDE(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,VORNAME VARCHAR(255),NAME VARCHAR(255),KONTOSTAND DOUBLE)
CREATE MEMORY TABLE WARE(ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,BEZEICHNUNG VARCHAR(255),PREIS DOUBLE)
CREATE MEMORY TABLE DUAL_HIBERNATE_SEQUENCE(ZERO INTEGER)
INSERT INTO SEQUENCE VALUES('SEQ_GEN',0)
