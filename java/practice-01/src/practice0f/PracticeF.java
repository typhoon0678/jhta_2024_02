package practice0f;

import java.util.*;
import java.util.stream.Collectors;

public class PracticeF {

    public static void main(String[] args) {
        new Problem1();
        new Problem2();
        new Problem3();
    }
}

class Problem1 {

    public Problem1() {

        List<String> fruitList = Arrays.asList("apple", "banana", "apple", "mango", "strawberry", "banana", "mango");

        // Supplier, Consumer, Predicate, Function
        fruitList.stream().filter(fruit -> fruit.startsWith("a")).distinct().forEach(System.out::println);
    }
}

class Employee {
    private String name;
    private int age;
    private String dept;
    private int salary;

    public Employee(String name, int age, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class Problem2 {

    public Problem2() {
        List<Employee> empList = Arrays.asList(
                new Employee("홍길동", 30, "총무부", 2000000),
                new Employee("이순신", 32, "회계부", 3000000),
                new Employee("유재석", 40, "인사부", 4000000),
                new Employee("손흥민", 26, "개발부", 2400000),
                new Employee("정준하", 28, "회계부", 2700000)
        );

        OptionalDouble optionalDouble = empList.stream().mapToInt(Employee::getSalary).average();

        if (optionalDouble.isPresent()) {
            System.out.println(optionalDouble.getAsDouble());
        }
    }
}

class Problem3 {

    public Problem3() {
        List<Employee> empList = Arrays.asList(
                new Employee("홍길동", 30, "총무부", 2000000),
                new Employee("이순신", 32, "회계부", 3000000),
                new Employee("유재석", 40, "인사부", 4000000),
                new Employee("손흥민", 26, "개발부", 2400000),
                new Employee("정준하", 28, "회계부", 2700000)
        );

        List<Employee> empFilterList = empList.stream().filter(employee -> employee.getDept().equals("회계부")).toList();
        empFilterList.forEach(System.out::println);

        Map<String, List<Employee>> groupList = empList.stream().collect(Collectors.groupingBy(Employee::getDept));

        groupList.forEach((dept, employees) -> {
            System.out.print("\n" + dept + " : ");
            employees.forEach(employee -> System.out.print(employee.getName() + " "));
        });
    }
}