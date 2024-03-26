package Practice01;

import java.util.Scanner;

public class Practice1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Problem 1
        System.out.print("원화를 입력하세요(단위 원) >> ");
        int won = scanner.nextInt();

        System.out.printf("%d원은 $%.1f입니다.\n\n", won, won / (double) 1300);

        // Problem 2
        System.out.print("2자리수 정수 입력(10~99) >> ");
        int num = scanner.nextInt();

        if (num / 10 == num % 10) {
            System.out.println("Yes! 10의 자리와 1의 자리가 같습니다.\n");
        } else {
            System.out.println("No, 10의 자리와 1의 자리가 다릅니다.\n");
        }

        // Problem 3
        System.out.print("금액을 입력하시오 >> ");
        int money = scanner.nextInt();

        System.out.println("오만 원권 " + money / 50000 + "매");
        money %= 50000;
        System.out.println("만 원권 " + money / 10000 + "매");
        money %= 10000;
        System.out.println("천 원권 " + money / 1000 + "매");
        money %= 1000;
        System.out.println("100원 동전 " + money / 100 + "개");
        money %= 100;
        System.out.println("100원 동전 " + money / 50 + "개");
        money %= 50;
        System.out.println("100원 동전 " + money / 10 + "개\n");

        // Problem 4
        System.out.print("정수 3개 입력 >> ");
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();
        int middle;

        if (n1 < n2) {
            if (n2 < n3) middle = n2;
            else middle = Math.max(n1, n3);
        } else {
            if (n1 < n3) middle = n1;
            else middle = Math.max(n2, n3);
        }
        System.out.println("중간 값은 " + middle + "\n");

        // Problem 5
        System.out.print("정수 3개 입력 >> ");
        int a1 = scanner.nextInt();
        int a2 = scanner.nextInt();
        int a3 = scanner.nextInt();
        int max;

        if (a1 < a2) {
            max = Math.max(a2, a3);
        } else {
            max = Math.max(a1, a3);
        }

        if (a1 + a2 + a3 - max > max) System.out.println("삼각형이 됩니다.\n");
        else System.out.println("삼각형이 되지 않습니다.\n");

        // Problem 6
        System.out.print("1~99 사이의 정수를 입력하시오 >> ");
        int game369 = scanner.nextInt();

        if (game369 / 10 != 0 && game369 / 10 % 3 == 0) {
            if (game369 % 10 != 0 && game369 % 10 % 3 == 0) System.out.println("박수짝짝\n");
            else System.out.println("박수짝\n");
        } else {
            if (game369 % 10 != 0 && game369 % 10 % 3 == 0) System.out.println("박수짝\n");
            else System.out.println("X\n");
        }

        // Problem 7
        System.out.print("달을 입력하세요(1~12) >> ");
        int month = scanner.nextInt();

        System.out.println("If");

        if (month <= 2) System.out.println("겨울");
        else if (month <= 5) System.out.println("봄");
        else if (month <= 8) System.out.println("여름");
        else if (month <= 11) System.out.println("가을");
        else System.out.println("겨울");

        System.out.println("Switch");

        switch (month) {
            case 12, 1, 2:
                System.out.println("겨울\n");
                break;
            case 3, 4, 5:
                System.out.println("봄\n");
                break;
            case 6, 7, 8:
                System.out.println("여름\n");
                break;
            case 9, 10, 11:
                System.out.println("가을\n");
                break;
        }

        // Problem 8
        System.out.print("연산 (예시 : 2 + 4) >> ");

        double cal1 = scanner.nextDouble();
        String calS = scanner.next();
        double cal2 = scanner.nextDouble();

        switch (calS) {
            case "+" -> System.out.printf("%f+%f의 계산 결과는 %f\n", cal1, cal2, cal1 + cal2);
            case "-" -> System.out.printf("%f-%f의 계산 결과는 %f\n", cal1, cal2, cal1 - cal2);
            case "*" -> System.out.printf("%f*%f의 계산 결과는 %f\n", cal1, cal2, cal1 * cal2);
            case "/" -> {
                if (cal2 == 0) {
                    System.out.println("0으로 나눌 수 없습니다.\n");
                } else {
                    System.out.printf("%f/%f의 계산 결과는 %f\n", cal1, cal2, cal1 / cal2);
                }
            }
        }
    }
}
