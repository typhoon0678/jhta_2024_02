package Practice06;

import java.util.*;

public class Practice6 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Problem 1
        int n;
        List<Integer> numVector = new Vector<>();

        System.out.print("정수(-1이 입력 될 때 까지) >> ");
        while (true) {
            n = scanner.nextInt();
            if (n == -1) break;
            else numVector.add(n);
        }
        scanner.nextLine();

        int max = 0;
        for (int num : numVector) {
            if (num > max) max = num;
        }

        System.out.println("가장 큰 수는 " + max);

        // Problem 2
        List<String> gradeArray;

        System.out.print("6개의 학점을 빈 칸으로 분리 입력(A/B/C/D/F) >> ");
        gradeArray = Arrays.stream(scanner.nextLine().split(" ")).toList();

        int sum = 0;

        for (String grade : gradeArray) {
            switch (grade) {
                case "A" -> sum += 4;
                case "B" -> sum += 3;
                case "C" -> sum += 2;
                case "D" -> sum += 1;
                default -> sum += 0;
            }
        }

        System.out.println(sum / (double) 6);

        // Problem 3
        HashMap<String, Integer> countries = new HashMap<>();
        String input;
        String[] inputCountry;

        System.out.println("나라 이름과 인구를 입력하세요. (예 : Korea 5000)");
        while (true) {
            System.out.print("나라 이름, 인구 >> ");
            input = scanner.nextLine();

            if (input.equals("그만")) break;
            else {
                inputCountry = input.split(" ");
                countries.put(inputCountry[0], Integer.parseInt(inputCountry[1]));
            }
        }


        while (true) {
            System.out.print("인구 검색 >> ");
            input = scanner.nextLine();

            if (input.equals("그만")) break;
            else {
                if (countries.containsKey(input)) {
                    System.out.println(input + "의 인구는 " + countries.get(input));
                } else {
                    System.out.println(input + " 나라는 없습니다.");
                }
            }
        }

        // Problem 4
        int rain, rainSum = 0;
        List<Integer> rainVector = new Vector<>();

        while (true) {
            System.out.print("강수량 입력 (0 입력시 종료) >> ");
            rain = scanner.nextInt();

            if (rain == 0) {
                scanner.nextLine();
                break;
            }

            rainVector.add(rain);
            rainSum += rain;

            for (int r : rainVector) {
                System.out.print(r + " ");
            }

            System.out.println("\n현재 평균 " + rainSum / rainVector.size());
        }

        // Problem 5-1
        System.out.println("학생 이름, 학과, 학점 평균을 입력하세요.");

        List<Student> students = new ArrayList<>();
        String[] studentInput;
        String name;
        boolean isContains;

        for (int i=0; i<4; i++) {
            System.out.print(">> ");
            studentInput = scanner.nextLine().split(",");
            students.add(new Student(studentInput[0].trim(), studentInput[1].trim(),
                    Integer.parseInt(studentInput[2].trim()), Double.parseDouble(studentInput[3].trim())));
        }

        for (Student s : students) {
            System.out.println("-------------------------------");
            s.show();
        }
        System.out.println("-------------------------------");

        while (true) {
            System.out.print("학생 이름 >> ");
            name = scanner.nextLine();
            isContains = false;

            if (name.equals("그만")) break;
            else {
                for (Student s : students) {
                    if (s.getName().equals(name)) {
                        s.showLine();
                        isContains = true;
                        break;
                    }
                }
            }

            if (!isContains) System.out.println(name + " 학생은 목록에 없습니다.");
        }

        // Problem 5-2
        System.out.println("학생 이름, 학과, 학점 평균을 입력하세요.");

        HashMap<String, Student> studentMap = new HashMap<>();

        for (int i=0; i<4; i++) {
            System.out.print(">> ");
            studentInput = scanner.nextLine().split(",");
            studentMap.put(studentInput[0].trim(),
                    new Student(studentInput[0].trim(), studentInput[1].trim(),
                    Integer.parseInt(studentInput[2].trim()), Double.parseDouble(studentInput[3].trim())));
        }

        for (Map.Entry<String, Student> s : studentMap.entrySet()) {
            System.out.println("-------------------------------");
            s.getValue().show();
        }
        System.out.println("-------------------------------");

        while (true) {
            System.out.print("학생 이름 >> ");
            name = scanner.nextLine();

            if (name.equals("그만")) break;
            else {
                if (studentMap.containsKey(name)) {
                    studentMap.get(name).showLine();
                } else {
                    System.out.println(name + " 학생은 목록에 없습니다.");
                }
            }
        }

        // Problem 6
        Map<String, Location> cities = new HashMap<>();
        String[] cityInput;
        String cityName;

        System.out.println("도시 이름, 위도, 경도를 입력하세요.");
        for (int i=0; i<4; i++) {
            System.out.print(">> ");
            cityInput = scanner.nextLine().split(",");

            cities.put(cityInput[0].trim(), new Location(cityInput[0].trim(),
                    Integer.parseInt(cityInput[1].trim()), Integer.parseInt(cityInput[2].trim())));
        }

        System.out.println("------------------------");
        for (Map.Entry<String, Location> city : cities.entrySet()) {
            city.getValue().show();
        }
        System.out.println("------------------------");

        while (true) {
            System.out.print("도시 이름 >> ");
            cityName = scanner.nextLine();

            if (cityName.equals("그만")) break;
            else if (cities.containsKey(cityName)) cities.get(cityName).show();
            else System.out.println(cityName + "는 없습니다.");
        }

        // Problem 7
        Map<String, Double> demy = new HashMap<>();
        String[] sInput;
        double cutLine;

        System.out.println("미래장학금관리스스템입니다.");
        for (int i=0; i<5; i++) {
            System.out.print("이름과 학점 >> ");
            sInput = scanner.nextLine().split(" ");

            demy.put(sInput[0], Double.parseDouble(sInput[1]));
        }

        System.out.print("장학금 선발 학점 기준 입력 >> ");
        cutLine = scanner.nextDouble();

        for (Map.Entry<String, Double> student : demy.entrySet()) {
            if (student.getValue() >= cutLine) System.out.print(student.getKey() + " ");
        }

        // Problem 8
        Map<String, Integer> points = new HashMap<>();
        String menu;
        String[] menus;

        System.out.println("** 포인트 관리 프로그램입니다. **");

        while (true) {
            System.out.print("이름과 포인트 입력 >> ");
            menu = scanner.nextLine();

            if (menu.equals("그만")) break;
            menus = menu.split(" ");

            if (points.containsKey(menus[0])) points.put(menus[0], points.get(menus[0]) + Integer.parseInt(menus[1]));
            else points.put(menus[0], Integer.parseInt(menus[1]));

            for (Map.Entry<String, Integer> point : points.entrySet()) {
                System.out.printf("(%s,%s)", point.getKey(), point.getValue());
            }
            System.out.println();
        }

        // Problem 9
        IStack<Integer> iStack = new MyStack<>();
        int num = 0;
        while (true) {
            if (!iStack.push(num++)) break;
        }

        while (true) {
            Integer pop = iStack.pop();
            if (pop == null) break;
            else System.out.print(pop + " ");
        }
        System.out.println();

        // Problem 10
        List<Nation> countryVector = new Vector<>();
        int select;
        String countryInput;
        String[] countryCity;
        boolean isIn;
        int randomIndex;

        while (true) {
            System.out.print("입력:1, 퀴즈:2, 종료:3 >> ");
            select = scanner.nextInt();
            scanner.nextLine();

            if (select == 3) break;
            else if (select == 1) {

                while (true) {
                    System.out.print("나라와 수도 입력 " + (countryVector.size() + 1) + " >> ");
                    countryInput = scanner.nextLine();

                    if (countryInput.equals("그만")) break;
                    else {
                        countryCity = countryInput.split(" ");
                        isIn = false;

                        for (Nation nation : countryVector) {
                            if (nation.getCountry().equals(countryCity[0])) {
                                System.out.println(countryCity[0] + "는 이미 있습니다!");
                                isIn = true;
                                break;
                            }
                        }

                        if (!isIn) countryVector.add(new Nation(countryCity[0], countryCity[1]));
                    }
                }

            } else if (select == 2) {

                while (true) {
                    randomIndex = (int) (Math.random() * countryVector.size());
                    System.out.print(countryVector.get(randomIndex).getCountry() + "의 수도는? >> ");
                    countryInput = scanner.nextLine();

                    if (countryInput.equals("그만")) break;
                    else countryVector.get(randomIndex).quiz(countryInput);
                }
            }
        }

        // Problem 11
        Vector<Word> v = new Vector<>();

        v.add(new Word("constructor", "생성자"));
        v.add(new Word("overriding", "재정의"));
        v.add(new Word("extends", "상속"));
        v.add(new Word("array", "배열"));
        v.add(new Word("string", "문자열"));
        v.add(new Word("character", "문자"));
        v.add(new Word("integer", "정수"));
        v.add(new Word("double", "실수"));
        v.add(new Word("abstract", "추상"));
        v.add(new Word("implements", "구현하다"));


        WordQuiz wordQuiz = new WordQuiz(v);
        wordQuiz.run();

    }


}

