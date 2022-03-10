import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Login mylogin = new Login();
        String username;
        String password;
        String name = null;

        Scanner scan = new Scanner(System.in);

        while(name == null){
            System.out.print("\nEnter username:");
            username = scan.next();
            System.out.print("Enter password:");
            password = scan.next();
            name = mylogin.matching(username, password);
        }

        Profile userProfile = new Profile(name);

        userProfile.printlist();



    }
}
