import java.io.*;
import java.security.MessageDigest;
import java.util.*;


public class Main {


    //Method #1 - This is for the Zoo@Bowery Splashscreen
    public static void runSplashScreen() {

        System.out.println("");
        System.out.println("_|_|_|_|_|                          _|_|_|_|_|    _|_|_|                                                              ");
        System.out.println("      _|      _|_|      _|_|      _|          _|  _|    _|    _|_|    _|      _|      _|    _|_|    _|  _|_|  _|    _| ");
        System.out.println("    _|      _|    _|  _|    _|  _|    _|_|_|  _|  _|_|_|    _|    _|  _|      _|      _|  _|_|_|_|  _|_|      _|    _| ");
        System.out.println("  _|        _|    _|  _|    _|  _|  _|    _|  _|  _|    _|  _|    _|    _|  _|  _|  _|    _|        _|        _|    _|  ");
        System.out.println("_|_|_|_|_|    _|_|      _|_|    _|    _|_|_|_|    _|_|_|      _|_|        _|      _|        _|_|_|  _|          _|_|_|  ");
        System.out.println("                                  _|                                                                                _|  ");
        System.out.println("                                    _|_|_|_|_|_|                                                                _|_|    ");
        return;
    }


    //Method #2 - Load the Zookeeper's Role File
    public static void runZookeeperRole() throws Exception {
        ZookeeperClass zRole = new ZookeeperClass();
        zRole.initiatezookeper();

        return;
    }


    //Method #3 - Load the Admin's Role File
    public static void runAdminRole() throws Exception {
        AdminClass adRole = new AdminClass();
        adRole.initiateadmin();

        return;
    }


    //Method #4 - Load the Veterinarian's Role File
    public static void runVeterinarianRole() throws Exception {
        VeterinarianClass vetRole = new VeterinarianClass();
        vetRole.initiateveterinarian();

        return;
    }


    //Method #5 - End of application
    public static void runEndOfApplication() {
        EndOfApplication eoApp = new EndOfApplication();
        eoApp.initiateeoapp();

        return;
    }




    public static void main(String[] args) throws Exception {

        //Initializing strings, integers, and booleans
        boolean isRunning = true;
        String tryAgain = "";
        String userDecision = "";
        int countDownToExit = 0;
        final int failedLoginAttempt = 3;
        String sessionUserPassword = "";
        String userRole = "";
        int loginSucces = 0;


        //This is the splashscreen for my app
        runSplashScreen();


        //This starts the while loop that will have my program restart to try login again
        while (isRunning) {


            //This is the scanner that will pull the file into the application. Make sure the file is saved in the folder "Java Program"
            Scanner file = new Scanner(new File("credentials.txt"));
            file.useDelimiter(",|\\t|\\n");


            //This is the ArrayList that holds the information what was once in the file
            ArrayList<String> userarraylist = new ArrayList<String>();
            while (file.hasNext()) {
                //file.useDelimiter("\t");
                userarraylist.add(file.next());
            }
            file.close();


            // This is to ask for userName and passWord
            String userName = "";
            String userPassword = "";
            int userNameIndexLocation = 0;
            int userPasswordIndexLocation = 0;
            int userRoleIndexLocation = 0;
            Scanner userscnr = new Scanner(System.in);
            System.out.println("");
            System.out.println("Welcome to Zoo@Bowery");
            System.out.print("Please enter your username: ");
            userName = userscnr.next().toLowerCase().trim();
            userscnr.nextLine(); //This is being invoked to consume a mythical new line character
            System.out.print("Please enter your password: ");
            userPassword = userscnr.nextLine();


            //Passing user password to MD5
            String original = userPassword;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            sessionUserPassword = sb.toString();



            //searches through the arraylist
            for (int i = 0; i < userarraylist.size(); i++) {
                if (userarraylist.get(i).equals(userName)) {
                    userNameIndexLocation = i;
                    userPasswordIndexLocation = userNameIndexLocation + 1;
                    userRoleIndexLocation = userNameIndexLocation + 3;
                }
            }



            //if statements for unsuccessful logins
            if (userName != userarraylist.get(userNameIndexLocation) || userPassword != userarraylist.get(userPasswordIndexLocation)) {
                countDownToExit++;
            }
            if (countDownToExit == failedLoginAttempt) {
                System.out.println("Your session has been terminated :-( " + "\n" + "please contact your administrator");
                return;
            }



            //if statement for login success with else to terminate or return to login
            if (userarraylist.get(userNameIndexLocation).equals(userName) && userarraylist.get(userPasswordIndexLocation).equals(sessionUserPassword)) {
                userRole = userarraylist.get(userRoleIndexLocation).trim();
                loginSucces = loginSucces + 1;
                //System.out.println("This is the role of the user: " + userarraylist.get(userRoleIndexLocation));
            } else {
                System.out.print("Your username and password were incorrect!" + "\n" + "Would you like to reattempt? Type YES to try again, EXIT to leave: ");
                tryAgain = userscnr.next().toLowerCase().trim();
                while (tryAgain.equals("yes")) {
                    break;
                }
                while (tryAgain.equals("exit")) {
                    return;
                }
            }


            //Determine the file that gets loaded
            if (loginSucces == 1 && userRole.equals("zookeeper")) {
                runZookeeperRole();
            } else if (loginSucces == 1 && userRole.equals("admin")) {
                runAdminRole();
            } else if (loginSucces == 1 && userRole.equals("veterinarian")) {
                runVeterinarianRole();
            }


            //Run the end of application scenario
            if (loginSucces == 1) {
                runEndOfApplication();
                file.close();

            }
        }
    }
}



