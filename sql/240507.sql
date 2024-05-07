 
-- sys계정에서
-- 19c 버전부터 계정을 만들때 c## 붙여야 하는데 불편함
-- 아래 구문을 쓰면 일반적인 계정을 만들 수 있음... 
alter session set "_ORACLE_SCRIPT" = true;
-- 계정은 생성되지만 접속권한이나 table을 만들 수 있는 권한 없음
CREATE USER scott IDENTIFIED BY tiger;
-- 권한 부여
GRANT CONNECT, resource, unlimited TABLESPACE TO scott;

-- select 
SELECT * FROM emp;

SELECT empno,ename,mgr, sal FROM emp;

SELECT empno AS 사원번호,ename,mgr, sal FROM emp;

-- 중복제거
SELECT DISTINCT deptno FROM emp;

SELECT ename, sal, sal*12+nvl(comm,0) as annualsal, comm FROM emp e;

-- desc 내림차순 -- asc 오름차순 default는 asc
-- order by는 비용 발생 시간 오래 걸릴 수도 있다.   
SELECT * FROM emp ORDER BY sal desc; 

--where 절 중요  filtering 해줌...
SELECT * FROM emp WHERE deptno = 30; 

SELECT * FROM emp WHERE deptno = 30 AND job = 'SALESMAN'; 

SELECT * FROM emp WHERE deptno = 20 OR job = 'CLERK'; 

SELECT * FROM emp WHERE sal != 3000; 

SELECT * FROM emp WHERE NOT sal = 3000; 

SELECT * FROM emp WHERE job = 'MANAGER' OR JOB = 'CLERK' OR job = 'SALESMAN';

SELECT * FROM emp WHERE job in('MANAGER','CLERK','SALESMAN');

-- manager가 아니고, clerk가 아니고, salesman이아닌 사람
SELECT * FROM emp WHERE job not in('MANAGER','CLERK','SALESMAN');

-- manager가 아니고, clerk가 아니고, salesman이아닌 사람
SELECT * FROM emp WHERE job != 'MANAGER' 
and JOB != 'CLERK' and job != 'SALESMAN';

--아래 두개의 구문은 같다.  
SELECT * FROM emp WHERE sal >= 2000 AND sal <= 3000;
SELECT * FROM emp WHERE sal BETWEEN 2000 AND 3000;

--like   %는  모든  , _는 자릿수
SELECT * FROM emp WHERE ename like 'S%';
SELECT * FROM emp WHERE ename like '%S%';
SELECT * FROM emp WHERE ename like '_L%';

--null 을 비교할때는 is
--아래 결과는 안나옴
SELECT * FROM emp WHERE COMM = NULL;
--is null 사용해야함, null은 연산도 안됨..
SELECT * FROM emp WHERE COMM IS NULL;
--매니저가 있는 사람만 출력
SELECT * FROM emp WHERE mgr IS NOT NULL;

--함수 function min(컬럼명)  단일행 vs다중행, 복수행
SELECT ename,UPPER(ename),lower(ename),INITCAP(ename) FROM emp;

-- 대문자바꾸기
SELECT * FROM emp WHERE UPPER(ename) LIKE UPPER('%s%'); 
--문자열 길이 lengthb()는 바이트 길이 
SELECT ename, length(ename) AS ename_length FROM emp;
SELECT LENGTH('장성호'),LENGTHB('장성호') FROM dual;
-- substr(컬럼명,시작위치, 길이) 시작위치에 음수가 올 수 있음 이때는 뒤에서 센다.
SELECT job,substr(job,1,2) AS job01, substr(job,2),substr(job,-2)  FROM emp;
SELECT substr(ename,1,3) || '...' AS ename FROM emp;
SELECT instr('hello oracle', 'l') AS instr01 FROM dual;
--이름중에 'S'가 하나라도 있는경우
SELECT * FROM emp WHERE instr(ename,'S') > 0; 
SELECT * FROM emp WHERE ename LIKE '%S%';

--replace 바꾸기
SELECT REPLACE('010-1111-2222','-','') AS replace01 FROM dual;

--lpad, rpad  masking할때 쓴다.  
SELECT lpad('oracle',10,'#') AS lpad01, 
rpad('oracle',10,'#') AS ㄱpad01 FROM dual;
SELECT rpad('951111-1',14,'#') AS jumin FROM dual;
SELECT rpad('010-1111-1',13,'#') AS tel FROM dual;
--concat() 7369:smith 
SELECT concat(empno, ename) AS ch_name,
concat(empno,concat(':',ename)) AS ch_name02,
empno||':'||ename AS ch_name02
FROM emp;

