SELECT ename, 
	INITCAP(ename) AS enameCap,
	UPPER(ename) AS upperName,
	LOWER(ename) AS lowerName 
	FROM emp;

SELECT ename, LENGTH(ename) AS LENGTH FROM emp;
SELECT LENGTH('한글'), LENGTHB('한글') FROM dual;
SELECT ename||job AS concat FROM emp; 
SELECT SUBSTR(ename, 1, 2)||'...' FROM emp; 

WITH temp AS (
	SELECT 1234 empno, 'michael' ename, 'manager' job FROM dual
) SELECT ename FROM temp;
SELECT INSTR('1-2-3-4', '-', 2, 2) FROM dual;
SELECT UPPER(ename) FROM emp WHERE INSTR(ename, 'S') != 0;
SELECT REPLACE('010_1111_2222', '_', '-') AS tel FROM dual;
SELECT LPAD('oracle', 10, '*'), RPAD('oracle', 10, '*') FROM dual; 
SELECT RPAD(SUBSTR('901231-1234567', 1, 7), LENGTH('901231-1234567'), '*') FROM dual;  
SELECT LPAD(SUBSTR(ename, -2), LENGTH(ename), '*'), RPAD(SUBSTR(ename, 1, 2), LENGTH(ename), '*') FROM emp;
SELECT TRIM('    oracle  ') FROM dual;
SELECT LTRIM('00001010101', '0') FROM dual;

SELECT ROUND(45.678, 1), ROUND(45.678, -1) FROM dual;
SELECT TRUNC(1234.5678), FLOOR(1234.5678), CEIL(1234.5678), CEIL(-2.5), MOD(10, 3) FROM dual; 
SELECT rownum, ename FROM emp;
SELECT SYSDATE, SYSDATE - 1 FROM dual;
SELECT MONTHS_BETWEEN(SYSDATE, '2002-09-08') FROM dual; 

WITH temp AS (
	SELECT 1111 AS NO, 'name1' AS name, '1990-08-02' AS hiredate FROM dual 
	UNION ALL
	SELECT 2222 AS NO, 'name2' AS name, '1984-04-12' AS hiredate FROM dual
) SELECT ROUND(MONTHS_BETWEEN(sysdate, hiredate) / 12, 1)  FROM temp;
SELECT NEXT_DAY(sysdate, '일요일') FROM dual;
SELECT LAST_DAY('2004-2-1') FROM dual; 
SELECT 2 + TO_NUMBER('2') FROM dual;

SELECT TO_CHAR(SYSDATE, 'yy-mm-dd dy / am HH:MI:SS') FROM dual;
SELECT TO_CHAR(SYSDATE, 'yy-mm-dd month mon ww q ') FROM dual;
SELECT TO_CHAR(1234, '$009,999.99') FROM dual;
SELECT TO_CHAR(1234, 'fm99,999,999') FROM dual;
SELECT TO_NUMBER('1234') AS num FROM dual; 
SELECT TO_DATE('2024/04/26', 'yyyy-mm-dd') AS "date" FROM dual;

SELECT ename, NVL2(comm, sal*12 + comm, sal*12) AS annualSal FROM emp;

-- Problem1
SELECT RPAD(SUBSTR(empno, 1, 2), LENGTH(empno), '*'), RPAD(SUBSTR(ename, 1, 1), LENGTH(ename), '*') FROM emp;
-- Problem2
SELECT TRUNC(sal / 21.5, 2) AS "daySalary", ROUND(sal / (21.5 * 8), 2) AS "hourSalary" FROM emp; 
-- Problem3
SELECT ename, TO_CHAR(NEXT_DAY(ADD_MONTHS(hiredate, 3), 2), 'YYYY-MM-DD') AS "day" FROM emp; 