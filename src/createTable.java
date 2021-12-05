import User.ExecuteSqlCommand;

public class createTable {

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTables() {
        // SQL statement for creating a new table
        String sql1 = "CREATE TABLE IF NOT EXISTS customerInfo (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	username text NOT NULL,\n"
                + "	address text NOT NULL\n"
                + ");";

        String sql2 = "CREATE TABLE IF NOT EXISTS loginInfo (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL\n"
                + ");";

        ExecuteSqlCommand.executeCommand(sql1);
        ExecuteSqlCommand.executeCommand(sql2);
    }


}