--trim()  공백제거
SELECT '   jjang051',trim('   jjang051') FROM dual;

--round, trunc,ceil, floor, mod
SELECT 
   round(1234.5678) AS round01,
   round(1234.5678,1) AS round02,
   round(1234.5678,2) AS round03,
   round(1234.5678,-1) AS round04,
   round(1235.5678,-1) AS round05,
   round(1235.5678,-2) AS round06,
   round(1255.5678,-2) AS round07
FROM dual;

-- 오라클에서만 사용 
SELECT 
   trunc(1234.5678) AS trunc01,
   trunc(1234.5678,1) AS trunc02,
   trunc(1234.5678,2) AS trunc03,
   trunc(1234.5678,-1) AS trunc04,
   trunc(1235.5678,-1) AS trunc05,
   trunc(1235.5678,-2) AS trunc06,
   trunc(1255.5678,-2) AS trunc07
FROM dual;

SELECT 
   ceil(1234.4678) AS ceil01,
   floor(1234.9678) AS ceil02
FROM dual;

SELECT 
   mod(10,3) AS mod01
FROM dual;

--날짜 다루는 함수
SELECT sysdate AS now,
sysdate - 1 AS yesterday,
sysdate + 1 AS tomorrow,
add_months(sysdate,3) AS after_3_month
FROM dual;

SELECT empno, ename, hiredate, 
add_months(hiredate,120) AS ten FROM emp;

SELECT empno, ename, hiredate, 
trunc(months_between(hiredate,sysdate)) AS month01 FROM emp;

--형변환함수  to_  묵시적 형변환
SELECT empno,empno+'100' AS empno FROM emp;
SELECT empno,empno+TO_NUMBER('100') AS empno FROM emp;
--이건 안됨 숫자랑 문자랑 더하기 안됨
SELECT empno,empno + 'aaa' AS empno FROM emp;

--to_char
SELECT sysdate, TO_CHAR(sysdate,'YYYY/MM/DD HH:MI:SS') 
AS now FROM dual; 

SELECT sysdate, 
   TO_CHAR(sysdate,'MM'),
   TO_CHAR(sysdate,'MON'),
   TO_CHAR(sysdate,'MONTH'),
   TO_CHAR(sysdate,'DD'),
   TO_CHAR(sysdate,'DY'),
   TO_CHAR(sysdate,'DAY')
AS now FROM dual; 

SELECT sysdate, 
   TO_CHAR(sysdate,'MM','NLS_DATE_LANGUAGE=ENGLISH'),
   TO_CHAR(sysdate,'MON','NLS_DATE_LANGUAGE=ENGLISH'),
   TO_CHAR(sysdate,'MONTH','NLS_DATE_LANGUAGE=ENGLISH'),
   TO_CHAR(sysdate,'DD','NLS_DATE_LANGUAGE=ENGLISH'),
   TO_CHAR(sysdate,'DY','NLS_DATE_LANGUAGE=ENGLISH'),
   TO_CHAR(sysdate,'DAY','NLS_DATE_LANGUAGE=ENGLISH')
AS now FROM dual; 

SELECT sysdate, 
   TO_CHAR(sysdate,'MM','NLS_DATE_LANGUAGE=JAPANESE'),
   TO_CHAR(sysdate,'MON','NLS_DATE_LANGUAGE=JAPANESE'),
   TO_CHAR(sysdate,'MONTH','NLS_DATE_LANGUAGE=JAPANESE'),
   TO_CHAR(sysdate,'DD','NLS_DATE_LANGUAGE=JAPANESE'),
   TO_CHAR(sysdate,'DY','NLS_DATE_LANGUAGE=JAPANESE'),
   TO_CHAR(sysdate,'DAY','NLS_DATE_LANGUAGE=JAPANESE')
AS now FROM dual; 

-- 중국 찾아보기 알면 가르쳐 주세요...
select TO_CHAR (SYSDATE, 'YYYY-MM-DD','nls_date_language=''Traditional Chinese''') from dual;
SELECT sysdate, 
   TO_CHAR(sysdate,'MM','NLS_DATE_LANGUAGE=''Traditional Chinese'''),
   TO_CHAR(sysdate,'MON','NLS_DATE_LANGUAGE=''Traditional Chinese'''),
   TO_CHAR(sysdate,'MONTH','NLS_DATE_LANGUAGE=''Traditional Chinese'''),
   TO_CHAR(sysdate,'DD','NLS_DATE_LANGUAGE=''Traditional Chinese'''),
   TO_CHAR(sysdate,'DY','NLS_DATE_LANGUAGE=''Traditional Chinese'''),
   TO_CHAR(sysdate,'DAY','NLS_DATE_LANGUAGE=''Traditional Chinese''')
