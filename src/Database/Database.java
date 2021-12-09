package Database;

import User.User;

import java.sql.*;

//create specified named database and have function to access query
public class Database {

    private static final String dbURL = "jdbc:sqlite:bankAtm.db";

    public Database() {
        createNewDatabase();
        createUserTable();
        createCredentialTable();
    }

    public void createNewDatabase() {
//        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(dbURL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUserTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS user (
                	id integer PRIMARY KEY autoincrement,
                	firstname text NOT NULL,
                	lastname text NOT NULL
                );""";
        executeCommand(sql);
    }

    //add a user to database and return his id
    public static void addUser(User user){
        String sql = "INSERT INTO user (firstname, lastname) VALUES ('" +
                user.getFirstname() + "', '" + user.getLastname() + "');";

        executeCommand(sql);

    }

    //return the add of just added user
    public static int getUserId(){
        String sql = "SELECT id FROM user order by id DESC Limit 1";
        //String sql = "select firstname from user";
        int userID;
        try (Connection conn = DriverManager.getConnection(dbURL);

             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
             userID = rs.getInt("id");

             return userID;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -2;
    }

    public void createCredentialTable(){
        String sql = """
                    CREATE TABLE IF NOT EXISTS loginInfo (
                           id integer PRIMARY KEY autoincrement,
                           username text NOT NULL,
                           password text NOT NULL,
                           userId integer,
                           userType text NOT NULL,
                           FOREIGN KEY(userId) references user(id)
                           );""";
        executeCommand(sql);
    }

    //insert credential info into loginInfo table
    public static void addToCredentialTable(User user, String username, String password){
        String type = "";
        if(user.getType() == User.UserType.MANAGER){
            type = "Manager";
        }else {
            type = "Customer";
        }
        String sql =  "INSERT INTO loginInfo (username, password, userId, userType) VALUES ('" +
                username + "', '" + password + "', '" + user.getId() + "', '" + type + "');";
        executeCommand(sql);
    }

    //return user type by checking username
    public static String getUserType(String username){
        String sql = "SELECT * FROM loginInfo WHERE username = '" + username + "'";
        String res = "";
        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))
        {
            res = rs.getString("userType");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    //check if input username exist in database
    //return password if user in the database, empty otherwise
    public static String checkUserExist(String username){
        String sql = "SELECT * FROM loginInfo WHERE username = '" + username + "'";
        String res = "";
        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))
        {
            res = rs.getString("password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    private static void executeCommand(String sql){

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}