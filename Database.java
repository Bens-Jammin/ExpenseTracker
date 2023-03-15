import org.python.util.PythonInterpreter;

public class Database {

    private final String filename;

    public Database(String filename) {
        this.filename = filename;
    }

    public void saveUser(User user) {

        // Create a Gson object with pretty printing
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filename)) {

            // Convert the user object to JSON string
            String json = gson.toJson(user);

            // Write the JSON string to the file
            writer.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User loadUser(String username) {

        // Create a Gson object
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filename)) {

            // Read the JSON string from the file
            User user = gson.fromJson(reader, User.class);

            // Check if the username matches
            if (user.getUsername().equals(username)) {
                return user;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // User not found
    }
}
