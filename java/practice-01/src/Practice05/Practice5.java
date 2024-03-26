package Practice05;

import Practice05.base.Shape;
import Practice05.etc.Calc;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Practice5 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Problem 1
        MyPoint myPoint = new MyPoint(3, 50);
        System.out.println(myPoint);
        if ((myPoint.equals(new MyPoint(10, 20)))) {
            System.out.println("같은 점");
        } else {
            System.out.println("다른 점");
        }
        System.out.println();


        // Problem 2
        Circle a = new Circle(2, 3, 5);
        Circle b = new Circle(2, 3, 30);
        System.out.println("원 a : " + a);
        System.out.println("원 b : " + b);
        if (a.equals(b)) {
            System.out.println("같은 원");
        } else {
            System.out.println("다른 원");
        }
        System.out.println();

        // Problem 3
        Calc c = new Calc(10, 20);
        System.out.println(c.sum());
        System.out.println();

        // Problem 4
        Shape shape = new Practice05.draw.Circle();
        shape.draw();
        System.out.println();

        // Problem 5

        // Problem 6

        // Problem 7
        StringTokenizer stringTokenizer;
        int count;

        while (true) {
            System.out.print(">> ");
            stringTokenizer = new StringTokenizer(scanner.nextLine(), " ");

            count = stringTokenizer.countTokens();

            if (stringTokenizer.nextToken().equals("그만")) {
                System.out.println("종료합니다...");
                break;
            }
            else System.out.println("어절 개수는 " + count);
        }

        // Problem 8
        System.out.println("문자열을 입력하세요. 빈칸이 있어도 되고 영어 한글 모두 됩니다.");
        String spin = scanner.nextLine();
        for (int i=0; i<spin.length(); i++) {
            spin = spin.substring(1) + spin.charAt(0);
            System.out.println(spin);
        }

        // Problem 9
        int select, computer;
        String[] game = {"가위", "바위", "보"};

        while (true) {
            System.out.print("\n철수[가위(1), 바위(2), 보(3), 끝내기(4)] >> ");
            select = scanner.nextInt();

            if (select == 4) break;
            else {
                computer = (int) (Math.random() * 3 + 1);
                System.out.printf("철수(%s) : 컴퓨터(%s)\n",game[select - 1], game[computer - 1]);

                if (select == computer) System.out.println("무승부입니다.");
                else if ((3 + select - computer) % 3 == 1) System.out.println("철수가 이겼습니다.");
                else System.out.println("컴퓨터가 이겼습니다.");
            }
        }

        // Problem 10
        Person[] person = new Person[2];

        for (int i=0; i<2; i++) {
            System.out.print((i+1) + "번째 선수 이름 >> ");
            person[i] = new Person(scanner.nextLine());
        }

        outerLoop :
        while (true) {
            for (int i=0; i<2; i++) {
                if (person[i].run()) break outerLoop;
            }
        }

        // Problem 11
        System.out.print(">> ");
        StringBuffer sentence = new StringBuffer(scanner.nextLine());
        StringTokenizer command;
        String checkString;
        int len, size;

        outerLoop :
        while (true) {
            System.out.print("명령: ");
            command = new StringTokenizer(scanner.nextLine(), "!");

            size = command.countTokens();
            checkString = command.nextToken();
            len = checkString.length();

            if (checkString.equals("그만")) break;
            else if (size != 2) {
                System.out.println("잘못된 명령입니다!");
                continue;
            }

            for (int i=0; i<=sentence.length() - len; i++) {
                if (sentence.substring(i, i + len).equals(checkString)) {
                    sentence.replace(i, i + len, command.nextToken());
                    System.out.println("명령: " + sentence);
                    continue outerLoop;
                }
            }

            System.out.println("찾을 수 없습니다!");
        }

        // Problem 12
        System.out.print("겜블링 게임에 참여할 선수 숫자 >> ");
        int n = scanner.nextInt();
        scanner.nextLine();
        Person[] persons = new Person[n];

        for (int i=0; i<n; i++) {
            System.out.print((i+1) + "번째 선수 이름 >> ");
            persons[i] = new Person(scanner.nextLine());
        }

        outerLoop :
        while (true) {
            for (int i=0; i<n; i++) {
                if (persons[i].run()) break outerLoop;
            }
        }
    }
}

class MyPoint {
    private final int x;
    private final int y;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point(" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        MyPoint point = (MyPoint) obj;
        return this.x == point.x && this.y == point.y;
    }
}


class Person {
    private final String name;
    private final int[] gamble;
    private final Scanner scanner = new Scanner(System.in);

    public Person(String name) {
        this.name = name;
        this.gamble = new int[3];
    }

    public boolean run() {
        int rand;

        System.out.printf("[%s]: ", this.name);
        scanner.nextLine();
        System.out.println("<Enter>");

        for (int i=0; i<3; i++) {
            rand = (int) (Math.random() * 3 + 1);
            gamble[i] = rand;
            System.out.print(rand + "        ");
        }

        if (gamble[0] == gamble[1] && gamble[1] == gamble[2]) {
            System.out.println(this.name + "님이 이겼습니다!");
            return true;
        } else {
            System.out.println("아쉽군요!");
            return false;
        }
    }
}