class Student {
    private final String name;
    private final String department;
    private final int id;
    private final double gradeAvg;

    public Student(String name, String department, int id, double gradeAvg) {
        this.name = name;
        this.department = department;
        this.id = id;
        this.gradeAvg = gradeAvg;
    }

    public void show() {
        System.out.println("이름: " + this.name);
        System.out.println("학과: " + this.department);
        System.out.println("학번: " + this.id);
        System.out.println("학점평균: " + this.gradeAvg);
    }

    public void showLine() {
        System.out.printf("%s, %s, %s, %s\n", this.name, this.department, this.id, this.gradeAvg);
    }

    public String getName() {
        return this.name;
    }
}

class Location {
    private final String cityName;
    private final int lat;
    private final int lon;

    public Location(String cityName, int lat, int lon) {
        this.cityName = cityName;
        this.lat = lat;
        this.lon = lon;
    }

    public void show() {
        System.out.println(this.cityName + " " + this.lat + " " + this.lon);
    }
}

interface IStack<T> {
    T pop();

    boolean push(T ob);
}

class MyStack<T> implements IStack<T> {

    private final Vector<T> stack = new Vector<>(10);

    @Override
    public T pop() {
        if (stack.isEmpty()) return null;
        else return stack.removeLast();
    }

    @Override
    public boolean push(T ob) {
        if (stack.size() >= 10) return false;
        else {
            stack.add(ob);
            return true;
        }
    }
}

