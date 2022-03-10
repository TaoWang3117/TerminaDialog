import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Profile {
    Convo convoObject;
    Buddies buddyObject;
    String userName;

public Profile(){}

    public Profile(String name) throws FileNotFoundException, IOException{
        this.userName = name;
        System.out.println("Hello " + userName);
        printlist();
    }

    public void printlist() throws IOException {

        System.out.println("Please choose from the following options:");
        System.out.println("1. Access Convos");
        System.out.println("2. Access Buddy List");
        System.out.println("3. Logout");
        System.out.println("Choice: ");

        //Create Scanner object to hold user input
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        directUser(choice);
    }

//    public void importClass(Convo userConvo, Buddies userBuddy){
//        this.userConvo = userConvo;
//        this.userBuddy = userBuddy;
//    }

    private void directUser(int choice) throws IOException {
        //Use switch system to direct user.
        switch (choice) {
            case 1:
                //Send user to the Convo class
                this.convoObject = new Convo(this.userName);
                convoObject.displayConvoMenu();
                break;
            case 2:
                //Send user to the Buddies class
                this.buddyObject = new Buddies(this.userName);
                buddyObject.display();
                break;
            case 3:
                //End program
                System.exit(0);
                break;
            default:
                System.out.println("Wrong input.");
        }
    }

}