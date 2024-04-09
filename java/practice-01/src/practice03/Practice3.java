package practice03;

import java.util.Arrays;
import java.util.Scanner;

public class Practice3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Problem 1
        TV myTV = new TV("LG", 2017, 32);
        myTV.show();

        // Problem 2
        System.out.print("\n수학, 과학, 영어 순으로 3개의 정수 입력 >> ");
        int math = scanner.nextInt();
        int science = scanner.nextInt();
        int english = scanner.nextInt();
        Grade me = new Grade(math, science, english);
        System.out.printf("평균은 %.1f\n\n", me.average());

        // Problem 3
        Song song = new Song("Dancing Queen", "ABBA", 1978, "스웨덴");
        song.show();

        // Problem 4
        Rectangle r = new Rectangle(2, 2, 8, 7);
        Rectangle s = new Rectangle(5, 5, 6, 6);
        Rectangle t = new Rectangle(1, 1, 10, 10);

        r.show();
        System.out.println("s의 면적은 "+s.square());
        if(t.contains(r)) System.out.println("t는 r을 포함합니다.");
        if(t.contains(s)) System.out.println("t는 s를 포함합니다.\n");

        // Problem 5
        Circle[] c = new Circle[3]; // 3개의 Practice03.Circle 배열 선언
        for(int i=0; i<3; i++) {
            System.out.print("x, y, radius >> ");
            double x = scanner.nextDouble(); // x값 읽기.
            double y = scanner.nextDouble(); // y값 읽기.
            int radius = scanner.nextInt(); // radius 값 읽기.
            c[i] = new Circle(x, y, radius); // Practice03.Circle 객체 생성
        }
        for (Circle circle : c) circle.show(); // 모든 Practice03.Circle 객체 출력

        // Problem 6
        int max = 0;
        int maxIndex = 0;
        for (int i=0; i<3; i++) {
            if(c[i].getRadius() > max) maxIndex = i;
        }

        System.out.print("\n가장 면적이 큰 원은 ");
        c[maxIndex].show();

        // Problem 7
        MonthSchedule april = new MonthSchedule(30);
        april.run();

        // Problem 8
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.search();

        // Problem 9
        int[] array1 = {1, 5, 7, 9};
        int[] array2 = {3, 6, -1, 100, 77};
        int[] array3 = ArrayUtil.concat(array1, array2);
        ArrayUtil.print(array3);

        // Problem 10
        String word, result;

        System.out.println("\n한영 단어 검색 프로그램입니다.");
        while (true) {
            System.out.print("한글 단어? >> ");
            word = scanner.nextLine();
            if (word.equals("그만")) break;
            result = Dictionary.kor2Eng(word);

            if (result.equals("Not Equal")) {
                System.out.println(word + "는 저의 사전에 없습니다.");
            } else {
                System.out.println(word + "는 " + result);
            }
        }

        // Problem 11
        String[] input;
        System.out.print("\n두 정수와 연산자를 입력하시요 >> ");
        input = scanner.nextLine().split(" ");

        switch (input[2]) {
            case "+" -> {
                Add cal = new Add();
                cal.setValue(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                System.out.println(cal.calculate());
            }
            case "-" -> {
                Sub cal = new Sub();
                cal.setValue(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                System.out.println(cal.calculate());
            }
            case "*" -> {
                Mul cal = new Mul();
                cal.setValue(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                System.out.println(cal.calculate());
            }
            default -> {
                Div cal = new Div();
                cal.setValue(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                System.out.println(cal.calculate());
            }
        }

        // Problem 12
        Reservation reservation = new Reservation();
        reservation.run();

    }
}

class TV {
    String name;
    int year;
    int inch;

    TV(String name, int year, int inch) {
        this.name = name;
        this.year = year;
        this.inch = inch;
    }

    public void show() {
        System.out.println(this.name + "에서 만든 " + this.year + "년형 " + this.inch + "인치 Practice03.TV");
    }
}

class Grade {
    private final int math;
    private final int science;
    private final int english;

    Grade(int math, int science, int english) {
        this.math = math;
        this.science = science;
        this.english = english;
    }

    public double average() {
        return (this.math + this.science + this.english) / 3.0;
    }
}

class Song {
    String title;
    String artist;
    int year;
    String country;

    Song(String title, String artist, int year, String country) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.country = country;
    }

    public void show() {
        System.out.println(this.year + "년 " + this.country + "국적의 " + this.artist + "가 부른 " + this.title + "\n");
    }
}

class Rectangle {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int square() {
        return width*height;
    }

    public void show() {
        System.out.printf("{%d,%d}에서 크기가 %dx%d인 사각형\n", x ,y, width, height);
    }

    public boolean contains(Rectangle r) {
        return (this.x <= r.x && this.y <= r.y &&
                this.x+this.width >= r.x+r.width &&
                this.y+this.height >= r.y+r.height);
    }
}

class Circle {
    private final double x, y;
    private final int radius;
    public Circle(double x, double y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public void show() {
        System.out.printf("(%.1f,%.1f)%d\n\n", x, y, radius);
    }

    public int getRadius() {
        return this.radius;
    }
}

class Day {
    private String work; //하루의 할 일을 나타내는 문자열
    public void set(String work) { this.work = work; }
//    public String get() { return work; }
    public void show() {
        if(work == null) System.out.println("없습니다.\n");
        else System.out.println(work+"입니다.\n");
    }
}

class MonthSchedule {
    int day;
    Day[] schedule;

    MonthSchedule(int day) {
        this.day = day;
        this.schedule = new Day[day];
        for (int i=0; i<day; i++) {
            this.schedule[i] = new Day();
        }
    }

    private void input(int day, String work) {
        this.schedule[day-1].set(work);
        System.out.println();
    }

    private void view(int day) {
        System.out.printf("%d일의 할 일은 ", day);
        this.schedule[day-1].show();
    }

    private void finish() {
        System.out.println("프로그램을 종료합니다.\n");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("이번달 스케쥴 관리 프로그램.");
        int select, day;
        String work;
        while (true) {
            System.out.print("할일(입력:1, 보기:2, 끝내기:3) >> ");
            select = scanner.nextInt();

            if (select == 3) {
                this.finish();
                break;
            }
            else {
                System.out.print("날짜(1~30)? ");
                day = scanner.nextInt();
                scanner.nextLine();

                if (select == 1) {
                    System.out.print("할일(빈칸없이입력)? ");
                    work = scanner.nextLine();
                    this.input(day, work);
                } else if (select == 2) {
                    this.view(day);
                }
            }
        }
    }
}

class Phone {
    private final String name;
    private final String number;

    Phone(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber (String name) {
        if (name.equals(this.name)) {
            return this.number;
        } else {
            return "Not Equal";
        }
    }
}

class PhoneBook {
    private final Phone[] phones;
    private final Scanner scanner;

    PhoneBook () {
        scanner = new Scanner(System.in);
        String[] info;
        System.out.print("인원수 >> ");
        int len = scanner.nextInt();
        scanner.nextLine();
        this.phones = new Phone[len];

        for (int i=0; i<len; i++) {
            System.out.print("이름과 전화번호(이름과 번호는 빈 칸없이 입력) >> ");
            info = scanner.nextLine().split(" ");
            this.setPhone(i, info[0], info[1]);
        }
        System.out.println("저장되었습니다...");
    }

    private void setPhone(int index, String name, String number) {
        this.phones[index] = new Phone(name, number);
    }

    public void search() {
        String name, result;
        int count;

        while (true) {
            System.out.print("검색할 이름 >> ");
            name = scanner.nextLine();

            if (name.equals("그만")) break;
            else {
                count = 0;

                for (Phone phone : phones) {
                    result = phone.getNumber(name);
                    if (!result.equals("Not Equal")) {
                        System.out.println(name + "의 번호는 " + result + " 입니다.");
                        count++;
                    }
                }

                if (count == 0) System.out.println(name + "이 없습니다.");
            }
        }

    }
}

class ArrayUtil {
    public static int[] concat(int[] a, int[] b) {
        int aLen = a.length, bLen = b.length;
        int[] result = new int[aLen + bLen];

        System.arraycopy(a, 0, result, 0, aLen);
        System.arraycopy(b, 0, result, aLen, bLen);

        return result;
    }

    public static void print(int[] a) {
        System.out.println(Arrays.toString(a) + "\n");
    }
}

class Dictionary {
    private static final String[] kor = {"사랑", "아기", "돈", "미래", "희망"};
    private static final String[] eng = {"love", "baby", "money", "future","hope"};
    public static String kor2Eng(String word) {
        for (int i=0; i<kor.length; i++) {
            if (word.equals(kor[i])) {
                return eng[i];
            }
        }

        return "Not Exist";
    }
}

class Add {
    private int a;
    private int b;

    public void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int calculate() {
        return this.a + this.b;
    }
}

class Sub {
    private int a;
    private int b;

    public void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int calculate() {
        return this.a - this.b;
    }
}

class Mul {
    private int a;
    private int b;

    public void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int calculate() {
        return this.a * this.b;
    }
}

class Div {
    private int a;
    private int b;

    public void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int calculate() {
        try {
            return this.a / this.b;
        } catch (Exception e) {
            return 0;
        }
    }
}

class Reservation {
    // S, A, B 좌석
    private final String[][] seats;
    private final Scanner scanner;

    Reservation() {
        this.seats = new String[3][10];
        scanner = new Scanner(System.in);
    }

    public void run() {
        int select;

        System.out.print("\n명품콘서트홀 예약 시스템입니다.");

        while (true) {
            try {
                System.out.print("\n예약:1, 조회:2, 취소:3, 끝내기:4 >> ");
                select = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요");
                continue;
            }

            if (select == 4) break;
            else if (select == 1) {
                this.reserve();
            } else if (select == 2) {
                this.check();
            } else if (select == 3) {
                this.cancel();
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    private void reserve() {
        int seat;
        String name;
        int num;
        int count;

        System.out.print("좌석구분 S(1), A(2), B(3) >> ");
        seat = scanner.nextInt();
        scanner.nextLine();

        count = check(seat);

        if (count == 10) {
            System.out.println("\n예약할 수 있는 자리가 없습니다.");
        } else {
            System.out.print("\n이름 >> ");
            name = scanner.nextLine();
            while (true) {
                System.out.print("번호 >> ");
                num = scanner.nextInt();
                scanner.nextLine();

                if (this.seats[seat-1][num-1] != null) {
                    System.out.println("다른 자리를 입력해주세요.");
                } else {
                    this.seats[seat-1][num-1] = name;
                    break;
                }
            }
        }

    }

    private int check(int seat) {
        int count = 0;
        System.out.print((seat == 1) ? "S >>" : (seat == 2) ? "A >>" : "B >>");

        for (String name : this.seats[seat-1]) {
            if (name == null) System.out.print("  ______");
            else {
                System.out.print("  " + name);
                count++;
            }
        }

        return count;
    }

    private void check() {
        for (int i=1; i<=3; i++) {
            this.check(i);
            System.out.println();
        }
        System.out.println("<<<조회를 완료하였습니다.>>>");
    }

    private void cancel() {
        int seat;
        String name;
        int count = 0;

        System.out.print("좌석 S:1, A:2, B:3 >> ");
        seat = scanner.nextInt();
        scanner.nextLine();

        if (check(seat) == 0) System.out.println("\n취소할 자리가 없습니다.");
        else {
            while (true) {
                System.out.print("\n이름 >> ");
                name = scanner.nextLine();

                for (int i=0; i<10; i++) {
                    if (seats[seat-1][i] != null && seats[seat-1][i].equals(name)) {
                        seats[seat-1][i] = null;
                        count++;
                        break;
                    }
                }

                if (count > 0) break;
                else System.out.print("이름을 다시 입력해주세요.");
            }
        }
    }

}