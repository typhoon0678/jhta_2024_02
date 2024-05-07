-- 객체 권한
GRANT SELECT ON emp TO jhta;


-- Problem 1
SELECT ename AS "사원명", sal AS "급여", sal + 300 AS "인상된 급여" FROM emp;

-- Problem 2
SELECT ename AS "사원명", sal AS "급여", sal * 12 + 300 AS "연간_총_수입" FROM emp
	ORDER BY sal DESC;

-- Problem 3
SELECT ename AS "사원명", sal AS "급여" FROM emp 
	WHERE sal > 2000 ORDER BY sal DESC;
	r
SELECT ename AS "사원명", deptno AS "부서번호" FROM emp WHERE empno = 7788;

-- Problem 5
SELECT ename AS "사원명", sal AS "급여" FROM emp 
	WHERE sal NOT BETWEEN 2000 AND 3000;
	
-- Problem 6
SELECT ename AS "사원명", job AS "담당업무", TO_CHAR(hiredate, 'YY/MM/DD')  AS "입사일" FROM emp
	WHERE hiredate BETWEEN TO_DATE('19810220', 'YYYYMMDD') AND TO_DATE('19810501', 'YYYYMMDD');  
	
-- Problem 7
SELECT ename AS "사원명", deptno AS "부서번호" FROM emp
	WHERE deptno IN (20, 30) ORDER BY ename DESC;
	
-- Problem 8
SELECT ename AS "사원명", sal AS "급여", deptno AS "부서번호" FROM emp
	WHERE sal BETWEEN 2000 AND 3000 AND deptno IN (20, 30)
	ORDER BY ename;
	
-- Problem 9
SELECT ename AS "사원명", TO_CHAR(hiredate, 'YY/MM/DD')  AS "입사일" FROM emp
	WHERE TO_CHAR(hiredate, 'YY/MM/DD') LIKE '81%';

-- Problem 10
SELECT ename AS "사원명", job AS "담당업무" FROM emp
	WHERE mgr IS NULL;
	
-- Problem 11
SELECT ename AS "사원명", sal AS "급여", comm AS "커미션" FROM emp
	WHERE nvl(comm, 0) != 0
	ORDER BY sal DESC, comm DESC;
	
-- Problem 12
SELECT ename AS "사원명" FROM emp 
	WHERE ename LIKE '__R%';
	
-- Problem 13
SELECT ename AS "사원명" FROM emp 
	WHERE ename LIKE '%A%' AND ename LIKE '%E%';
	
-- Problem 14 
SELECT ename AS "사원명", job AS "담당업무", sal AS "급여" FROM emp
	WHERE job IN ('CLERK', 'SALESMAN') AND sal NOT IN (1600, 950, 1300);
	
-- Problem 15 
SELECT ename AS "사원명", sal AS "급여", comm AS "커미션" FROM emp
	WHERE comm >= 500;

-- Problem 16
SELECT ename AS "사원명", SUBSTR(hiredate, 1, 2)  AS "입사_년", SUBSTR(hiredate, 4, 2) AS "입사_달" FROM emp;
-- SELECT ename AS "사원명", TO_CHAR(hiredate, 'YY') AS "입사_년", TO_CHAR(hiredate, 'MM') AS "입사_달" FROM emp;

-- Problem 17
SELECT * FROM emp WHERE SUBSTR(hiredate, 4, 2) = '04';

-- Problem 18
SELECT * FROM emp WHERE MOD(empno, 2) = 0;

-- Problem 19
SELECT TO_CHAR(hiredate, 'YY') AS "YY", TO_CHAR(hiredate, 'MM') AS "MON", TO_CHAR(hiredate, 'DD') AS "DY" FROM emp;  

-- Problem 20
SELECT TO_DATE(SYSDATE) - TO_DATE('2024-01-01') FROM dual;  

-- Problem 21
SELECT nvl(mgr, 0) FROM emp;

-- Problem 22
SELECT ename AS "이름", sal AS "급여", 
	DECODE(job, 'ANALIST', sal + 200, 'SALESMAN', sal + 180, 'MANAGER', sal + 150, 'CLERK', sal + 100, sal) AS "인상된_급여" FROM emp;
	
-- Problem 23
SELECT MAX(sal) AS "최고액", MIN(sal) AS "최저액", SUM(sal) AS "총액", ROUND(AVG(sal)) AS "평균" FROM emp;

-- Problem 24
SELECT job AS "담당업무", MAX(sal) AS "최고액", MIN(sal) AS "최저액", SUM(sal) AS "총액", ROUND(AVG(sal)) AS "평균" FROM emp
	GROUP BY job;

-- Problem 25
SELECT job AS "담당업무", COUNT(*) AS "사원_수" FROM emp
	GROUP BY job;
	
-- Problem 26
SELECT COUNT(*) FROM emp WHERE job = 'MANAGER';

