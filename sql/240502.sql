--- 
SELECT * FROM emp INNER join(
   SELECT empno,ename,sal, dense_rank() over(ORDER BY sal desc) AS rank FROM emp) ranking
ON emp.EMPNO = ranking.empno
WHERE ranking.RANK <=5 ORDER BY RANKing.RANK;

COMMIT ;
ROLLBACK ;

SELECT * FROM emp;

-- 1~10, 11~20
-- 게시판 page
SELECT rownum,e.* FROM 
(SELECT * FROM emp ORDER BY sal DESC) e
WHERE rownum <= 5;  -- 고민해봐야 할 문제인거 같음

-- sequence 
-- 오라클은 자동 증가 없음  
SELECT * FROM emp;  
--7935
insert INTO emp (empno, ename) VALUES (
   (SELECT max(empno)+1 FROM emp),'jjang053'
);
SELECT * FROM emp;
ROLLBACK;

--sequence  jpa
CREATE SEQUENCE emp_seq 
 INCREMENT BY 1
 START WITH 1
 MAXVALUE 99
 MINVALUE 1
 NOCYCLE 
 CACHE 2;

--증가한 값을 다시 처음 부터 사용 안됨...
SELECT emp_seq.nextval FROM dual; 

SELECT * FROM emp;
 
 insert INTO emp (empno, ename) VALUES (
   emp_seq.nextval,'jjang053'
);

ROLLBACK;
--RANK dense_rank는 중복됐을때 그 다음 순서가  1,2,2,2,3
SELECT * FROM emp INNER join(
   SELECT empno,ename,sal, dense_rank() over(ORDER BY sal desc) AS rank 
FROM emp) ranking
ON emp.EMPNO = ranking.empno
WHERE ranking.RANK <=5
ORDER BY RANKing.RANK;

-- primary-key 는 중복되면 안되고 null이면 안됨.
CREATE SEQUENCE emp_seq01 
 INCREMENT BY 1
 START WITH 1
 MAXVALUE 10
 MINVALUE 1
 NOCYCLE 
 CACHE 5;  --cache는 미리 만들어논 SEQUENCE 갯수
 

 CREATE SEQUENCE emp_seq02
 INCREMENT BY 1
 START WITH 1
 MAXVALUE 9999999999999999
 MINVALUE 1
 CYCLE 
 CACHE 5;

--1,2,3,4,5/1,2,3,4,5/
 
 SELECT emp_seq02.currval FROM dual; 
-- 제약사항   
-- primary-key 제약사항을 만들면 자동으로 인덱싱 해줌.... 
-- db튜닝할때  dba들이 많이 한다.
CREATE INDEX idx_emp_no ON emp(sal);  -- 꼭 장점만 있는건 아니다.
-- 데이터가 엄청 많을때 사용하면 좋음... 
-- 미리 정렬을 해놓음...
-- SELECT 속도가 빨라짐...
-- index는 검색 빨리하기 위해 쓰는 거
SELECT * FROM emp;



-- 이건 시퀀스 보기
SELECT * FROM USER_SEQUENCES;

-- index 걸려 있는 컬럼 보기...
SELECT * FROM user_ind_columns;



-- Problem 5
DROP TABLE empidx;
CREATE TABLE empidx AS SELECT * FROM emp;
CREATE INDEX idx_impidx_empno ON empidx (empno);
SELECT * FROM user_indexes;

-- Problem 6
DROP VIEW empidx_over15k;
CREATE OR REPLACE VIEW empidx_over15k AS (
	SELECT empno, ename, job, deptno, sal, DECODE(nvl(comm, 0), 0, 'X', 'O') AS comm FROM emp
	WHERE sal > 1500
);

SELECT * FROM empidx_over15k;

-- Problem 7
CREATE TABLE deptseq AS SELECT * FROM dept;
SELECT * FROM deptseq;

CREATE SEQUENCE seq_deptseq
 INCREMENT BY 1
 START WITH 1
 MAXVALUE 10
 MINVALUE 1
 NOCYCLE 
 NOCACHE;

SELECT * FROM user_sequences;
SELECT * FROM user_ind_columns;

INSERT INTO deptseq (deptno, dname, loc) VALUES(seq_deptseq.nextval, 'DB', 'JONGRO');
INSERT INTO deptseq (deptno, dname, loc) VALUES(seq_deptseq.nextval, 'FRONT', 'IKSUN');
INSERT INTO deptseq (deptno, dname, loc) VALUES(seq_deptseq.nextval, 'SERVER', 'ILSAN');

SELECT * FROM deptseq;

COMMIT ;
ROLLBACK ;