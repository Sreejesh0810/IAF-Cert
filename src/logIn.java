import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class logIn {

    static Scanner scan = new Scanner(System.in);
    static final String ANSI_RESET = "\u001B[0m";

    // public static void main(String[] args) throws SQLException {
    public void takeInput(String type) throws SQLException {
        boolean loggedin = false;
        System.out.println("\u001B[35m");
        System.out.println("Welcome");
        while (loggedin == false) {
            System.out.print("Enter your email: ");
            String email = scan.nextLine();
            if (credCheck("email", email, type)) {
                System.out.println("Enter your Password");
                String password = scan.nextLine();
                if (credCheck("password", password, type)) {
                    loggedin = true;
                    System.out.println();
                    System.out.println("You are Logged in");
                } else {
                    System.out.println();
                    System.out.println("Wrong Password");
                    System.out.println();
                    continue;
                }
            } else {
                System.out.println();
                System.out.println("Wrong Username");
                System.out.println();
                continue;
            }

        }
        System.out.print(ANSI_RESET);

    }

    protected boolean credCheck(String col, String call, String type) throws SQLException {
        boolean found = false;
        dbConnection dbcon = new dbConnection();
        String query = "select " + col + " from login where emp_id in (select emp_id from employees " +
                "where emp_type = '" + type + "');";
        ResultSet res = dbcon.select(query);
        while (res.next()) {
            if (res.getString(1).equals(call)) {
                found = true;
                break;
            }
        }

        dbcon.disconnectFromDatabase();
        return found;
    }

}