AS now FROM dual; 







SELECT sysdate, 
   TO_DATE('2024-05-07','YYYY-MM-DD'),
   TO_DATE('07/05/2024','DD/MM/YYYY')
AS now FROM dual;

SELECT empno,ename,comm, nvl(comm,0) AS nvl_comm ,
nvl2(comm,'yes','no') AS nvl_comm02
FROM emp;

--case when
SELECT empno, ename, job,sal,
CASE upper(job)
  WHEN 'MANAGER' THEN sal*1.5
  WHEN 'CLERK' THEN sal*1.25
  WHEN 'SALESMAN' THEN sal*1.1
  ELSE sal*1
END AS plus_sal
FROM emp;
  

SELECT empno, ename, job,sal,
CASE 
  WHEN job = 'MANAGER' THEN sal*1.5
  WHEN job = 'manager' THEN sal*1.5
  WHEN job = 'CLERK' THEN sal*1.25
  WHEN job = 'SALESMAN' THEN sal*1.1
  ELSE sal*1
END AS plus_sal
FROM emp;

--오라클 전용 decode
SELECT empno, ename, job,sal,
   decode(job, 
     'MANAGER',sal*1.5,
     'manager',sal*1.5,
     'CLERK',sal*1.25,
     'SALESMAN',sal*1.1,
     sal*1) AS plus_sal
FROM emp;

--다중행 함수
SELECT sum(sal), sum(comm) FROM emp;
SELECT count(*) FROM emp WHERE deptno=30;
SELECT max(sal) FROM emp;
SELECT min(sal) FROM emp;

SELECT max(hiredate),min(hiredate) FROM emp;

SELECT round(avg(sal),2) FROM emp WHERE DEPTNO = 30;
SELECT deptno,round(avg(sal),2) FROM emp 
GROUP BY deptno;

--일반열은 다중행 함수에 같이 못씀 group by로 묶어서 사용
SELECT deptno,job,round(avg(sal),2) FROM emp 
GROUP BY deptno, job
ORDER BY DEPTNO, job;

-- having절은 group by 절과 같이 사용
-- where 절에 group 함수 사용 안됨...
SELECT deptno,job,round(avg(sal),2) FROM emp
--WHERE avg(sal) >= 2000
GROUP BY deptno, job
HAVING avg(sal) >= 2000
ORDER BY DEPTNO, job;

SELECT deptno,job,round(avg(sal),2) AS rr FROM emp
WHERE sal >= 3000   
GROUP BY deptno, job
HAVING avg(sal) >= 2000
ORDER BY rr;


-- join  은 table 연결해서 쓰기... 
-- db 테아블의 row는 얼마든지 확장 가능 옆으로 확장은 힘들다
-- 모든 테이블에 컬럼을 다 때려 넣는건 좋지 않다.  
-- rdbms  (오라클, mysql)

--  오라클 조인(오라클 전용) ansi(표준) 조인 (거의 대부분의 rdbms에서 사용) 
SELECT * FROM emp,dept
WHERE emp.deptno = dept.deptno;

-- 같은 데이터를 쓰고 있을때  등가조인
SELECT e.empno, e.ename,e.job,e.mgr, e.HIREDATE ,e.sal, e.comm,
d.DEPTNO,d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno = d.deptno;  

SELECT * FROM salgrade;

-- sal이 같은 경우가 잘 없다 이때는 범위로 찾아야 한다. 비등가조인
SELECT e.empno, e.ename,e.job,e.mgr, e.HIREDATE ,e.sal, e.comm,
e.DEPTNO,s.grade,s.losal,s.hisal
FROM emp e,salgrade s
WHERE e.sal >= s.losal AND e.sal <= s.HISAL;

-- sal이 같은 경우가 잘 없다 이때는 범위로 찾아야 한다. 비등가조인
SELECT e.empno, e.ename,e.job,e.mgr, e.HIREDATE ,e.sal, e.comm,
e.DEPTNO,s.grade,s.losal,s.hisal
FROM emp e,salgrade s
WHERE e.sal BETWEEN s.LOSAL AND s.HISAL;

