package Collection;

import User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CollectionArrays {
    private static List<User> users = new ArrayList<User>();

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static User retrieveUser(String username){
        // return a user object from username
        for(User user: users){
            if(Objects.equals(user.getUsername(), username))
                return user;
        }
        return null;
    }

}