class Nation {
    private final String country;
    private final String capital;

    public Nation(String country, String capital) {
        this.country = country;
        this.capital = capital;
    }

    public void show() {
        System.out.println(this.country + " " + this.capital);
    }

    public String getCountry() {
        return this.country;
    }

    public void quiz(String capital) {
        if (capital.equals(this.capital)) System.out.println("정답!");
        else System.out.println("아닙니다!!");
    }
}

class Word {
    private final String question;
    private final String answer;

    public Word(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}

class WordQuiz {
    private final Vector<Word> vector;
    private final Scanner scanner;

    public WordQuiz(Vector<Word> vector) {
        this.vector = vector;
        this.scanner = new Scanner(System.in);
    }

    public void add(Word word) {
        vector.add(word);
    }

    public void run() {
        System.out.println("Java 단어 테스트를 시작합니다. -1을 입력하면 종료합니다.");
        System.out.println("현재 " + vector.size() + "개의 단어가 들어 있습니다.");

        int questionIndex, answer, correctIndex;
        ArrayList<String> selectList;
        ArrayList<Integer> randomList;

        while (true) {
            questionIndex = (int) (Math.random() * vector.size());
            System.out.println("---------------------------------------------");
            System.out.println(vector.get(questionIndex).getQuestion() + "?");

            selectList = new ArrayList<>();
            randomList = new ArrayList<>();

            for (int i=0; i<vector.size(); i++) {
                randomList.add(i);
            }
            randomList.remove(questionIndex);
            for (int i=0; i<3; i++) {
                selectList.add(
                        vector.get(randomList.remove((int) (Math.random() * (randomList.size()))))
                                .getAnswer());
            }

            correctIndex = (int) (Math.random() * 4) + 1;
            selectList.add(correctIndex - 1, vector.get(questionIndex).getAnswer());

            for (int i=0; i<selectList.size(); i++) {
                System.out.printf("(%d)%s ", i+1, selectList.get(i));
            }
            System.out.print(">> ");

            answer = scanner.nextInt();

            if (answer == -1) {
                System.out.println("종료합니다.");
                break;
            } else if (answer == correctIndex) System.out.println("Excellent !!");
            else System.out.println("No. !!");
        }
    }
}