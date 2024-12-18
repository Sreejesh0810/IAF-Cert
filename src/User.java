import java.sql.*;
import java.util.*;

public class User {
    static Scanner scan = new Scanner(System.in);
    static boolean emptySet = false;
    static dbConnection dbcon = new dbConnection();
    String compNum = "";
    String certNum = "";
    ResultSet result = null;


    protected boolean isEmpty() throws SQLException {

        if (result.next() == false) {
            emptySet = true;
            return true;
        }
        else {
            return false;
        }
    }


    protected void serEuid() throws SQLException {
        String query = "select Entity_Unique_ID, Identity_Number from cert_table where Entity_Unique_ID = "
        + "'" + compNum + "';";
        result = dbcon.select(query);

    }

    protected String[] searchCertID(boolean metadata) throws SQLException {
        String query2 = "select * from cert_table where Identity_Number like '"
        + compNum + "-" + certNum + "%'";
        result = dbcon.select(query2);
        String[] colname = new String[23];
        if (metadata) {
            ResultSetMetaData rMetaData = result.getMetaData();
            System.out.println("List of column name: ");
            int count = rMetaData.getColumnCount();
            for (int i = 1; i <=count; i++) {
                colname[i-1] = rMetaData.getColumnName(i);

            }
            return colname;
        }
        return null;
    }

    protected void search() {

    }


    protected void search1(boolean metadata) throws SQLException {
        System.out.print("Enter the company ID: ");
        compNum = scan.nextLine();
        serEuid();
        while (result.next()) {
            System.out.println(result.getString(1) + "\t" + result.getString(2));
        }

        System.out.println("Enter Certification numer: ");
        certNum = scan.nextLine();
        String[] metaData = searchCertID(metadata);
        while (result.next()) {
            for (int i= 1; i < 23 + 1; i++) {
                String print = metaData[i-1] + ": " + result.getString(i);
                System.out.println(print);
            }
    }

        dbcon.disconnectFromDatabase();

    }

    
}


