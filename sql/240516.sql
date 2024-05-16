CREATE TABLE MEMBER (
	userno			NUMBER(7) ,
	userid 			VARCHAR2(30) PRIMARY KEY ,
	userpw 			VARCHAR2(30) NOT NULL ,
	username 		VARCHAR2(30) NOT NULL ,
	email			VARCHAR2(100) NOT NULL UNIQUE ,
	postcode		NUMBER(5) NOT NULL ,
	address			VARCHAR2(200) NOT NULL ,
	address_detail 	VARCHAR2(200) ,
	birth			DATE
);

SELECT * FROM MEMBER;

CREATE SEQUENCE member_seq START WITH 1 INCREMENT BY 1 MAXVALUE 9999999 CYCLE NOCACHE;

INSERT INTO MEMBER (userno, userid, userpw, username, email, postcode, address, address_detail, birth)
VALUES (member_seq.nextval, 'userID1', '1234', 'username1', 'uesr1@example.com', '12345', '서울 종로구 종로1길 20', 'asdf', sysdate);
VALUES (member_seq.nextval, 'userID2', '1234', 'username2', 'uesr2@example.com', '12345', '서울 종로구 종로1길 50', 'asdf', sysdate);
VALUES (member_seq.nextval, 'userID3', '1234', 'username3', 'uesr3@example.com', '12345', '서울 종로구 종로3길 17', 'asdf', sysdate);

DELETE FROM MEMBER WHERE userno IS NULL;
DROP TABLE MEMBER;


CREATE TABLE BOARD (
	boardno			NUMBER(10) PRIMARY KEY ,
	subject			VARCHAR2(50) NOT NULL ,
	content 		VARCHAR2(4000) NOT NULL ,
	userid			VARCHAR2(30) NOT NULL ,
	username 		VARCHAR2(30) NOT NULL ,
	regdate			DATE DEFAULT sysdate NOT NULL ,
	hit 			NUMBER(10) DEFAULT 0 NOT NULL
);

CREATE SEQUENCE board_seq START WITH 1 INCREMENT BY 1 MAXVALUE 9999999 CYCLE NOCACHE;

SELECT * FROM board;

INSERT INTO BOARD VALUES(board_seq.nextval, 'subject01', 'content01', 'userID1', 'username1', sysdate, 0);
INSERT INTO BOARD VALUES(board_seq.nextval, 'subject02', 'content02', 'userID1', 'username1', sysdate, 0);
INSERT INTO BOARD VALUES(board_seq.nextval, 'subject03', 'content03', 'userID2', 'username2', sysdate, 0);

UPDATE board SET hit = hit + 1 WHERE boardNo = 10;

DROP TABLE board;