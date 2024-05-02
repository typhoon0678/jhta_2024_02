-- Sub Query

SELECT * FROM emp WHERE sal > (SELECT sal FROM emp WHERE ename = 'JONES');

SELECT * FROM emp WHERE comm > (SELECT comm FROM emp WHERE ename = 'ALLEN');

SELECT * FROM emp WHERE hiredate < (SELECT hiredate FROM emp WHERE ename = 'SCOTT');

SELECT e.empno, e.ename, e.sal, e.deptno, d.dname, d.loc
FROM emp e INNER JOIN dept d ON e.deptno = d.deptno
WHERE e.deptno = 20 AND e.sal > (SELECT avg(sal) FROM emp);

SELECT * FROM emp WHERE sal = ANY (
	SELECT max(sal) AS max FROM emp GROUP BY deptno
);

SELECT * FROM emp WHERE sal > ALL (SELECT sal FROM emp WHERE deptno = 30);


SELECT e10.empno, e10.ename, e10.deptno, d.dname, d.loc
FROM (SELECT * FROM emp WHERE deptno = 10) e10 INNER JOIN dept d 
ON e10.deptno = d.deptno;

SELECT empno, ename, job, sal, 
	(SELECT grade FROM salgrade s WHERE e.sal BETWEEN s.losal AND s.hisal) AS grade
FROM emp e;


-- Problem 1
SELECT e.job, e.empno, e.ename, e.sal, e.deptno, d.dname 
FROM emp e INNER JOIN dept d ON e.deptno = d.deptno
WHERE e.job = (SELECT job FROM emp WHERE ename = 'ALLEN');

-- Problem 2
SELECT e.empno, e.ename, d.dname, e.hiredate, d.loc, e.sal, (SELECT grade FROM salgrade WHERE e.sal BETWEEN losal AND hisal) AS grade
FROM emp e INNER JOIN dept d ON e.deptno = d.DEPTNO
WHERE e.sal > (SELECT avg(sal) FROM emp)
ORDER BY e.sal desc;

-- Problem 3
SELECT e.empno, e.ename, e.job, e.deptno, d.dname, d.loc
FROM emp e INNER JOIN dept d ON e.deptno = d.deptno
WHERE e.deptno = 10 AND e.job NOT IN (SELECT DISTINCT job FROM emp WHERE deptno = 30);

-- Problem 4
SELECT e.empno, e.ename, e.sal, s.grade
FROM emp e INNER JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal
WHERE sal > (SELECT MAX(sal) FROM emp WHERE job = 'SALESMAN');