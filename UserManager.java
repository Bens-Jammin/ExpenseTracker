import java.io.*;
import java.util.HashMap;

public class UserManager {
  private static final String filePath = "logininfo.txt";
  private static HashMap<String, String> userData;

  static {
    userData = new HashMap<>();
    loadData();
  }

  public static void addUser(User user) {
    String data = user.getUsername() + "," + user.getPassword();
    userData.put(user.getUsername(), user.getPassword());
    saveData(data);
  }

  public static void saveUserData(User user) {
    String data = user.getUsername() + "," + user.getPassword();
    userData.put(user.getUsername(), user.getPassword());
    saveData(data);
  }

  public static User loadUser(String username) {
    String userData = loadUserData(username);
    if (userData == null) {
      return null;
    }
    String[] parts = userData.split(",");
    return new User(parts[0], parts[1]);
  }

  private static String loadUserData(String username) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts[0].equals(username)) {
          reader.close();
          return line;
        }
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void saveData(String data) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
      writer.write(data + "\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void loadData() {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.createNewFile();
      }
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        userData.put(parts[0], parts[1]);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
