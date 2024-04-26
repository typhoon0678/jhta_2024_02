ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER scott IDENTIFIED BY tiger;

ALTER USER scott IDENTIFIED BY tiger;

GRANT resource, CONNECT TO scott;

GRANT CONNECT,RESOURCE,UNLIMITED TABLESPACE TO scott;

SELECT * FROM tab;



--------------------------------------------------------------------


DROP TABLE DEPT CASCADE constraints;
CREATE TABLE DEPT
       (DEPTNO NUMBER(2) CONSTRAINT PK_DEPT PRIMARY KEY,
   DNAME VARCHAR2(14) ,
   LOC VARCHAR2(13) );
DROP TABLE EMP  CASCADE constraints;
CREATE TABLE EMP
       (EMPNO NUMBER(4) CONSTRAINT PK_EMP PRIMARY KEY,
   ENAME VARCHAR2(10),
   JOB VARCHAR2(9),
   MGR NUMBER(4),
   HIREDATE DATE,
   SAL NUMBER(7,2),
   COMM NUMBER(7,2),
   DEPTNO NUMBER(2) CONSTRAINT FK_DEPTNO REFERENCES DEPT);
INSERT INTO DEPT VALUES   (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES   (30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES   (40,'OPERATIONS','BOSTON');
INSERT INTO EMP VALUES (7369,'SMITH','CLERK',7902,to_date('17-12-1980','dd-mm-yyyy'),800,NULL,20);
INSERT INTO EMP VALUES (7499,'ALLEN','SALESMAN',7698,to_date('20-2-1981','dd-mm-yyyy'),1600,300,30);
INSERT INTO EMP VALUES (7521,'WARD','SALESMAN',7698,to_date('22-2-1981','dd-mm-yyyy'),1250,500,30);
INSERT INTO EMP VALUES (7566,'JONES','MANAGER',7839,to_date('2-4-1981','dd-mm-yyyy'),2975,NULL,20);
INSERT INTO EMP VALUES (7654,'MARTIN','SALESMAN',7698,to_date('28-9-1981','dd-mm-yyyy'),1250,1400,30);
INSERT INTO EMP VALUES (7698,'BLAKE','MANAGER',7839,to_date('1-5-1981','dd-mm-yyyy'),2850,NULL,30);
INSERT INTO EMP VALUES (7782,'CLARK','MANAGER',7839,to_date('9-6-1981','dd-mm-yyyy'),2450,NULL,10);
INSERT INTO EMP VALUES (7788,'SCOTT','ANALYST',7566,to_date('13-7-1987','dd-mm-yyyy'),3000,NULL,20);
INSERT INTO EMP VALUES (7839,'KING','PRESIDENT',NULL,to_date('17-11-1981','dd-mm-yyyy'),5000,NULL,10);
INSERT INTO EMP VALUES (7844,'TURNER','SALESMAN',7698,to_date('8-9-1981','dd-mm-yyyy'),1500,0,30);
INSERT INTO EMP VALUES (7876,'ADAMS','CLERK',7788,to_date('13-7-1987','dd-mm-yyyy'),1100,NULL,20);
INSERT INTO EMP VALUES (7900,'JAMES','CLERK',7698,to_date('3-12-1981','dd-mm-yyyy'),950,NULL,30);
INSERT INTO EMP VALUES (7902,'FORD','ANALYST',7566,to_date('3-12-1981','dd-mm-yyyy'),3000,NULL,20);
INSERT INTO EMP VALUES (7934,'MILLER','CLERK',7782,to_date('23-1-1982','dd-mm-yyyy'),1300,NULL,10);
DROP TABLE BONUS  CASCADE constraints;
CREATE TABLE BONUS
   (
   ENAME VARCHAR2(10)   ,
   JOB VARCHAR2(9)  ,
   SAL NUMBER,
   COMM NUMBER
   ) ;
DROP TABLE SALGRADE  CASCADE constraints;
CREATE TABLE SALGRADE
      ( GRADE NUMBER,
   LOSAL NUMBER,
   HISAL NUMBER );
INSERT INTO SALGRADE VALUES (1,700,1200);
INSERT INTO SALGRADE VALUES (2,1201,1400);
INSERT INTO SALGRADE VALUES (3,1401,2000);
INSERT INTO SALGRADE VALUES (4,2001,3000);
INSERT INTO SALGRADE VALUES (5,3001,9999);
COMMIT;


-------------------------------


SELECT * FROM emp;
SELECT * FROM emp WHERE empno = 7369;
SELECT * FROM emp WHERE ename = 'SMITH';

SELECT ename AS 이름, sal AS 연봉 FROM emp WHERE sal >= 2000;
SELECT ename AS 이름, sal AS 연봉, sal*1.1 AS "10% 인상된 연봉" FROM emp WHERE sal >= 2000;
SELECT ename AS 이름, sal AS 연봉 FROM emp WHERE sal >= 1000 AND sal <= 2000;
SELECT ename AS 이름, sal AS 연봉 FROM emp WHERE sal BETWEEN 1000 AND 2000;
SELECT ename FROM emp WHERE ename LIKE 'S%';

SELECT CONCAT(ename, job) AS "name and job" FROM emp;
SELECT ename || ' - ' || job AS "name and job" FROM emp;
SELECT ename AS name, sal AS salary, sal*12 + NVL(comm, 0) AS yearSalary FROM emp;
SELECT empno, ename, comm AS commition, NVL2(comm , 'YES', 'NO') AS "COMMITION Y/N" FROM emp; 

SELECT * FROM emp ORDER BY sal DESC, DEPTNO ASC;

-- Practice
SELECT ename, deptno FROM emp WHERE deptno = 30;
SELECT ename, job, deptno FROM emp WHERE deptno = 30 AND job = 'SALESMAN';
SELECT ename, job, deptno FROM emp WHERE deptno = 30 AND job = 'CLERK';
SELECT ename, sal, comm, sal*12 + NVL(comm, 0) AS yearSalary FROM emp WHERE SAL*12 + NVL(comm, 0) >= 30000;
SELECT ename FROM emp WHERE ename >= 'G';
SELECT ename, job FROM emp WHERE job IN ('MANAGER', 'SALESMAN', 'CLERK');
SELECT ename, job FROM emp WHERE job NOT IN ('MANAGER', 'SALESMAN', 'CLERK');

SELECT ename FROM emp WHERE ename LIKE '____S';
SELECT ename FROM emp WHERE ename LIKE '__A%';
SELECT ename, comm FROM emp WHERE comm IS NULL;
SELECT SYSDATE FROM dual;

-- WITH : 1회용 VIEW
-- UNION ALL : 중복허용 O, UNION : 중복허용 X
WITH temp AS (
	SELECT 111 empno, 'name1' ename, 'programmer' job FROM dual 
	UNION ALL 
	SELECT 111 empno, 'name1' ename, 'programmer' job FROM dual 
	UNION ALL 
	SELECT 222 empno, 'name2' ename, 'actor' job FROM dual
) SELECT * FROM temp;

SELECT * FROM emp WHERE deptno IN (20, 30) AND sal >= 2000;

SELECT * FROM emp WHERE deptno = 20
UNION ALL
SELECT * FROM emp WHERE deptno = 30
INTERSECT
SELECT * FROM emp WHERE sal >= 2000;   -- 차집합 MINUS

SELECT * FROM emp;
-- Practice2
-- 1
SELECT ename FROM emp WHERE ename LIKE '%S';
-- 2
SELECT empno, ename, job, sal, deptno FROM emp WHERE deptno = 30 AND job = 'SALESMAN';
-- 3
SELECT empno, ename, sal, deptno FROM emp WHERE deptno IN (20, 30) AND sal >= 2000;
SELECT empno, ename, sal, deptno FROM emp WHERE deptno IN (20, 30)
	INTERSECT SELECT empno, ename, sal, deptno FROM emp WHERE sal >= 2000;
-- 4
SELECT ename, sal FROM emp WHERE sal < 2000 OR sal > 3000;
SELECT ename, sal FROM emp
 	MINUS SELECT ename, sal FROM emp WHERE sal BETWEEN 2000 AND 3000;
-- 5
SELECT ename, empno, sal, deptno FROM emp WHERE ename LIKE '%E%' AND deptno = 30 AND sal NOT BETWEEN 1000 AND 2000;	
-- 6
SELECT * FROM emp WHERE mgr IS NOT NULL AND NVL(comm, 0) = 0 AND job IN ('MANAGER', 'CLERK') AND ename LIKE '_L%'; 