-- 자체 join inner vs outer join
SELECT e1.empno, e1.ename, e1.mgr, e2.empno,e2.ename 
FROM emp e1, emp e2
WHERE e1.mgr = e2.EMPNO(+);

SELECT e1.empno, e1.ename, e1.mgr, e2.empno,e2.ename 
FROM emp e1, emp e2
WHERE e1.mgr(+) = e2.EMPNO;


--표준 join으로 바꿔 보기...

SELECT e.empno, e.ename,e.job,e.mgr, e.HIREDATE ,e.sal, e.comm,
      DEPTNO,
      d.dname,d.loc
FROM emp e NATURAL JOIN dept d;  

SELECT e.empno, e.ename,e.job,e.mgr, e.HIREDATE ,e.sal, e.comm,
      DEPTNO,
      d.dname,d.loc
FROM emp e JOIN dept d USING(deptno);  

--가장 많이 씀...
SELECT e.empno, e.ename,e.job,e.mgr, e.HIREDATE ,e.sal, e.comm,
      e.DEPTNO,
      d.dname,d.loc
FROM emp e JOIN dept d 
ON e.DEPTNO = d.DEPTNO;


SELECT e1.empno, e1.ename, e1.mgr, e2.empno,e2.ename 
FROM emp e1 JOIN emp e2
ON e1.mgr = e2.empno;

SELECT e1.empno, e1.ename, e1.mgr, e2.empno,e2.ename 
FROM emp e1 LEFT OUTER JOIN emp e2
ON e1.mgr = e2.empno;

SELECT e1.empno, e1.ename, e1.mgr, e2.empno,e2.ename 
FROM emp e1 RIGHT OUTER JOIN emp e2
ON e1.mgr = e2.empno;


-- subquery
--query안에 query한번 더 쓰기
-- SELECT sal FROM emp WHERE ename = 'JONES';
-- subquery 쓸때 type이 같아야 함...
SELECT * FROM emp 
WHERE sal > (SELECT sal FROM emp WHERE ename = 'JONES');

SELECT * FROM emp 
WHERE hiredate > (SELECT HIREDATE FROM emp WHERE ename = 'JONES');


-- 20번 부서에 있는 사람중 전체 사원의 평균 급여보다 높은 월급을 
-- 받는 사람의 부서 정보와 이름, 월급을 출력
SELECT e.EMPNO , e.ENAME , e.job,e.sal, d.dname, d.loc, d.deptno
FROM emp e, dept d
WHERE e.DEPTNO = d.DEPTNO 
AND e.DEPTNO = 20
AND e.sal > (SELECT avg(sal) FROM emp);


SELECT e.EMPNO , e.ENAME , e.job,e.sal, d.dname, d.loc, d.deptno
FROM emp e JOIN dept d
ON e.DEPTNO = d.DEPTNO 
WHERE e.DEPTNO = 20
AND e.sal > (SELECT avg(sal) FROM emp);

--부서별 최고 연봉자
SELECT * FROM emp 
WHERE sal IN (SELECT max(sal) FROM emp
GROUP BY deptno);

--any, some는 하나만 만족하면 true, all 싹다 만족해야지만 true
SELECT * FROM emp 
WHERE sal = some (SELECT max(sal) FROM emp
GROUP BY deptno);

--EXISTS는 하나라도 존재하면 true, 없으면 false
SELECT * FROM emp 
WHERE EXISTS (SELECT dname FROM dept WHERE DEPTNO = 10);

--subquery는 조건절에서만 사용하는건 아니다.
-- from절에 쓰는 subquery를 inline view라고도 한다.
SELECT e10.empno,e10.ename,e10.deptno, d.dname, d.loc
FROM 
(SELECT * FROM emp WHERE DEPTNO = 10) e10, 
(SELECT * FROM dept) d
WHERE e10.deptno = d.deptno;


SELECT * FROM emp;

-- select 절에 있는 subquery는 스칼라 서브쿼리라고도 함...
SELECT empno, ename, job,sal,
(SELECT grade FROM SALGRADE WHERE e.sal BETWEEN  losal AND hisal)  
AS grade
FROM emp e;

-- select가 가장 중요 나중에 가면 결국 query 잘짜야지만 결과가 나옴 

-- insert / delete / update  where 가 중요
DROP TABLE TEMP_DEPT; 
CREATE TABLE TEMP_DEPT AS SELECT * FROM dept;
SELECT * from TEMP_DEPT;

INSERT INTO TEMP_DEPT (deptno, dname,loc) 
VALUES (50,'db','jongro');

