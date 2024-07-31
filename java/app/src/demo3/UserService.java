package demo3;

public class UserService {

    private static final UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
    }

    private UserService() {
        System.out.println("UserService Created");
    }

    public void deleteUser(int userNo) {
        System.out.println("Deleting user " + userNo);
    }
}
