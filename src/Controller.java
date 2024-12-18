import java.sql.SQLException;
import java.util.Scanner;


public class Controller  {

    static Scanner scan = new Scanner(System.in);
    static dbConnection dbcon = new dbConnection();
    User use = new User();

    public void controllerPage() throws Exception {
        logIn log = new logIn();
        log.takeInput();
        int choice;
        do {
            System.out.println("Press 1 to add new Certificate Details");
            System.out.println("Press 2 to update Certificate Details");
            System.out.println("Press 3 to delete Cetrificate or Comapany Details");
            System.out.println("Press 9 to log out");
            System.out.print("Choice: ");
            choice = Integer.parseInt(scan.nextLine());
        switch (choice) {
            case 1:
            takeInput();
            break;
            case 2:
            update();
            break;
            case 3:
            delete();
            break;
            case 9:
            dbcon.disconnectFromDatabase();
            System.out.println("You are Logged out!");
            break;
            default:
            System.out.println("Invalid Input");
        }
        }
        while (choice != 9);

    }
// public static void main(String[] arga) throws SQLException {
    protected void takeInput() throws SQLException {
        System.out.print("Identity Number: ");
        String id = scan.nextLine();
        System.out.print("Status: ");
        String status = scan.nextLine();
        System.out.print("Accreditation Status: ");
        String accStatus = scan.nextLine();
        System.out.print("Type: ");
        String type = scan.nextLine();
        System.out.print("Scope: ");
        String scope = scan.nextLine();
        System.out.print("Original Issue Date: ");
        String OIData = scan.nextLine();
        System.out.print("Issue Date: ");
        String Idate = scan.nextLine();
        System.out.print("Expiry Date: ");
        String Exdate = scan.nextLine();
        System.out.print("Entity Name: ");
        String Ename = scan.nextLine();
        System.out.print("Entity Trading Name: ");
        String ETname = scan.nextLine();
        System.out.print("Entity English Name: ");
        String EEname = scan.nextLine();
        System.out.print("Entity Email Address: ");
        String EEadd = scan.nextLine();
        System.out.print("Entity Key Contact: ");
        String EKcon = scan.nextLine();
        System.out.print("Entity Phone: ");
        String Ephone = scan.nextLine();
        System.out.print("Entity Unique ID: ");
        String EUid = scan.nextLine();
        System.out.print("Entity Street: ");
        String Estreet = scan.nextLine();
        System.out.print("Entity City: ");
        String Ecity = scan.nextLine();
        System.out.print("Entity State: ");
        String Estate = scan.nextLine();
        System.out.print("Entity Zipcode/Postcode: ");
        String Ecode = scan.nextLine();
        System.out.print("Entity Country/Economy: ");
        String Ecoun = scan.nextLine();
        System.out.print("Entity_Website: ");
        String Ewebsite = scan.nextLine();
        System.out.print("Accreditation Body Name: ");
        String ABN = scan.nextLine();
        System.out.print("Accreditation Body Acronym: ");
        String ABA = scan.nextLine();
        // String[] vallist = {id, status, accStatus, type, scope, OIData, Idate, Exdate, Ename, 
        //     ETname, EEname, EEadd, EKcon, Ephone, EUid, Estreet, Ecity, Estate, Ecode, Ecoun, 
        //     Ewebsite, ABN, ABA};
        String query = "insert into cert_table values ('" + id + "','" + status + "','"  + accStatus + "','"  + type + "','"  + scope + "','" 
        + OIData + "','"  + Idate + "','"  + Exdate + "','"  + Ename + "','"  + ETname + "','" + EEname + "','"  + EEadd + "','"  + EKcon + "','"  + Ephone + "','"  + EUid + "','"  + Estreet + "','" 
        + Ecity + "','"  + Estate + "','" + Ecode + "','"  + Ecoun + "','"  + Ewebsite + "','"  + ABN + "','"  + ABA + "')";
        dbcon.insetInto(query);
        

    }

    protected void update() throws Exception {
        System.out.println("Enter the company ID number: ");
        use.compNum = scan.nextLine();
        use.serEuid();
        System.out.println("Enter Certificate number: ");
        use.certNum = scan.nextLine();
        System.out.print("Column name to be updated: ");
        String colname = scan.nextLine();
        System.out.print("Enter the updated value: ");
        String upval = scan.nextLine();
        String query = "update cert_table set " + colname + " = '" + upval + 
        "' where Identity_Number like '"+ use.compNum + "-" + use.certNum + "%'";
        dbcon.update(query);
        System.out.println("Details updated");

    }


    protected void delete() throws SQLException{

        System.out.println("Press 1 for Company");
        System.out.println("Press 2 for Certifiacate of a Company");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scan.nextLine());
        switch (choice) {
            case 1:
            System.out.println("Enter the compant ID number: ");
            use.compNum = scan.nextLine();
            use.serEuid();
            String query = "delete from cert_table where Entity_Unique_ID = '" + use.compNum + "';";
            dbcon.drop(query);
            System.out.println("Details Dropped");
            break;
            case 2:
            System.out.println("Enter the compant ID number: ");
            use.compNum = scan.nextLine();
            System.out.println("Enter Certifivate number: ");
            use.certNum = scan.nextLine();
            String certQuery = "delete from cert_table where Identity_Number like '"
            + use.compNum + "-" + use.certNum + "%'";
            dbcon.drop(certQuery); 
            System.out.println("Details Dropped");
            break;
        }


    }
    
}
