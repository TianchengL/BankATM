package LoginOrSignUp;


import Database.Database;

//used to log in when user already have an account
public class Login {

    private final String username;
    private final String password;

    public Login(String username, String password){
        this.password = password;
        this.username = username;
    }

    /**needs to have a method to check with the database to see if there is a match user**/

    //return true if we find user
    public String findUser(){
        //if pwd is empty means no user find
        String pwd = Database.checkUserExist(username);
        return pwd;
    }

    //return true if login success
    public boolean run(){
        String pwd = findUser();
        if(!pwd.isEmpty()){
            return pwd.equals(password);
        }
        return false;
    }

    //get user type of current login user
    public String getUserType(){
        return Database.getUserType(this.username);
    }

}