import java.util.Scanner;
import java.util.*;

public class App {

    static Scanner sc = new Scanner(System.in);

    static void IAFinfo() {
        String info = "\nABOUT IAF\n" + "The International Accreditation Forum (IAF) is a worldwide association" +
                " of accreditation bodies and other bodies interested in conformity assessment in the fields of management"
                +
                " systems, products, processes, services, personnel, validation and verification and other similar programmes"
                +
                " of conformity assessment.\n"
                + "Our primary function is to develop a single worldwide program of conformity" +
                " assessment which reduces risk for businesses and their customers by assuring them that accredited" +
                " certificates and validation and verification statements may be relied upon.";
        String[] strArray = null;
        strArray = info.split(" ");
        justification jus = new justification();
        List<String> res = new ArrayList<String>();
        res = jus.fullJustify(strArray, 75);
        ListIterator<String> lItr = res.listIterator();
        while (lItr.hasNext()) {
            System.out.println(lItr.next());
        }
    }

    static int ToDo() {
        System.out.println("1. Know About IAF.\n2. Search.\n3. Login.\n4. Sign Up.\n5. Exit");
        System.out.print("What would you like to do?? ");
        int ch = Integer.parseInt(sc.nextLine());
        return ch;
    }

    static int LoginPage() {
        System.out.println("\nLOGIN PAGE");
        System.out.println("1. Login as Admin (1).");
        System.out.println("2. Login as Controller (2).");
        System.out.println("3. Back");
        System.out.print("Login as: ");
        int option = Integer.parseInt(sc.nextLine());
        return option;
    }

    static void Login(int login) throws Exception {

        switch (login) {
            case 1:
                Admin admin = new Admin();
                admin.adminPage();
                break;
            case 2:
                Controller controller = new Controller();
                controller.controllerPage();
                break;
        }

    }

    static void HomePage() throws Exception {
        boolean con2 = true;
        do {
            System.out.println("\nINTERNATIONAL ACCREDIATION FORUM");
            System.out.println("Celebrating 30 Years\n");

            int todo = ToDo();
            switch (todo) {
                case 1:
                    IAFinfo();
                    break;
                case 2:
                    User use = new User();
                    try {
                        use.search(true);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case 3:
                    int log = LoginPage();
                    Login(log);
                    break;
                case 4:
                    Signup signup = new Signup();
                    signup.signup();
                    break;
                case 5:
                    System.out.println("SEE YOU NEXT TIME AROUND.\n");
                    con2 = false;
                    break;
                default:
                    System.out.println("\nInvalid Choice.\n");
                    break;
            }
        } while (con2);
    }

    public static void main(String[] args) throws Exception {
        HomePage();
        sc.close();
    }
}
