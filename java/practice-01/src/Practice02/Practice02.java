package Practice02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Practice02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Problem 1
        int result = 0;
        for (int i=1; i< 100; i++) {
            if (i%2 == 0) result += i;
        }
        System.out.println("100보다 작은 짝수의 합 for문 : " + result);

        result = 0;
        int count = 1;
        while (count <= 100) {
            if (count % 2 == 0) result += count;
            count++;
        }
        System.out.println("100보다 작은 짝수의 합 while문 : " + result + "\n");

        // Problem 2
        int[][] n = {{1}, {1,2,3}, {1}, {1,2,3,4}, {1,2}};

        for (int[] a : n) {
            for (int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }

        // Problem 3
        System.out.print("\n정수를 입력하시오 >> ");
        int n3 = scanner.nextInt();

        for (int i=n3; i>0; i--) {
            for (int j=i; j>0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Problem 4
        scanner.nextLine();
        System.out.print("\n소문자 알파벳 하나를 입력하시오 >> ");
        int c = scanner.nextLine().charAt(0);

        for (int i=0; i<=c-97; i++) {
            for (int j=97; j<=c-i; j++) {
                System.out.print((char) (j));
            }
            System.out.println();
        }

        // Problem 5
        System.out.print("\n양의 정수 10개를 입력하세요 >> ");
        int[] nums = new int[10];

        for (int i=0; i<10; i++) {
            nums[i] = scanner.nextInt();
        }

        for (int num : nums) {
            if (num % 3 == 0) System.out.print(num + " ");
        }

        // Problem 6
        System.out.print("\n\n금액을 양의 정수로 입력하세요 >> ");
        int price = scanner.nextInt();
        int[] unit = {50000, 10000, 5000, 1000, 500, 100, 50};

        for (int u : unit) {
            System.out.println(u + "원 " + price/u + "개");
            price %= u;
        }
        System.out.println();

        // Problem 7

        int[] randInt = new int[10];
        int sum = 0;

        for (int i=0; i<10; i++) {
            randInt[i] = (int)(Math.random()*10+1);
            sum += randInt[i];
        }

        for (int num : randInt) {
            System.out.print(num + " ");
        }

        System.out.println("\n평균 : " + sum / (double) 10);

        // Problem 8
        System.out.print("\n정수 몇 개 ? >> ");
        int n4 = scanner.nextInt();
        int[] nums2 = new int[n4];
        ArrayList<Integer> randomList = new ArrayList<>();
        int index;

        for (int i=1; i<=100; i++) {
            randomList.add(i);
        }

        for (int i=0; i<n4; i++) {
            index = (int) (Math.random()*(100-i));
            nums2[i] = randomList.get(index);
            randomList.remove(index);
        }

        System.out.println(Arrays.toString(nums2));
        System.out.println("\n");

        scanner.nextLine();

        // Problem 9
        int com;
        while (true) {
            System.out.print("가위 바위 보! >> ");
            String option = scanner.nextLine();

            com = (int) (Math.random() * 3);

            if (option.equals("그만")) {
                System.out.println("게임을 종료합니다...");
                break;
            } else {
                System.out.print("사용자 = " + option + ", 컴퓨터 = ");

                if (option.equals("가위")) {
                    if (com == 0) System.out.println("가위, 비겼습니다\n");
                    else if (com == 1) System.out.println("바위, 컴퓨터가 이겼습니다\n");
                    else System.out.println("보, 사용자가 이겼습니다\n");
                } else if (option.equals("바위")) {
                    if (com == 0) System.out.println("가위, 사용자가 이겼습니다\n");
                    else if (com == 1) System.out.println("바위, 비겼습니다\n");
                    else System.out.println("보, 컴퓨터가 이겼습니다\n");
                } else if (option.equals("보")) {
                    if (com == 0) System.out.println("가위, 컴퓨터가 이겼습니다\n");
                    else if (com == 1) System.out.println("바위, 사용자가 이겼습니다\n");
                    else System.out.println("보, 비겼습니다\n");
                } else {
                    System.out.println("다시 입력해주세요\n");
                }
            }
        }
    }
}
