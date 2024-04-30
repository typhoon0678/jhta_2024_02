-- join
SELECT * FROM emp e, dept d
WHERE e.DEPTNO = d.DEPTNO;

SELECT e.*, d.dname, d.loc FROM emp e, dept d
WHERE e.deptno = d.deptno
ORDER BY ename;

SELECT * FROM salgrade;

SELECT e.ENAME, e.SAL, s.GRADE FROM emp e, salgrade s WHERE e.sal BETWEEN s.LOSAL AND s.HISAL ;

SELECT e1.empno, e1.ename, e1.mgr AS MGR_NO, e2.ename AS MGR_NAME
FROM emp e1, emp e2
WHERE e1.mgr = e2.empno(+);

SELECT e.EMPNO , e.ENAME , e.JOB , e.MGR , e.HIREDATE, e.SAL , e.COMM ,
		e.deptno, 
		d.DNAME , d.LOC 
FROM emp e INNER JOIN dept d ON e.deptno = d.deptno;

SELECT e.EMPNO , e.ENAME , e.JOB , e.MGR , e.HIREDATE, e.SAL , e.COMM ,
		deptno, 
		d.DNAME , d.LOC 
FROM emp e NATURAL JOIN dept d;

SELECT e1.empno, e1.ename,
	e2.empno AS mgr_no, e2.ename AS mgr_name
FROM emp e1 LEFT OUTER JOIN emp e2 ON e1.mgr = e2.empno ORDER BY e2.empno;


-- rarely used - cross join, full outer join
SELECT e1.empno, e1.ename, d1.deptno, d1.dname
FROM emp e1 CROSS JOIN dept d1 ORDER BY e1.empno;

SELECT e1.empno, e1.ename, d1.deptno, d1.dname
FROM emp e1 FULL OUTER JOIN dept d1 ON e1.deptno = d1.deptno ORDER BY e1.empno;


-- Problem 1
SELECT e.ename, e.sal, e.deptno, d.dname, d.loc
FROM emp e INNER JOIN dept d ON e.deptno = d.deptno
WHERE e.sal > 2000;

-- Problem 2
SELECT d.deptno, d.dname, round(avg(e.sal)) AS avg_sal, max(e.sal) AS max_sal, min(e.sal) AS min_sal, count(*) AS cnt
FROM emp e INNER JOIN dept d ON e.deptno = d.deptno
GROUP BY d.deptno, d.dname;

-- Problem 3
SELECT d.deptno, d.dname, e.empno, e.ename, e.job, e.sal
FROM emp e INNER JOIN dept d ON e.deptno = d.deptno
ORDER BY 1, 4;