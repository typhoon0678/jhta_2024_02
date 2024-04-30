-- 다중행 함수

SELECT sum(sal), count(comm) FROM emp;
	
SELECT max(sal) AS max_sal, min(sal) AS min_sal FROM emp;

SELECT avg(sal) FROM emp WHERE deptno = 10
UNION ALL 
SELECT avg(sal) FROM emp WHERE deptno = 20
UNION ALL 
SELECT avg(sal) FROM emp WHERE deptno = 30;

SELECT avg(sal), deptno FROM emp GROUP BY deptno;

SELECT avg(sal), deptno FROM emp GROUP BY deptno;

SELECT deptno, job, avg(sal) FROM emp 
GROUP BY deptno, job 
ORDER BY deptno, job;

SELECT deptno, job, avg(sal) FROM emp 
	WHERE sal <= 3000
	GROUP BY deptno, job 
	having avg(sal) >= 2000
	ORDER BY deptno, job;
	
SELECT deptno, job, avg(sal) FROM emp 
	GROUP BY deptno, job
	ORDER BY avg(sal), deptno, job;
	
SELECT job, sum(sal) AS sal_total, count(*) AS count, avg(sal) FROM emp 
	GROUP BY job
	HAVING sum(sal) >= 5000 AND avg(sal) >= 2000;

SELECT job, sum(sal) FROM emp GROUP BY ROLLUP (job);

SELECT job, deptno, sum(sal) FROM emp GROUP BY ROLLUP (job, deptno) ORDER BY job, deptno;

SELECT 
	deptno, job, count(*) AS count, max(sal) AS max, sum(sal) AS sum, 
	GROUPING(deptno), GROUPING(job) FROM emp 
GROUP BY CUBE (deptno,  job) ORDER BY deptno, job;

SELECT 
	DECODE(GROUPING(deptno), 1, 'ALL_DEPT_NO', deptno) AS deptno, 
	DECODE(GROUPING(job), 1, 'ALL_JOB', job)  AS job, 
	count(*),
	max(sal) AS max,
	avg(sal) AS avg
	FROM emp
	GROUP BY CUBE(deptno, job)
	ORDER BY deptno, job;
	
-- group by 절에 alias(별명) 사용 X, order by 절에는 사용 가능

-- Problem 1
SELECT deptno, round(avg(sal)), max(sal), min(sal), count(*)
FROM emp
GROUP BY deptno
ORDER BY deptno;

-- Problem 2
SELECT job, count(*) FROM emp
GROUP BY job
HAVING count(*) >= 3;

-- Problem 3
SELECT TO_CHAR(hiredate, 'YYYY') AS hire_year , deptno, count(*) FROM emp
GROUP BY TO_CHAR(hiredate, 'YYYY'), deptno
ORDER BY 1, 2;

-- Problem 4
SELECT DECODE(NVL(comm, 0), 0, 'no', 'yes') AS decode_comm, count(*) FROM emp 
GROUP BY DECODE(NVL(comm, 0), 0, 'no', 'yes')
ORDER BY 1 DESC;

-- Problem 5
SELECT deptno, TO_CHAR(hiredate, 'YYYY') AS hire_year, max(sal), sum(sal), round(avg(sal)) FROM emp 
GROUP BY ROLLUP(deptno, TO_CHAR(hiredate, 'YYYY'))
ORDER BY 1, 2; 



-- listagg
SELECT deptno, LISTAGG(ename, ', ') WITHIN GROUP(ORDER BY sal DESC)
FROM emp GROUP BY deptno;