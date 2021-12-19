/**
 * @author Xiang Long Gao
 * Une classe pour utiliser les utilisateurs du logiciel dans la base de donnée.
 */
package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String username;
    private String password;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Lire le data base et retourner tous les users existant dans une liste de users
     * @return Liste de users
     */
    public static List<User> readData() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        List<User> results = new ArrayList<>();

        try (FileReader reader = new FileReader("employees.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            userList.forEach(user -> {
                results.add(parseUserObject((JSONObject) user));
            });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Fonction permet de transformer un user à un JSON object afin qu'on puisse la sauvegarder et lire dans
     * le JSON file
     * @param employee
     * @return
     */
    private static User parseUserObject(JSONObject employee) {
        User user = new User();
        //Get employee object within list
        user.id = ((String) employee.get("id"));
        user.username = ((String) employee.get("username"));
        user.password = ((String) employee.get("password"));
        user.role = ((String) employee.get("role"));
        return user;
    }
}
