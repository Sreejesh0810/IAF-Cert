import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Analysis {

    static dbConnection dbcon = new dbConnection();
    static String[][] totalStateCount = new String[30][];

    public void analysisPage() {
        System.out.println("Here are some Analysis on the Information of the certificates.");

    }

    protected void countCert() throws SQLException {
        String query = "select Identity_Number from cert_table;";
        ResultSet result = dbcon.select(query);
        while (result.next()) {
            String certID = result.getString(1).split("-")[1];

        }
    }

    public static void main(String[] args) throws SQLException {
    // protected void countState() throws SQLException {
        String subQuery = "select distinct Entity_State from cert_table;";
        ResultSet subResult = dbcon.select(subQuery);
        int i = 0;
        while (subResult.next()) {
            String[] stateCount = new String[2];
            String state = subResult.getString(1);
            String query = "select count(Identity_Number) from cert_table where Entity_State = '"
                    + state + "';";
            ResultSet result = dbcon.select(query);
            while (result.next()) {
                String count = Integer.toString(result.getInt(1));
                stateCount[0] = state;
                stateCount[1] = count;
                totalStateCount[i] = stateCount;
                i+=1;
            }
        }
        
        try {
            for (String[] x : totalStateCount) {
                System.out.println(x[0] + " has " + x[1] + " certificates registered in our database\n");
            }
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
