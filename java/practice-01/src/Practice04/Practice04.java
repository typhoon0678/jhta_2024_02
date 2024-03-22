package Practice04;

import java.util.Scanner;

public class Practice04 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Problem 1
        ColorTV myTV = new ColorTV(32,1024);
        myTV.printProperty();

        // Problem 2
        IPTV iptv = new IPTV("192.1.1.2", 32, 2048);
        iptv.printProperty();

        // Problem 3
        Won2Dollar toDollar = new Won2Dollar(1200);
        toDollar.run();

        // Problem 4
        Km2Mile toMile = new Km2Mile(1.6);
        toMile.run();

        // Problem 5
        ColorPoint cp = new ColorPoint(5, 5, "Yellow");
        String str1 = cp.toString();
        System.out.println("처음 정의된 colorPoint는 "+str1+"입니다.");
        cp.setXY(10,20);
        cp.setColor("Red");
        String str2 = cp.toString();
        System.out.println("이동한 colorPoint는 "+str2+"입니다.");

        // Problem 6
        Point3D p = new Point3D(1,2,3);
        System.out.println("초기 점의 위치는 "+p.toString()+" 입니다.");
        p.moveUp();
        System.out.println("한 칸 위로 이동한 점은 "+p.toString()+" 입니다.");
        p.moveDown();
        System.out.println("한 칸 아래로 이동한 점은 "+p.toString()+" 입니다.");
        p.move(10, 10);
        System.out.println("x,y좌표가 이동한 점은 "+p.toString()+" 입니다.");
        p.move(100, 200, 300);
        System.out.println("x,y,z좌표가 이동한 점은 "+p.toString()+" 입니다.");

        // Problem 7
        Point3D p2 = new Point3D(1, 2, 3); // 1, 2, 3은 각각 x, y, z축의 값.
        System.out.println(p2.toString() + "입니다.");

        p2.moveUp(); // z 축으로 위쪽 이동
        System.out.println(p2.toString() + "입니다.");

        p2.moveDown(); // z 축으로 아래쪽 이동
        p2.move(10, 10); // x, y 축으로 이동
        System.out.println(p2.toString() + "입니다.");

        p2.move(100, 200, 300); // x, y, z 축으로 이동
        System.out.println(p2.toString() + "입니다.");

        // Problem 8
        PositivePoint p3 = new PositivePoint();
        p3.move(10, 10);
        System.out.println(p3.toString() + "입니다.");

        p3.move(-5, 5); // 객체 p는 음수 공간으로 이동되지 않음
        System.out.println(p3.toString() + "입니다.");

        PositivePoint p4 = new PositivePoint(-10, -10);
        System.out.println(p4.toString() + "입니다.");

        // Problem 9
        String word;
        System.out.print("총 스택 저장 공간의 크기 입력 >> ");
        StringStack stringStack = new StringStack(scanner.nextInt());
        scanner.nextLine();

        while (true) {
            System.out.print("문자열 입력 >> ");
            word = scanner.nextLine();
            if (word.equals("그만")) break;
            else if (!stringStack.push(word)) {
                System.out.println("스택이 꽉 차서 푸시 불가!");
                break;
            }
        }

        System.out.println(stringStack.pop());

        // Problem 10
        Dictionary dic = new Dictionary(10);
        dic.put("황기태", "자바");
        dic.put("이재문", "파이선");
        dic.put("이재문", "C++"); // 이재문의 값을 C++로 수정
        System.out.println("이재문의 값은 " + dic.get("이재문")); // 이재문 아이템 출력
        System.out.println("황기태의 값은 " + dic.get("황기태")); // 황기태 아이템 출력
        dic.delete("황기태"); // 황기태 아이템 삭제
        System.out.println("황기태의 값은 " + dic.get("황기태")); // 삭제된 아이템 접근

        // Problem 11
        String[] equation;
        int a, b;
        System.out.print("두 정수와 연산자를 입력하시오 >> ");
        equation = scanner.nextLine().split(" ");
        a = Integer.parseInt(equation[0]);
        b = Integer.parseInt(equation[1]);

        switch (equation[2]) {
            case "+" -> {
                Add add = new Add();
                add.setValue(a, b);
                System.out.println(add.calculate());
            }
            case "-" -> {
                Sub sub = new Sub();
                sub.setValue(a, b);
                System.out.println(sub.calculate());
            } case "*" -> {
                Mul mul = new Mul();
                mul.setValue(a, b);
                System.out.println(mul.calculate());
            } default -> {
                Div div = new Div();
                div.setValue(a, b);
                System.out.println(div.calculate());
            }
        }
    }
}

class TV {
    private int size;

    public TV(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String toString() {
        return "TV [size=" + size + ", getSize()=" + getSize() + "]";
    }
}

class ColorTV extends TV {

    private final int color;

    ColorTV(int size, int color) {
        super(size);
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public void printProperty() {
        System.out.printf("%d인치 %d컬러\n\n", getSize(), getColor());
    }
}

class IPTV extends ColorTV {
    private final String ip;

    IPTV(String ip, int size, int color) {
        super(size, color);
        this.ip = ip;
    }

