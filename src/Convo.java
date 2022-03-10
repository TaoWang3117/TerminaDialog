import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class Convo extends Profile {
    String filenameID = ""; //Used to hold the file path
    String userID; //Used to find file
    String buddyName = ""; //Used to hold the buddyName after tested if buddy name is valid in buddyExistsTest() method
    String buddyID = ""; //Used to find file - obtained from getUserID method.
    Profile profileObject;
    Buddies buddyObject;
    Login login;
    Map<String, String> userIDList;


    public Convo(String name) throws IOException {
        this.userName = name;
        this.buddyObject = new Buddies(this.userName);
        this.login = new Login();
        userIDList = login.getUserIDList();
        this.userID = matching(userName);

    }


    public String matching(String userName) {
        if (userIDList.containsKey(userName)) {
            return userIDList.get(userName);
        } else {
            System.out.println("User not found.");
            return null;
        }
    }


    /**
     * Plan for convo class:
     * Present list of options to user
     * Get choice from user and determine direction for program
     *
     * New message choice: get buddy name from user, get buddyID, test if convo file exist already
     * before creating new file, If file exist let user now and print out the files contents,
     * then prompt for user's new message. Append message to file, return to convo options
     *
     * Check message choice: get buddy name from user, get buddyID, test if convo file exist, if
     * file exist print out file contents to user and then give user a list of options, if
     * file does not exist inform user and return to convo menu
     *
     * Delete message choice:get buddy name from user, get buddyID, test if convo file exist, if
     * file exist print out file contents to user and then double check if user would like to delete contents,
     * if file does not exist inform user and return to convo menu
     *
     * Go to buddies choice : return to buddies class.
     * Go to profile choice: return to profile class.
     */



    public void displayConvoMenu() throws IOException {

        System.out.println("\nWe are in Convo class");
        String userChoice;
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose from the following options: ");
        System.out.println("------------------------------------------");
        System.out.println("1. New Convo Thread");
        System.out.println("2. Check Message");
        System.out.println("3. Add to Existing Convo Thread");
        System.out.println("4. Delete Message");
        System.out.println("5. Go to Buddy List");
        System.out.println("6. Return to Profile");

        userChoice = input.next();

        switch (userChoice) {
            case "1":
                //User choose to Create a New Message
                getConvoBuddyName();
                //Send to getBuddyID() method
                this.buddyID = this.buddyObject.getBuddyID(this.buddyName);
                if (fileExistTest()){
                    //if file exists inform user and ask if they would like to append file
                    System.out.println("A convo thread with this buddy has already been created. ");
                    System.out.println("Returning to Main Convo Menu.");
                } else {
                    //if file does not exist send to newMessage() method
                    newMessage();
                }
                displayConvoMenu();
                break;
            case "2":
                //User choose to Check a message
                getConvoBuddyName();
                //Send to getBuddyID() method
                this.buddyID = this.buddyObject.getBuddyID(this.buddyName);
                if (!fileExistTest()){
                    //if file does not exists inform user
                    System.out.println("A convo file with this buddy does not exist. ");
                    System.out.println("Returning to Main Convo Menu. ");
                } else {
                    //if file does exist send to newMessage() method
                    displayConvo();
                }
                displayConvoMenu();
                break;
            case "3":
                //User choose to Check a message
                getConvoBuddyName();
                //Send to getBuddyID() method
                this.buddyID = this.buddyObject.getBuddyID(this.buddyName);
                if (!fileExistTest()){
                    //if file does not exists inform user
                    System.out.println("A convo file with this buddy does not exist. ");
                    System.out.println("Returning to Main Convo Menu. ");
                } else {
                    //if file does exist send to newMessage() method
                    addToConvo();
                }
                displayConvoMenu();
                break;
            case "4":
                //Send to getBuddyName() method
                getConvoBuddyName();
                //Send to getBuddyID() method with buddyName as argument
                this.buddyID = this.buddyObject.getBuddyID(this.buddyName);
                //Send to fileExistTest() method (returns boolean), if file exists:
                if (fileExistTest()) {
                    //Send to deleteMessage() method with filenameID
                    deleteMessage();
                } else {
                    //if file does not exists inform user
                    System.out.println("A convo file with this buddy does not exist. ");
                    System.out.println("Returning to Main Convo Menu. ");
                }
                displayConvoMenu();
                break;
            case "5":
                //send user to buddies class.
                this.buddyObject = new Buddies(this.userName);
                buddyObject.display();
                break;
            case "6":
                //send user to profile class
                this.profileObject = new Profile(this.userName);
                profileObject.printlist();
                break;

            default:
                //wrong input from user
                System.out.println("Invalid input");
                System.out.println("Returning to Main Convo Menu. ");
                displayConvoMenu();
                break;

        }
    }

    public void getConvoBuddyName() throws IOException {
        //Prompt user for buddy name & set this.buddyName
        this.buddyName = this.buddyObject.getBuddyName();
        if (this.buddyName.equals("")){
            //If this.buddyName = "" then the buddy does not exist, user needs
            // to return to displayConvoMenu()
            System.out.println("Returning to Convo Menu...");
            displayConvoMenu();
        }

    }


    public boolean fileExistTest() {
        Path tempFilePath1 = Path.of("datafile/ConvoFiles/" + userID + buddyID + ".txt");
        Path tempFilePath2 = Path.of("datafile/ConvoFiles/" + buddyID + userID + ".txt");
        if (Files.exists(tempFilePath1)){
            this.filenameID = "datafile/ConvoFiles/"+userID+buddyID+".txt";
            return true;
        } else if (Files.exists(tempFilePath2)) {
            this.filenameID = "datafile/ConvoFiles/"+buddyID+userID+".txt";
            return true;
        } else {
            //if file does not exist, inform user
            System.out.println("File does not exist.");
            return false;
        }
    }


    public void newMessage() throws IOException {
        //Create new file with userID and buddyID
        File newFile = new File("datafile/ConvoFiles/" + userID + buddyID + ".txt");
        //Prompt user input from message
        System.out.println("Please enter your message");
        //Add input to file
        Scanner input = new Scanner(System.in);
        String stringInput = input.nextLine();
        FileWriter fwriter = new FileWriter(newFile, true);
        fwriter.write("\n" +this.userName + ":"+ "\n" + stringInput);
        fwriter.close();

        //Let user know that message has been added to new file.
        System.out.println("Message has been sent!");
    }
    public void displayConvo() throws FileNotFoundException {
        //display the contents of the convo using this.filenameID to access contents
        String message;
        File tempFile = new File(this.filenameID);
        System.out.println("\n-----Convo Thread-----");
        Scanner readF = new Scanner(tempFile);
        while (readF.hasNext()) {
            message = readF.nextLine();
            System.out.println(message);
        }
        readF.close();
    }
    public void addToConvo() throws IOException {
        FileWriter tempFile = new FileWriter(this.filenameID,true);
        System.out.println("Please enter your message");
        //Add input to file
        Scanner input = new Scanner(System.in);
        String stringInput = input.nextLine();
        tempFile.write(this.userName + ":"+ "\n" + stringInput + "\n");
        tempFile.close();


        //Let user know that message has been added to new file.
        System.out.println("Convo thread has successfully been updated!");
    }
    public void deleteMessage(){
        //delete convo file
        File deleteFile = new File(this.filenameID);
        if (deleteFile.delete()) {
            System.out.println("Deleted the file!");
        } else {
            System.out.println("File not deleted.");
        }
    }

}


