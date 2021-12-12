package Collection;

import Database.Database;
import User.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserCollection {

    private List<User> users;
    private static UserCollection instance;


    public UserCollection(){
        this.users = new ArrayList<>();
        addAllUsers();
    }

    //return singleton of the collection
    public static UserCollection getInstance(){
        if(instance == null) instance = new UserCollection();
        return instance;
    }

    //add all user from database
    private void addAllUsers(){
        this.users = Database.addPreviousUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    //get user object according to id
    public User getUserById(int userId){
        for (User user : users) {
            if(user.getId() == userId)
                return user;
        }
        return null;
    }


    public User retrieveUser(String username){
        // return a user object from username
        for(User user: users){
            if(Objects.equals(user.getUsername(), username))
                return user;
        }
        return null;
    }

}
