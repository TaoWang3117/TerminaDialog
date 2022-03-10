import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Login mylogin = new Login();
        String username;
        String password;
        String name = null;

        Scanner scan = new Scanner(System.in);

        while(name == null){
            System.out.print("Enter username:");
            username = scan.next();
            System.out.print("Enter password:");
            password = scan.next();
            name = mylogin.matching(username, password);
        }

        Profile userProfile = new Profile(name);
//        Convo userConvo = new Convo(name);
 //       Buddies userBuddies = new Buddies(name);

//        userProfile.importClass(userConvo,userBuddies);
//        userConvo.importClass(userBuddies);
//        userBuddies.importClass(userConvo);
        //while(true){
        userProfile.printlist();
        //}


    }
}
