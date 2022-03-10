import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login extends Exception{
    Map<String, String[]> userLogin = new HashMap<>();
    ArrayList<String> userList = new ArrayList<>();
    Map<String, String> userIDList = new HashMap<>();

    public Login(){
        {
            try {
                File myfile = new File("datafile/credential.txt");
                Scanner text = new Scanner(myfile);
                while (text.hasNextLine()) {
                    String[] string = text.nextLine().split("[\\s]");
                    String[] userPass = {string[2],string[0]};
                    userList.add(string[0]);
                    userIDList.put(string[0],string[3]);
                    userLogin.put(string[1], userPass);
                }
                text.close();
            } catch (FileNotFoundException e) {
                System.out.println("Credential text file not found!");
            }
        }
    }

    public ArrayList<String> getUserList(){
        return userList;
    }
    public Map<String,String> getUserIDList(){ return userIDList; }

    public String matching(String username, String password){
        if(userLogin.containsKey(username)){
            if(userLogin.get(username)[0].equals(password)){
                return userLogin.get(username)[1];//further on return Profile class with name declaration

            }else {
                System.out.println("Incorrect Password!!!");
                return null;//further on return Profile class with empty declaration
            }
        }else{
            System.out.println("Incorrect Username!!!");
            return null;//further on return Profile class with empty declaration
        }
    }
}