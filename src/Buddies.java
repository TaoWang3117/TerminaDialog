import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class Buddies extends Profile {
    Convo convoObject;
    Login login = new Login();
    String buddyName = "";

    ArrayList<String> friendList = new ArrayList<>();
    ArrayList<String> userList;
    Profile profileObject;
    Map<String, String> userIDList;

    public Buddies(String name) throws IOException {
        this.userName = name;
        userList = login.getUserList();
        userIDList = login.getUserIDList();
        File buddyFile = new File("datafile/BuddyFiles/"+name + "Buddies.txt");
        if (!buddyFile.exists()) {
            System.out.println("User Buddy file not found!");
        }
        Scanner text = new Scanner(buddyFile);
        while (text.hasNextLine()) {
            friendList.add(text.nextLine());
        }
        text.close();

    }

    public void display() throws IOException {

        String userChoice;
        Scanner input = new Scanner(System.in);
        printBuddies();
        System.out.println("\n");
        System.out.println("Please choose from the following options: ");
        System.out.println("------------------------------------------");
        System.out.println("1. Add/Search for a Buddy");
        System.out.println("2. Delete Buddy");
        System.out.println("3. Go to Convo Platform");
        System.out.println("4. Return to Profile");


        userChoice = input.next();

        switch (userChoice) {
            case "1":
                getBuddyName();
                searchBuddy();
                break;
            case "2":
                deleteBuddy();
                break;
            case "3":
                this.convoObject = new Convo(this.userName);
                convoObject.displayConvoMenu();
                break;
            case "4":
                //send user to profile class
                this.profileObject = new Profile(this.userName);
                profileObject.printlist();
                break;
            default:
                //wrong input from user
                System.out.println("Invalid input");
                System.out.println("Returning to Buddies Menu. ");
                display();
                break;
        }
    }

    private void printBuddies()  {
        System.out.println("\nBuddies List:");
        for (String name: friendList) {
            System.out.println(name);
        }
    }


    protected String getBuddyName(){

        //Prompt user for name of the buddy wished to search for.
        System.out.println("Please enter the name of your buddy: ");
        //Create Scanner object to hold user input.
        Scanner name = new Scanner(System.in);
        //Set scanner object as a string equal to String nameSearch variable.
        String nameSearch = name.nextLine();

        //Test is buddy name exists.
        if (userList.contains(nameSearch)) {
            this.buddyName = nameSearch;
        }else{
            System.out.println("Buddy not found.");
            this.buddyName = "";
        }
        return this.buddyName;

    }


    protected String getBuddyID(String tempBuddyName){

        //Set this.buddyID to the buddy ID from credential.txt file
        this.buddyName = tempBuddyName;
        //use buddyName to get buddyID

        if (userIDList.containsKey(this.buddyName)) {
            return userIDList.get(this.buddyName);
        } else {
            System.out.println("User not found.");
            return null;
        }
    }

    protected void searchBuddy() throws IOException {

        //Test is buddy name exists.
        if (this.buddyName.equals("")) {
            System.out.println("Your buddy must not have an account yet.");
            System.out.println("Returning to Buddy Menu");
        } else {
            System.out.println("Buddy found!");
            System.out.println("Would you like to add this buddy?");
            System.out.println("Enter a 1 for 'Yes' or a 2 for 'No'");
            //Create Scanner object to hold user input.
            Scanner input = new Scanner(System.in);
            //Set scanner object as a integer equal to integer choice variable.
            int choice = input.nextInt();

            //Test user input for new pathways.
            if (choice == 1) {
                //Send user to the addBuddy method.
                addBuddy();
            } else if (choice == 2) {
                //Send user to buddy display method.
                System.out.println("Okay! Buddy not added!");
            } else {
                //Have user restart the searchBuddy method.
                System.out.println("Wrong input.");
                searchBuddy();
            }

        }
        display();
    }

    protected void addBuddy() throws IOException {
        if(!friendList.contains(this.buddyName)){
            friendList.add(this.buddyName);
            updateFile();
        }else{
            System.out.println("You two already become buddy!!!");
        }

        display();
    }


    protected void deleteBuddy() throws IOException {
        System.out.println("Enter the buddy name you want to delete:");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        if(friendList.contains(name)){
            friendList.remove(name);
            updateFile();
        }else{
            System.out.println("Name not Found!!!");
        }
        display();
    }

    protected void updateFile() throws IOException {
        FileWriter fwriter = new FileWriter("datafile/BuddyFiles/" + this.userName + "Buddies.txt", false);
        for (String name: friendList) {
            fwriter.write(name + '\n');
        }
        fwriter.close();
    }

}

