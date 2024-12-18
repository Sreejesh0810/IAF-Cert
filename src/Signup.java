import java.sql.SQLException;
import java.util.Scanner;

public class Signup {
    static dbConnection dbcon = new dbConnection();
    static Scanner scan = new Scanner(System.in);

    public void signup() throws SQLException {
        boolean pass = false;
        String password;
        System.out.println("Welcome to Controller Sign up");
        System.out.print("Enter Your Employee ID: ");
        String emp_id = scan.nextLine();
        System.out.print("Enter your email ID: ");
        String email = scan.nextLine();
        do {
        System.out.println("Enter new Password: ");
        password = scan.nextLine();
        System.out.println("Enter Password Again: ");
        String passwordCheck = scan.nextLine();
        if (password.equals(passwordCheck)) {
            pass = true;
            System.out.println("Password matches");
        }
        else {
            System.out.println("Password did not match");
        }
        }
        while (!pass);

        String query = "insert into login value('" + emp_id + "','" + email + "','" + password + "');";
        dbcon.insetInto(query);

        System.out.println("New Account created");
        System.out.println("Welcome..!!");
    }

    
}
