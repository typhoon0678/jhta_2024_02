-- 제약사항 (not null, unique ...)
CREATE TABLE user_table (
	userid 		varchar2(20) NOT NULL,
	userpw		varchar2(20) NOT NULL,
	cp			varchar2(20)
);

SELECT * FROM user_table;
INSERT INTO user_table (userid, userpw, cp) VALUES ('jjang051', '1234', 010-1234-5678);

UPDATE user_table SET userid = NULL WHERE userid = 'jjang051;'

SELECT * FROM user_constraints;  


DROP TABLE user_table CASCADE CONSTRAINTS;
CREATE TABLE user_table (
	userid		varchar2(20) CONSTRAINT user_table_userid_not_null NOT NULL,
	userpw		varchar2(20) CONSTRAINT user_table_userpw_not_null NOT NULL,
	cp			varchar2(20)
);

ALTER TABLE user_table MODIFY(cp CONSTRAINT user_table_cp_not_null NOT NULL);



DROP TABLE table_unique CASCADE CONSTRAINTS;
CREATE TABLE table_unique (
	userid		varchar2(20) UNIQUE,
	userpw		varchar2(20) NOT NULL,
	cp			varchar2(20)
);

INSERT INTO table_unique (userid, userpw, cp) VALUES ('jjang-051', '1234', '010-1111-1111');
INSERT INTO table_unique (userid, userpw, cp) VALUES (NULL, '1234', '010-1111-1111');

SELECT * FROM table_unique;

UPDATE table_unique SET userid = 'jjang'


DROP TABLE table_primary CASCADE CONSTRAINTS;
CREATE TABLE table_primary (
	userid		varchar2(20) UNIQUE,
	userpw		varchar2(20) NOT NULL,
	cp			varchar2(20)
	PRIMARY KEY ( userid ),
	UNIQUE (cp)
);


INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, deptno)
VALUES  (9999, 'jjang051', 'anager', '1111', '2024-05-03', 3400, 87);


CREATE TABLE fk_dept (
	deptno		number(2) CONSTRAINT fk_dept_pk PRIMARY KEY,
	dname		varchar2(20),
	loc			varchar2(20)
);

DROP TABLE fk_emp;
CREATE TABLE fk_emp (
	empno		number(4) CONSTRAINT fk_emp_pk PRIMARY KEY,
	ename		varchar2(20),
	job			varchar2(20),
	manager 	number(4),
	hiredate	DATE,
	sal			number(7, 2),
	comm 		number(7, 2),
	deptno 		number(2) CONSTRAINT fk_emp_foreignkey REFERENCES fk_dept (deptno) ON DELETE CASCADE
);

INSERT INTO fk_dept values(50, 'database', 'jongro');

INSERT INTO fk_emp values(9999, 'jjang051', 'manager', 1234, sysdate, 3500, NULL, 50);

SELECT * FROM fk_dept;
SELECT * FROM fk_emp;

DELETE FROM fk_dept WHERE deptno = 50;


create TABLE check_table (
	userid 		varchar2(20) CONSTRAINT check_table_pk PRIMARY KEY,
	userpw 		varchar2(20) CONSTRAINT check_table_ck CHECK (LENGTH(userpw) > 8),
	cp 			varchar2(13)
);


INSERT INTO check_table VALUES ('jjang051', '123456789', '010-1111-2222');
SELECT * FROM check_table;

CREATE TABLE default_table (
	userid 		varchar2(20) CONSTRAINT default_table_pk PRIMARY KEY,
	userpw 		varchar2(20) DEFAULT '1234',
	cp 			varchar2(13) DEFAULT '010-0000-0000'
);

INSERT INTO default_table values('jjang051', NULL, NULL);
INSERT INTO default_table (userid) values('jjang052');
SELECT * FROM default_table;


-- Problem 1
DROP TABLE dept_const;
CREATE TABLE dept_const (
	deptno 		number(2) CONSTRAINT deptconst_deptno_pk PRIMARY KEY,
	dname 		varchar2(14) CONSTRAINT deptconst_dname_unq UNIQUE,
	loc 		varchar2(13) CONSTRAINT deptconst_loc_nn NOT NULL
);

CREATE TABLE emp_const (
	empno 		number(4) CONSTRAINT empconst_empno_pk PRIMARY KEY,
	ename 		varchar2(10) CONSTRAINT empconst_ename_nn NOT NULL,
	job 		varchar2(9),
	tel 		varchar2(20) CONSTRAINT empconst_tel_unq UNIQUE,
	hiredate 	DATE,
	sal 		number(7, 2) CONSTRAINT empconst_sal_chk CHECK(sal BETWEEN 1000 AND 9999),
	comm 		number(7, 2),
	deptno 		number(2) CONSTRAINT empconst_deptno_fk REFERENCES dept_const (deptno)
);


-- Problem 2
SELECT * FROM user_constraints ORDER BY last_change desc;