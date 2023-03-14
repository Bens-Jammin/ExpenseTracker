import java.io.*;
import java.util.ArrayList;

// need to test this

public class DataManager {
    
    public static boolean saveUser(User user){
        try{
            FileOutputStream fileOut = new FileOutputStream("UserData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(user);
            out.close();
            
            fileOut.close();
            System.out.println("Object serialized to myObj.ser");
            return true;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }


    public static User loadUserFromUsername(String username) {
        try {
            FileInputStream fileIn = new FileInputStream("UserData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            User loadedUser = (User) in.readObject();
            
            in.close();
            fileIn.close();
            
            System.out.println("User deserialized from UserData.ser: ");
            
            if (loadedUser.getUserName().equals(username)) {
                System.out.println("Found user with name " + username);
                return loadedUser;
            } else {
                System.out.println("User with name " + username + " not found");
                return null;
            }
        } catch (IOException e) {
            System.out.println("Error reading UserData.ser: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Error deserializing User object: " + e.getMessage());
            return null;
        }
    }
    
}