-- Problem 27
SELECT MAX(sal) AS "최고액", MIN(sal) AS "최저액", MAX(sal) - MIN(sal) AS "차액" FROM emp;

-- Problem 28
SELECT job AS "직급", MIN(sal) AS "최저액" FROM emp
	GROUP BY job
	HAVING MIN(sal) > 2000
	ORDER BY MIN(sal) DESC;
	
-- Problem 29
SELECT deptno AS "부서번호", COUNT(*) AS "사원수", ROUND(AVG(sal) , 2) AS "평균급여" FROM emp
	GROUP BY deptno ORDER BY deptno DESC;  
	
-- Problem 30
SELECT deptno, 
	DECODE(deptno, 
			10, (SELECT dname FROM dept WHERE deptno = 10),
			20, (SELECT dname FROM dept WHERE deptno = 20),
			30, (SELECT dname FROM dept WHERE deptno = 30), NULL) AS "이름", 
	DECODE(deptno, 
			10, (SELECT loc FROM dept WHERE deptno = 10),
			20, (SELECT loc FROM dept WHERE deptno = 20),
			30, (SELECT loc FROM dept WHERE deptno = 30), NULL) AS "지역명", 
	COUNT(*) AS "사원수", ROUND(AVG(sal)) AS "평균급여" 
	FROM emp GROUP BY deptno;
/* 
SELECT e.deptno, d.dname AS "이름", d.loc AS "지역명", COUNT(*) AS "사원수", ROUND(AVG(e.sal)) AS "평균급여" 
	FROM emp e INNER JOIN dept d ON e.deptno = d.deptno
	GROUP BY e.deptno, d.dname, d.loc;
*/

-- Problem 31
SELECT deptno AS "부서번호", SUM(sal) AS "총액",
	DECODE(deptno, 
				10, (SELECT dname FROM dept WHERE deptno = 10),
				20, (SELECT dname FROM dept WHERE deptno = 20),
				30, (SELECT dname FROM dept WHERE deptno = 30), NULL) AS "부서명"
	FROM emp GROUP BY deptno;

-- Problem 32
SELECT e.ename AS "사원명", e.deptno AS "부서번호", d.dname AS "부서이름"
	FROM emp e, dept d 
	WHERE e.deptno = d.deptno AND e.ename = 'SCOTT';
	
-- Problem 33
SELECT e.ename AS "사원명", d.dname AS "부서이름", d.loc AS "지역명"
	FROM emp e INNER JOIN dept d ON e.deptno = d.deptno;

-- Problem 34
SELECT e.empno AS "사원번호", d.loc AS "지역명", e.job AS "담당업무"
	FROM emp e INNER JOIN dept d USING (deptno) WHERE deptno = 10;

-- Problem 35
SELECT e.ename AS "사원이름", e.job AS "부서이름", d.loc AS "지역명"
	FROM emp e NATURAL JOIN dept d
	WHERE nvl(comm, 0) != 0;
	
-- Problem 36
SELECT e.ename AS "이름", e.job AS "부서명"
	FROM emp e, dept d 
	WHERE e.deptno = d.deptno AND e.ename LIKE '%A%';
	
-- Problem 37
SELECT e.ename AS "사원명", e.job AS "업무", deptno AS "부서번호", d.dname AS "부서명"
	FROM emp e NATURAL JOIN dept d
	WHERE d.loc = 'NEW YORK';
	
-- Problem 38
SELECT e1.ename AS "사원명", e1.empno AS "사원번호", e1.mgr AS "관리자번호", e2.ename AS "관리자명"
	FROM emp e1, emp e2
	WHERE e1.mgr = e2.empno;
	
-- Problem 39
SELECT e1.ename AS "사원명", e2.empno AS "사원번호"
	FROM emp e1 LEFT OUTER JOIN emp e2 ON e1.empno = e2.empno
	ORDER BY e2.empno DESC;
/*
SELECT e.ename AS "사원명", e.empno AS "사원번호"
	FROM emp e 
	ORDER BY e.empno DESC;
*/

-- Problem 40
SELECT e1.ename AS "사원명", e2.deptno AS "부서번호"
	FROM emp e1 INNER JOIN emp e2
	ON e1.empno = e2.empno 
	AND e2.deptno = (SELECT deptno FROM emp WHERE ename = 'SCOTT');
/*	
SELECT e.ename AS "사원명", e.deptno AS "부서번호"
	FROM emp e WHERE e.deptno = (SELECT deptno FROM emp WHERE ename = 'SCOTT');
*/

-- Problem 41
SELECT e1.ename AS "사원명", TO_CHAR(e2.hiredate, 'YY/MM/DD') AS "입사일"
	FROM emp e1 INNER JOIN emp e2
	ON e1.empno = e2.empno
	AND e2.hiredate > (SELECT hiredate FROM emp WHERE ename = 'WARD');
