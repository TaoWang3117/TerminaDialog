import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class Buddies extends Profile {
    //ArrayList<String> userList;
    //ArrayList<String> friendList = new ArrayList<>();
    Convo convoObject;
    Login login = new Login();
    String buddyName = "";
    String buddyID;
    ArrayList<String> userList;
    Profile profileObject;
    Map<String, String> userIDList;


//    public Buddies(String name, ArrayList<String> userList) throws IOException {
//
//        this.userList = userList;
//        this.name = name;
//
//        try {
//            File Buddies = new File(name + "Buddies.txt");
//            Scanner text = new Scanner(Buddies);
//            while (text.hasNextLine()) {
//                String[] string = text.nextLine().split("[\\s]");
//                for (int i = 0; i < string.length; i++) {
//                    friendList.add(string[i]);
//                }
//            }
//            text.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("User Buddy file not found!");
//        }
//
//    }

    public Buddies(String name) throws FileNotFoundException, IOException {
        this.userName = name;
        userList = login.getUserList();
        userIDList = login.getUserIDList();
        File buddyFile = new File("datafile/"+name + "Buddies.txt");
        boolean truthValue = buddyFile.exists();
        if (truthValue) {
            System.out.println("User Buddy file found!");
        }
        //this.convoObject = new Convo(this.userName);
    }
    public void display() throws IOException {

        int userChoice;
        Scanner input = new Scanner(System.in);

        printBuddies();

        System.out.println("");
        System.out.println("");


        System.out.println("Please choose from the following options: ");
        System.out.println("------------------------------------------");
        System.out.println("1. Add/Search for a Buddy");
        System.out.println("2. Delete Buddy");
        System.out.println("3. Go to Convo Platform");
        System.out.println("4. Return to Profile");


        userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                searchBuddy();
                break;
            case 2:
                deleteBuddy();
                break;
            case 3:
                this.convoObject = new Convo(this.userName);
                convoObject.displayConvoMenu();
                break;
            case 4:
                //send user to profile class
                this.profileObject = new Profile(this.userName);
                profileObject.printlist();
                break;
            default:
                display();
                break;
        }
    }

    private void printBuddies() throws IOException {
        String Friend = null;
        File Buddies = new File("datafile/BuddyFiles/" + this.userName + "Buddies.txt");
        System.out.println("Buddies List:");

        Scanner readF = new Scanner(Buddies);
        while (readF.hasNext()) {
            Friend = readF.nextLine();
            System.out.println(Friend);
        }
        readF.close();

        System.out.println(""); //empty line to add space between buddies list and action list.
        System.out.println(""); //empty line to add space between buddies list and action list.
    }

    protected boolean validEntry(String entry) throws IOException {

        if(userList.contains(entry)){
            return true;}
        else
            return false;
    }

    /**
     * This method  has the user enter a name to search if the user exits.
     * This method will use the name entered by the user and send it to the
     * validEntry method, which returns a boolean expression.
     * If the validEntry method returns true then the searchBuddy method will
     * ask the user if it would like to add the buddy. If yes then the method will send the user to the addBuddy method.
     * If the validEntry returns false then the searchBuddy method will send the user to startup method.
     * If input is invalid the searchBuddy method will restart the searchBuddy method.
     *
     * @name is the buddy name the user entered when searching for a buddy.
     */

    protected String getBuddyName() throws IOException {

        //Prompt user for name of the buddy wished to search for.
        System.out.println("Please enter the name of your buddy: ");
        //Create Scanner object to hold user input.
        Scanner name = new Scanner(System.in);
        //Set scanner object as a string equal to String nameSearch variable.
        String nameSearch = name.nextLine();

        //Test is buddy name exists.
        if (validEntry(nameSearch)) {
            this.buddyName = nameSearch;
        }

        if(!validEntry(nameSearch)){
            System.out.println("Buddy not found.");
            this.buddyName = "";
        }
        return this.buddyName;

    }


    protected String getBuddyID(String tempBuddyName) throws IOException {

        //Set this.buddyID to the buddy ID from credential.txt file
        this.buddyName = tempBuddyName;
        //use buddyName to get buddyID

            if (userIDList.containsKey(this.buddyName)) {
                userIDList.get(this.buddyName);
                System.out.println( userIDList.get(this.buddyName));
                return userIDList.get(this.buddyName);
            } else {
                System.out.println("User not found.");
                return null;
            }
    }

    protected void searchBuddy() throws IOException {
        this.buddyName = getBuddyName();

        //Test is buddy name exists.
        if (this.buddyName.equals("")) {
            System.out.println("Your buddy must not have an account yet.");
            System.out.println("Returning to Buddy Menu");
        } else {


            //Test if buddy is already on users buddy file:
            //
            // if (buddyOnFileTest(this.buddyName)){
            //      inform user that buddy is already on buddy list
            //      return to display();
            //      }
            //  else { (the following code below.)


            // Let user know the buddy has been found and prompt if user would like to add buddy.
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


    protected  boolean buddyOnFileTest(String name){
        
        //if buddy is on file then:
        //return true;

        //if buddy is not on file then:
        return false;

    }




    /**
     * This method adds a buddy to the users buddy list.
     *
     * @name is the buddy name the user entered when searching for a buddy.
     */
    protected void addBuddy() throws IOException {

        FileWriter fwriter = new FileWriter("datafile/BuddyFiles/" + this.userName + "Buddies.txt", true);
        fwriter.write("\n" + this.buddyName );
        fwriter.append("");
        fwriter.close();
    }




    /**
     * This method deletes a buddy to the users buddy list.
     *
     * @name is the buddy name the user entered when searching for a buddy.
     */
    protected void deleteBuddy() throws IOException {
        File Buddies = new File("datafile/BuddyFiles/" + this.userName + "Buddies.txt");
        String nameSearch;
        String Sure;


        System.out.println("Please enter the name of your buddy you would like to delete: ");
        //Create Scanner object to hold user input
        Scanner name = new Scanner(System.in);
        nameSearch = name.nextLine();

        System.out.println("Are you sure you want to delete " + nameSearch + "?");
        System.out.println("Yes or No");
        Scanner check = new Scanner(System.in);
        Sure = check.nextLine();

        if(Sure.equalsIgnoreCase("yes")){

            File temp = File.createTempFile("datafile/BuddyFiles/" +this.userName+ "Buddies", ".txt");
            String delete = nameSearch;

            BufferedReader reader = new BufferedReader(new FileReader("datafile/BuddyFiles/" + this.userName + "Buddies.txt"));
            PrintWriter writer = new PrintWriter(temp);

                for (String line; (line = reader.readLine()) != null; ) {
                line = line.replace(delete, "");

                writer.println(line);



                boolean booleanDelete = Buddies.delete();
                if (booleanDelete) {
                    System.out.println("Delete Successful");
                }
                boolean booleanRename = temp.renameTo(new File("datafile/buddy/" + this.userName + "Buddies.txt"));
                if (booleanRename) {
                    System.out.println("Buddy list has been updated!");
                }

            }
            reader.close();
            writer.close();
            name.close();


        }

        else {
            System.out.println("Buddy was not deleted");
        }
        display();
    }
//    public void importClass(Convo userConvo) {
//        this.userConvo = userConvo;
//   }
//
//    public void gotoConvo() throws IOException {
//        userConvo.display();
//        System.out.println("We are in Buddies Class");
//        System.out.println(" "); //Adds a space
//    }
//
//    public void display() throws IOException {
//
//        int userChoice;
//        Scanner input = new Scanner(System.in);
//
//        printBuddies();
//
//        System.out.println("");
//        System.out.println("");
//
//
//        System.out.println("Please choose from the following options: ");
//        System.out.println("------------------------------------------");
//        System.out.println("1. Add Search for a Buddy");
//        System.out.println("2. Delete Buddy");
//        System.out.println("3. Go to Message Platform");
//        System.out.println("4. Return to Profile");
//
//
//        userChoice = input.nextInt();
//
//        switch (userChoice) {
//            case 1:
//                searchBuddy();
//                break;
//            case 2:
//                deleteBuddy();
//                break;
//            case 3:
//                gotoConvo();
//                break;
//            case 4:
//                return;
//        }
//    }
//
//    private void printBuddies() throws IOException {
//        String Friend = null;
//        File Buddies = new File(name +"Buddies.txt");
//        System.out.println("Buddies List:");
////        System.out.println("Buddy");//Somehow print user buddies
////
////        System.out.println("Here are your buddies!");
//
//        Scanner readF = new Scanner(Buddies);
//        while (readF.hasNext()) {
//            Friend = readF.nextLine();
//            System.out.println(Friend);
//        }
//        readF.close();
//
//        System.out.println(""); //empty line to add space between buddies list and action list.
//        System.out.println(""); //empty line to add space between buddies list and action list.
//    }
//
//    protected boolean validEntry(String entry) throws IOException {
//
//        if(userList.contains(entry)){
//            return true;}
//        else
//            return false;
//    }
//
//    /**
//     * This method  has the user enter a name to search if the user exits.
//     * This method will use the name entered by the user and send it to the
//     * validEntry method, which returns a boolean expression.
//     * If the validEntry method returns true then the searchBuddy method will
//     * ask the user if it would like to add the buddy. If yes then the method will send the user to the addBuddy method.
//     * If the validEntry returns false then the searchBuddy method will send the user to startup method.
//     * If input is invalid the searchBuddy method will restart the searchBuddy method.
//     *
//     * @name is the buddy name the user entered when searching for a buddy.
//     */
//    protected void searchBuddy() throws IOException {
//
//        //Prompt user for name of the buddy wished to search for.
//        System.out.println("Please enter the name of your buddy: ");
//        //Create Scanner object to hold user input.
//        Scanner name = new Scanner(System.in);
//        //Set scanner object as a string equal to String nameSearch variable.
//        String nameSearch = name.nextLine();
//
//        //Test is buddy name exists.
//        if (validEntry(nameSearch) == true) {
//            // Let user know the buddy has been found and prompt if user would like to add buddy.
//            System.out.println("Buddy found!");
//            System.out.println("Would you like to add this buddy?");
//            System.out.println("Enter a 1 for 'Yes' or a 2 for 'No'");
//            //Create Scanner object to hold user input.
//            Scanner input = new Scanner(System.in);
//            //Set scanner object as a integer equal to integer choice variable.
//            int choice = input.nextInt();
//
//            //Test user input for new pathways.
//            if (choice == 1) {
//                //Send user to the addBuddy method, passing the nameSearch variable.
//                addBuddy(nameSearch);
//                name.close();
//            } else if (choice == 2) {
//                //Send user to buddy startup method.
//                System.out.println("Okay! Buddy not added!");
//                display();
//                name.close();
//            } else {
//                //Have user restart the searchBuddy method.
//                System.out.println("Wrong input.");
//                name.close();
//                searchBuddy();
//            }
//
//        }
//
//        if(validEntry(nameSearch) == false){
//            System.out.println("Buddy not found.");
//            System.out.println("Make sure everything was typed correctly");
//            searchBuddy();}
//    }
//
//    /**
//     * This method adds a buddy to the users buddy list.
//     *
//     * @name is the buddy name the user entered when searching for a buddy.
//     */
//    protected void addBuddy(String name) throws IOException {
//        String buddyName = name;
//
//        FileWriter fwriter = new FileWriter(this.name+"Buddies.txt", true);
//        fwriter.write("\n" + buddyName );
//        fwriter.append("");
//        fwriter.close();
//
//        display();
//
//    }
//
//    /**
//     * This method deletes a buddy to the users buddy list.
//     *
//     * @name is the buddy name the user entered when searching for a buddy.
//     */
//    protected void deleteBuddy() throws IOException {
//        File Buddies = new File(name+"Buddies.txt");
//        String nameSearch;
//        String Sure;
//
//        /**
//         * Rewrite file
//         * delete string from file
//         * Create array list?
//         * Create new array list and deep copy strings into list
//         * then use list to rewrite file with.
//         */
//
//        System.out.println("Please enter the name of your buddy you would like to delete: ");
//        //Create Scanner object to hold user input
//        Scanner name = new Scanner(System.in);
//        nameSearch = name.nextLine();
//
//        System.out.println("Are you sure you want to delete " + nameSearch + "?");
//        System.out.println("Yes or No");
//        Scanner check = new Scanner(System.in);
//        Sure = check.nextLine();
//
//        if(Sure == "Yes" || Sure == "yes") {
//
//
//            File temp = File.createTempFile("Buddies", ".txt");
//            //String delete = nameSearch;
//
//            BufferedReader reader = new BufferedReader(new FileReader(this.name + "Buddies.txt"));
//            PrintWriter writer = new PrintWriter(temp);
//
//            for (String line; (line = reader.readLine()) != null; ) {
//                line = line.replace(nameSearch, "");
//
//                writer.println(line);
//
//                reader.close();
//                writer.close();
//                name.close();
//
//                boolean booleanDelete = Buddies.delete();
//                if (booleanDelete) {
//                    System.out.println("Delete Successful");
//                }
//                boolean booleanRename = temp.renameTo(new File(this.name + "Buddies.txt"));
//                if (booleanRename) {
//                    System.out.println("Buddy list has been updated!");
//                }
//            }
//        }
//
//        else {
//            System.out.println("Buddy was not deleted");
//        }
//        display();
//    }

}

