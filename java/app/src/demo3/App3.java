package demo3;

public class App3 {

    public static void main(String[] args) {

        UserService userService = UserService.getInstance();
        UserService userService2 = UserService.getInstance();

        System.out.println(userService);
        System.out.println(userService2);
    }
}
