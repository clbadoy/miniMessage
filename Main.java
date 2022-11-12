public class Main {

    public static void main(String[] args) {
        Admin.getInstance();
        User bob = new User("Bob");
        UserDisplay test = new UserDisplay(bob);
        System.out.println("Hello World");
    }
    
}
