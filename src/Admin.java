public class Admin extends Controller {
    static dbConnection dbcon = new dbConnection();

    static Scanner scan = new Scanner(System.in);
    static final String ANSI_RESET = "\u001B[0m";

    public void adminPage() throws Exception {
        System.out.println();
        logIn log = new logIn();
        log.takeInput("Admin");
        int choice;
        
        do {
            System.out.print("\u001B[34m");
            System.out.println();
            System.out.println("Press 1 to View Employee Info");
            System.out.println("Press 2 to Add a new Employee");
            System.out.println("Press 3 to remove an Employee");
            System.out.println("Press 9 to log out");
            System.out.print("Choice: ");
            choice = Integer.parseInt(scan.nextLine());
            System.out.print(ANSI_RESET);
            switch (choice) {
                case 1:
                    viewDetail();
                    break;
                case 2:
                    addEmp();
                    break;
                case 3:
                    removeEmp();
                    break;
                case 9:
                    dbcon.disconnectFromDatabase();
                    System.out.println("Good Bye");
                    break;
                default:
                    System.out.println("Wrong Input");

            }

        } while (choice != 9);
    }

    protected void addEmp() throws SQLException {
        int emp_id = createEmpid();
        System.out.println();
        System.out.print("\u001B[32m");
        System.out.print("Enter the Employees Name: ");
        String emp_name = scan.nextLine();
        System.out.print("Enter Employees Address: ");
        String emp_add = scan.nextLine();
        System.out.print("Enter Employees Type: ");
        String emp_type = scan.nextLine();
        System.out.println(ANSI_RESET);
        String status = "Working";
        String query = "insert into employees values(" + emp_id + ",'" + emp_name + "','"
                + emp_add + "','" + emp_type + "','" + status + "');";
        dbcon.insetInto(query);
        System.out.println();
        System.out.println("New Employee Added!");
        System.out.println("Employees new ID is: " + "\u001B[31m" + emp_id + ANSI_RESET);
    }

    protected int createEmpid() throws SQLException {
        System.out.println();
        String query = "select emp_id from employees where emp_type = 'Controller' order by emp_id;";
        ResultSet rs = dbcon.select(query);
        int[] idList = new int[10];
        int con = 0;
        while (rs.next()) {
            idList[con] = rs.getInt(1);
            con += 1;
        }
        int newID = idList[con - 1] + 1;
        return newID;
    }

    protected void removeEmp() throws SQLException {
        System.out.print("\u001B[31m");
        System.out.println();
        System.out.print("Enter Employees ID: ");
        String emp_id = scan.nextLine();
        if (empCheck(emp_id)){
            String query = "delete from login where emp_id = '" + emp_id + "';";
            dbcon.drop(query);
            System.out.println();
            System.out.println("Employees Log In deleted");
            System.out.print(ANSI_RESET);
            String cque = "update employees set status = 'Not Working' where emp_id = '" 
                + emp_id + "';";
            dbcon.update(cque);
        }
        else {
            System.out.println();
            System.out.println("Employees not found");
        }

    }

    protected void viewDetail() throws SQLException {
        String query = "select * from employees where not emp_type = 'Admin';";
        ResultSet result = dbcon.select(query);
        System.out.print("\u001B[33m");
        while (result.next()) {
            System.out.println();
            System.out.println("Employee ID: " + result.getInt(1));
            System.out.println("Employees Name: " + result.getString(2));
            System.out.println("Employees Address: " + result.getString(3));
            System.out.println("Employees Type: " + result.getString(4));
            System.out.println("Employee Status: " + result.getString(5));
            System.out.println();
        }
        System.out.print(ANSI_RESET);

    }

    static boolean empCheck(String emp_id) throws SQLException {
        String query = "select emp_id from login where emp_id in (select emp_id from employees where not emp_type = 'Admin');";
        ResultSet result = dbcon.select(query);
        while (result.next()) {
            String emp = result.getString(1);
            if (emp.equals(emp_id)) {
                return true;
            }

        }
        return false;
    }

}