/*
SELECT ename AS "사원명", TO_CHAR(hiredate, 'YY/MM/DD') AS "입사일"
	FROM emp WHERE hiredate > (SELECT hiredate FROM emp WHERE ename = 'WARD');
*/

-- Problem 42
SELECT e1.ename AS "사원이름", TO_CHAR(e1.hiredate, 'YY/MM/DD') AS "사원입사", 
	TO_CHAR(e2.hiredate, 'YY/MM/DD') AS "관리자입사", e2.ename AS "관리자이름"
	FROM emp e1 INNER JOIN emp e2
	ON e1.mgr = e2.empno
	AND e1.hiredate < e2.hiredate;
	
-- Problem 43
SELECT ename AS "사원이름", job AS "담당업무"
	FROM emp 
	WHERE job = (SELECT job FROM emp WHERE empno = 7788)
	AND empno != 7788;

-- Problem 44
SELECT ename AS "사원명", job AS "담당업무", sal AS "급여"
	FROM emp 
	WHERE sal > (SELECT sal FROM emp WHERE empno = 7499);

-- Problem 45
SELECT ename AS "이름", sal AS "급여", job AS "담당업무"
	FROM emp WHERE sal = (SELECT MIN(sal) FROM emp);

-- Problem 46
SELECT job AS "담당업무", ROUND(AVG(sal), 1) AS "급여평균" 
	FROM emp GROUP BY job
	ORDER BY AVG(sal)
	FETCH FIRST 1 ROWS ONLY;
/*
SELECT job AS "담당업무", ROUND(AVG(sal), 1) AS "급여평균" 
	FROM emp GROUP BY job
	HAVING AVG(sal) = 
		(SELECT MIN(avg_sal) FROM (SELECT job, AVG(sal) AS avg_sal FROM emp GROUP BY job));
*/

-- Problem 47
SELECT ename AS "사원명",sal AS "급여", deptno AS "부서번호"
	FROM emp WHERE (deptno, sal) IN (
		SELECT deptno, min(sal) FROM emp GROUP BY deptno
	);
/*
SELECT e1.ename AS "사원명", e1.sal AS "급여", e1.deptno AS "부서번호"
	FROM emp e1 INNER JOIN (
		SELECT MIN(sal) AS min_sal, deptno
		FROM emp GROUP BY deptno
	) e2 ON e1.deptno = e2.deptno AND e1.sal = e2.min_sal;
*/

-- Problem 48
SELECT empno AS "사원번호", ename AS "사원명", job AS "담당업무", sal AS "급여"
	FROM emp WHERE sal < (SELECT MIN(sal) FROM emp WHERE job = 'ANALYST') AND job != 'ANALYST';
SELECT DISTINCT mgr FROM emp;

-- Problem 49
SELECT ename FROM emp 
	WHERE empno NOT IN (SELECT DISTINCT mgr FROM emp WHERE mgr IS NOT NULL);

-- Problem 50
SELECT ename FROM emp 
	WHERE empno IN (SELECT DISTINCT mgr FROM emp WHERE mgr IS NOT NULL);

-- Problem 51
SELECT ename AS "사원명", TO_CHAR(hiredate, 'YY/MM/DD') AS "입사일"
	FROM emp WHERE deptno = (SELECT deptno FROM emp WHERE ename = 'BLAKE') AND ename != 'BLAKE';
	
-- Problem 52
SELECT empno AS "사원번호", ename AS "사원명", sal AS "급여"
	FROM emp WHERE sal > (SELECT AVG(sal) FROM emp)
	ORDER BY sal;
	
-- Problem 53
SELECT empno AS "사원번호", ename AS "사원명"
	FROM emp WHERE deptno IN (SELECT DISTINCT deptno FROM emp WHERE ename LIKE '%K%' AND deptno IS NOT NULL);
	
-- Problem 54
SELECT e.ename, e.deptno AS "부서번호", e.job AS "담당업무"
	FROM emp e INNER JOIN dept d
	ON e.deptno = d.deptno
	WHERE d.loc = 'DALLAS';
	
-- Problem 55
SELECT ename AS "사원이름", sal AS "급여"
	FROM emp WHERE mgr = (SELECT empno FROM emp WHERE ename = 'KING');
	
-- Problem 56
SELECT e.deptno AS "부서번호", e.ename AS "사원명", e.job AS "담당업무"
	FROM emp e INNER JOIN dept d ON e.deptno = d.deptno 
	WHERE d.dname = 'RESEARCH';
	
-- Problem 57
SELECT job FROM emp 
	GROUP BY job 
	HAVING AVG(sal) = (
		SELECT MIN(avg_sal) FROM (SELECT AVG(sal) AS avg_sal FROM emp GROUP BY job)
	); 

-- Problem 58
SELECT ename FROM emp 
	WHERE deptno IN (
		SELECT DISTINCT deptno FROM emp WHERE job = 'MANAGER' AND deptno IS NOT NULL
	);
	