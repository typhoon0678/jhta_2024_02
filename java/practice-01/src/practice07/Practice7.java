package practice07;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Practice7 {

    static String rootPath = "/Users/mac/Downloads/";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        problem1();
        problem2();
        problem3();
        problem4();
        problem5();
        problem6();
        problem7();
        problem8();
        problem9();
        problem10();
        problem11();

    }

    static void problem1() {
        FileOutputStream fileOutputStream = null;
        String nameAndPhone;

        try {
            fileOutputStream = new FileOutputStream(rootPath + "phone.txt");

            while (true) {
                System.out.print("이름, 전화번호 : ");
                nameAndPhone = scanner.nextLine();
                if (nameAndPhone.equals("그만")) break;

                fileOutputStream.write((nameAndPhone + "\r\n").getBytes());
            }

            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void problem2() {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(rootPath + "phone.txt");

            int c;
            while ((c = fileReader.read()) != -1) {
                System.out.print((char) c);
            }

            fileReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void problem3() {
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(rootPath + "test.txt");

            int c;
            while ((c = fileReader.read()) != -1) {
                System.out.println((Character.toUpperCase((char) c)));
            }
            fileReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void problem4() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(rootPath + "test.txt");
            bufferedReader = new BufferedReader(fileReader, 16 * 1024);

            String str;
            int line = 1;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.printf("%4d: %s\n", line++, str);
            }

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void problem5() {
        FileReader fileReader = null;

        ArrayList<Integer>[] files = new ArrayList[2];
        String[] fileNames = {"elvis01.txt", "elvis02.txt"};

        try {
            for (int i=0; i<2; i++) {
                files[i] = new ArrayList<Integer>();
                fileReader = new FileReader(rootPath + fileNames[i]);

                int c;
                while ((c = fileReader.read()) != -1) {
                    files[i].add(c);
                }
            }

            if (files[0].equals(files[1])) System.out.println("파일이 서로 같습니다.");
            else System.out.println("파일이 서로 다릅니다.");

            fileReader.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void problem6() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        String[] fileNames = {"elvis01.txt", "elvis02.txt"};
        String newName = fileNames[0].split("\\.")[0] + " + " + fileNames[1].split("\\.")[0];

        try {
            printWriter = new PrintWriter(rootPath + newName + ".txt");

            for (int i=0; i<2; i++) {
                fileReader = new FileReader(rootPath + fileNames[i]);
                bufferedReader = new BufferedReader(fileReader, 16 * 1024);

                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    printWriter.println(str);
                }
            }

            fileReader.close();
            bufferedReader.close();
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void problem7() {
        String fileName = "a.jpeg";
        String copyName = fileName.split("\\.")[0] + "-copy." + fileName.split("\\.")[1];
        File file = new File(rootPath + fileName);
        File newFile = new File(rootPath + copyName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);

            System.out.println(fileName + "를 " + copyName + "로 복사합니다.");
            System.out.println("10%마다 * 를 출력합니다.");

            byte[] buffer = new byte[1024];

            int readData;
            long perByte = file.length() / 10;
            long copyByte = 0;
            while((readData = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, readData);

                copyByte += 1024;
                if (copyByte >= perByte) {
                    System.out.print("* ");
                    copyByte -= perByte;
                }

                TimeUnit.MILLISECONDS.sleep(10);
            }
            System.out.println();

            fileInputStream.close();
            fileOutputStream.close();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void problem8() {
        File folder = new File(rootPath);
        String fileName = null;
        long maxSize = 0;

        try {
            for (File file : folder.listFiles()) {
                if (file.isFile() && file.length() > maxSize) {
                    fileName = file.getName();
                    maxSize = file.length();
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        System.out.printf("가장 큰 파일은 %s이다. 크기는 %d바이트\n", fileName, maxSize);
    }

    static void problem9() {
        File folder = new File(rootPath + "temp/");

        try {
            for (File file : folder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getName() + "삭제");
                    file.delete();
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    static void problem10() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        HashMap<String, String> phones = new HashMap<>();

        try {
            fileReader = new FileReader(rootPath + "phone.txt");
            bufferedReader = new BufferedReader(fileReader, 16 * 1024);

            String str;
            String[] temp;

            while ((str = bufferedReader.readLine()) != null) {
                temp = str.split(" ");
                phones.put(temp[0], temp[1]);
            }

            System.out.println(phones.size() + "개의 전화번호를 읽었습니다.");

            String name;

            while (true) {
                System.out.print("이름 : ");
                name = scanner.nextLine();

                if (name.equals("-1")) break;
                else System.out.println(phones.getOrDefault(name, "저장되지 않은 이름입니다."));
            }

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void problem11() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        List<String> words = new Vector<>();

        try {
            fileReader = new FileReader(rootPath + "words.txt");
            bufferedReader = new BufferedReader(fileReader, 16 * 1024);

            String str;
            String[] temp;
            while ((str = bufferedReader.readLine()) != null) {
                words.add(str);
            }

            String start;
            while (true) {
                System.out.print("시작 단어 입력 >> ");
                start = scanner.nextLine();

                if (start.equals("-1")) break;
                else {
                    for (String word : words) {
                        if (word.startsWith(start)) System.out.println(word);
                    }
                }
            }

            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
