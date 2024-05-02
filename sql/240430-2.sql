-- CRUD

CREATE TABLE temp_dept AS SELECT * FROM dept;
SELECT * FROM temp_dept;
DROP TABLE temp_dept;

-- DESC temp_dept; // DBeaver에서 동작 안됨

INSERT INTO temp_dept (deptno, dname, loc)
	SELECT 50, 'BACKEND', 'SEOUL' FROM dual UNION ALL
	SELECT 60, 'FRONTEND', NULL FROM dual UNION ALL
	SELECT 70, 'DATABASE', 'SEOUL' FROM dual UNION ALL
	SELECT 80, 'BACKEND', '' FROM dual;
	

CREATE TABLE temp_emp AS SELECT * FROM emp WHERE 1 != 1;

INSERT INTO temp_emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (9997, 'JJ', 'MANAGER', NULL, to_date('30-04-2024', 'DD-MM-YYYY'), 8800, 800, 20);
SELECT * FROM temp_emp;
 
UPDATE temp_dept SET loc = 'SEOUL' WHERE loc IS null;
COMMIT;
ROLLBACK;

UPDATE temp_dept SET (dname, loc) = (SELECT dname, loc FROM dept WHERE deptno = 40)
WHERE deptno = 60;

SELECT * FROM temp_emp;

DELETE FROM temp_emp WHERE job = 'MANAGER';


-- Problem 
DROP TABLE temp_emp;
DROP TABLE temp_dept;
DROP TABLE temp_salgrade;
CREATE TABLE temp_emp AS SELECT * FROM emp;
CREATE TABLE temp_dept AS SELECT * FROM dept;
CREATE TABLE temp_salgrade AS SELECT * FROM salgrade;
COMMIT;
ROLLBACK;

SELECT * FROM temp_emp;
SELECT * FROM temp_dept;
SELECT * FROM temp_salgrade;
-- Problem 1
INSERT INTO temp_dept (deptno, dname, loc)
	SELECT 50, 'ORACLE', 'BUSAN' FROM dual UNION ALL
	SELECT 60, 'SQL', 'ILSAN' FROM dual UNION ALL
	SELECT 70, 'SELECT', 'INCHEON' FROM dual UNION ALL
	SELECT 80, 'DML', 'BUNDANG' FROM dual; 
	

-- Problem 2
INSERT INTO temp_emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
	SELECT 7201, 'TEST_USER1', 'MANAGER', 7788, TO_DATE('2023-11-13', 'YYYY-MM-DD') + 50 * 1, 4500, NULL, 50 FROM dual UNION ALL
	SELECT 7202, 'TEST_USER2', 'CLERK', 7788, TO_DATE('2023-11-13', 'YYYY-MM-DD') + 50 * 2, 1800, NULL, 50 FROM dual UNION ALL
	SELECT 7203, 'TEST_USER3', 'ANALYST', 7788, TO_DATE('2023-11-13', 'YYYY-MM-DD') + 50 * 3, 3400, NULL, 60 FROM dual UNION ALL
	SELECT 7204, 'TEST_USER4', 'SALESMAN', 7788, TO_DATE('2023-11-13', 'YYYY-MM-DD') + 50 * 4, 2700, 300, 60 FROM dual UNION ALL
	SELECT 7205, 'TEST_USER5', 'CLERK', 7788, TO_DATE('2023-11-13', 'YYYY-MM-DD') + 50 * 5, 2600, NULL, 70 FROM dual UNION ALL
	SELECT 7206, 'TEST_USER6', 'CLERK', 7788, TO_DATE('2023-11-13', 'YYYY-MM-DD') + 50 * 6, 2600, NULL, 70 FROM dual UNION ALL
	SELECT 7207, 'TEST_USER7', 'LECTURER', 7788, TO_DATE('2023-11-13', 'YYYY-MM-DD') + 50 * 7, 2300, NULL, 80 FROM dual UNION ALL
	SELECT 7208, 'TEST_USER8', 'STUDENT', 7788, TO_DATE('2024-03-09', 'YYYY-MM-DD'), 1200, NULL, 80 FROM dual;

-- Problem 3
UPDATE temp_emp SET deptno = 70
WHERE sal > (SELECT avg(sal) FROM temp_emp WHERE deptno = 50);

-- Problem 4
UPDATE temp_emp SET sal = sal * 1.1, deptno = 80
WHERE hiredate > (SELECT min(hiredate) FROM temp_emp WHERE deptno = 60);