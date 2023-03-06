public class mainTest{

    public static void main(String[] args) {
        User ben = new User("ADMIN", "123");
        System.out.println(ben.AttemptSignin("ADMIN", "123"));
        System.out.println(ben.AttemptSignin("ADMIN", "122"));
    }
}