INSERT INTO TEMP_DEPT (deptno,dname,loc) 
VALUES (70,'back',null);
ROLLBACK;
COMMIT;
-- delete, update는 조건이 필요하다...  
-- insert into, update set, delete from
UPDATE TEMP_DEPT 
SET loc = 'seoul', dname='backend' 
WHERE deptno = 70;

SELECT * FROM TEMP_DEPT ;
DELETE FROM TEMP_DEPT WHERE deptno = 70;

-- dml end   select/insert/delete/update
-- table, entity 만들기,,,  ddl
-- jpa 쓰면 자동으로 만들어 준다. 대신 순서는 없다.
-- 보통 만들어준거 쓰면 된다.   
DROP TABLE ddl_emp;
CREATE TABLE DDL_EMP (
   empno    number(4),
   ename    varchar2(10),
   job      varchar2(10),
   mgr      number(4),
   hiredate DATE,
   sal      number(7,2),
   comm     number(7,2),
   deptno   number(2)
);
SELECT * FROM ddl_emp;

INSERT INTO ddl_emp VALUES 
(1111,'jjang051','MANAGER',NULL,sysdate,2500,NULL,30);
COMMIT;
SELECT * FROM ddl_emp;

--컬럼 추가
ALTER TABLE ddl_emp 
ADD hp varchar2(14);

--이름 바꾸기
ALTER TABLE DDL_EMP 
RENAME COLUMN mgr TO manager;

--컬럼의 type을 바꿀때 글자수 줄이거나 하는거 힘듦
ALTER TABLE DDL_EMP 
MODIFY empno number(5);

--컬럼 삭제
ALTER TABLE DDL_EMP 
DROP COLUMN hp;

--테이블 이름 바꾸기 잘 안함...
RENAME ddl_emp TO ddl_emp02;
SELECT * FROM ddl_emp02;

-- 안에 있는 내용 지우기..  delete랑 같음...  ddl은 transaction없음
-- 만들거나 지우면 끝...
TRUNCATE TABLE ddl_emp02;

-- 자동 증가 없는 oracle sequence


CREATE SEQUENCE seq_ddl_emp
INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999999999
MINVALUE 1
CYCLE 
CACHE 10;
ROLLBACK;
--rownum 사용
SELECT * FROM ddl_emp02;
INSERT INTO ddl_emp02 
VALUES (seq_ddl_emp.nextval,'jjang','MANAGER',NULL,
'2024-05-01',2500,NULL,10);

SELECT seq_ddl_emp.currval FROM dual;

ALTER SEQUENCE seq_ddl_emp
MAXVALUE 15
nocycle;


CREATE SEQUENCE seq_ddl_emp02
START WITH 1
INCREMENT BY 1
MAXVALUE 999999
MINVALUE 1
noCYCLE
cache 10;

--
INSERT INTO ddl_emp02 values(seq_ddl_emp02.nextval, 'jjang', 'MANAGER', 
NULL, '2024-05-01', 2500, NULL, 30);


-- 제약 조건
CREATE TABLE temp_not_null (
   userid varchar2(20) NOT NULL,
   userpw varchar2(20) NOT NULL,
   cp     varchar2(12)
);
-- primary key를 두개 만들고 싶으면 
CREATE TABLE temp_not_primary (
   userid varchar2(20), 
   userpw varchar2(20) NOT null,
   email varchar2(100) NOT NULL unique,
   cp     varchar2(14),
   CONSTRAINT temp_not_primary_pk 
   PRIMARY key(userid, email)
);
select * FROM temp_not_primary;
DROP TABLE temp_not_primary;

INSERT INTO temp_not_primary 
values('jjang053','1234','jjang051@hanmail.net',null);
SELECT * FROM temp_not_primary;


CREATE TABLE temp_dept_fk(
   deptno number(2) CONSTRAINT temp_dept_fk_pk PRIMARY KEY,
   dname  varchar2(20),
   loc    varchar2(20)
);

CREATE TABLE temp_emp_fk02 (
   empno number(4),
   ename varchar2(20),
   deptno number(2) CONSTRAINT temp_dept_fk_fk
    REFERENCES temp_dept_fk(deptno)
);
--참조키 다른 테이블에 있는 값만 넣을 수 있음
INSERT INTO temp_dept_fk values(10,'db','jongro');
INSERT INTO temp_emp_fk02 values(1111,'aaaa',20);

DROP TABLE temp_dept_fk CASCADE constraints;