    @Override
    public void printProperty() {
        System.out.printf("나의 IPTV는 %s 주소의 ", this.ip);
        super.printProperty();
    }

}

abstract class Converter {
    abstract protected double convert(double srs);
    abstract protected String getSrcString();
    abstract protected String getDestString();
    protected double ratio;

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println(getSrcString()+"을 "+getDestString()+"로 바꿉니다.");
        System.out.print("바꾸고싶은 "+getSrcString()+"화를 입력하세요 >> ");
        double val = sc.nextDouble();
        double res = convert(val);
        System.out.println("변환 결과 : "+res+getDestString()+"입니다.");
//        sc.close();
    }
}

class Won2Dollar extends Converter {

    Won2Dollar(int ratio) {
        super.ratio = ratio;
    }


    @Override
    protected double convert(double srs) {
        return srs / super.ratio;
    }

    @Override
    protected String getSrcString() {
        return "원";
    }

    @Override
    protected String getDestString() {
        return "달러";
    }
}

class Km2Mile extends Converter {

    Km2Mile(double ratio) {
        super.ratio = ratio;
    }

    @Override
    protected double convert(double srs) {
        return srs / super.ratio;
    }

    @Override
    protected String getSrcString() {
        return "Km";
    }

    @Override
    protected String getDestString() {
        return "mile";
    }
}

class Point {
    private int x, y;
    public Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    protected void move(int x, int y) { this.x = x; this.y = y; }
}

class ColorPoint extends Point {
    private String color;

    ColorPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    public void setXY(int x, int y) {
        move(x, y);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return this.color + "색의 (" + getX() + "," + getY() + ")의 점";
    }
}

class Point3D extends Point {
    private int z;

    Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public void move(int x, int y, int z) {
        move(x, y);
        this.z = z;
    }

    public int getZ() {
        return this.z;
    }

    public void moveUp() {
        move(getX(), getY() + 1);
    }

    public void moveDown() {
        move(getX(), getY()-1);
    }

    public String toString() {
        return "(" + getX() + "," + getY() + "," + getZ() + ")의 점";
    }
}

class PositivePoint extends Point {

    PositivePoint() {
        super(0, 0);
    }

    PositivePoint(int x, int y) {
        super(x, y);
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        super.move(x, y);
    }

    public void move(int x, int y) {
        if (x >= 0 && y >= 0) {
            super.move(x, y);
        }
    }

    public String toString() {
        return "(" + getX() + "," + getY() + ")의 점";
    }
}

interface Stack {
    int length(); // 현재 스택에 저장된 개수 리턴
    int compacity(); //스택의 전체 저장 가능한 개수 리턴
    String pop(); // 스택의 톱(top)에 실수 저장
    boolean push(String val); // 스택의 톱(top)에 저장된 실수 리턴
}

class StringStack implements Stack {

    private final String[] stringStack;
    private int index;

    StringStack(int n) {
        stringStack = new String[n];
        index = 0;
    }

    @Override
    public int length() {
        return index;
    }

    @Override
    public int compacity() {
        return stringStack.length - length();
    }

    @Override
    public String pop() {
        String result = "";
        for (String word : stringStack) {
            if (word != null) {
                result += word;
                result += " ";
            }
        }

        return result;
    }

    @Override
    public boolean push(String val) {
        if (compacity() == 0) return false;
        else {
            stringStack[index++] = val;
            return true;
        }
    }
}

abstract class PairMap {
    protected String[] keyArray; // key 들을 저장하는 배열
    protected String[] valueArray; // value 들을 저장하는 배열
    abstract String get(String key); // key 값을 가진 value 리턴. 없으면 null 리턴
    abstract void put(String key, String value);
    // key와 value를 쌍으로 저장. 기존에 key가 있으면, 값을 value로 수정
    abstract void delete(String key);
    // key 값을 가진 아이템(value와 함께) 삭제. 삭제된 value 값 리턴
    abstract int length(); // 현재 저장된 아이템 개수 리턴
}

class Dictionary extends PairMap {

    Dictionary(int n) {
        keyArray = new String[n];
        valueArray = new String[n];
    }

    @Override
    String get(String key) {
        for (int i=0; i<keyArray.length; i++) {
            if (keyArray[i] != null && keyArray[i].equals(key)) return valueArray[i];
        }
        return null;
    }

    @Override
    void put(String key, String value) {
        boolean check = true;

        for (int i=0; i<keyArray.length; i++) {
            if (keyArray[i] != null && keyArray[i].equals(key)) {
                valueArray[i] = value;
                check = false;
                break;
            }
        }

        if (check) {
            for (int i=0; i<keyArray.length; i++) {
                if (keyArray[i] == null) {
                    keyArray[i] = key;
                    valueArray[i] = value;
                    break;
                }
            }
        }
    }

    @Override
    void delete(String key) {
        for (int i=0; i<keyArray.length; i++) {
            if (keyArray[i] != null && keyArray[i].equals(key)) {
                keyArray[i] = null;
                valueArray[i] = null;
                break;
            }
        }
    }

    @Override
    int length() {
        int len = 0;

        for (String key : keyArray) {
            if (key != null) {
                len++;
            }
        }

        return len;
    }
}

abstract class Calc {
    protected int a;
    protected int b;

    abstract void setValue(int a, int b);
    abstract int calculate();



}

class Add extends Calc {
    @Override
    void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    int calculate() {
        return a + b;
    }
}

class Sub extends Calc {
    @Override
    void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }
    @Override
    int calculate() {
        return a - b;
    }
}

class Mul extends Calc {
    @Override
    void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }
    @Override
    int calculate() {
        return a * b;
    }
}

class Div extends Calc {
    @Override
    void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }
    @Override
    int calculate() {
        return a / b;
    }
}
