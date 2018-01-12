import java.util.Scanner;

public class EndOfApplication {
    public void initiateeoapp() {
        Scanner userscnr = new Scanner(System.in);
        String userDecision = "";
        System.out.print("Type GO to return to the login sreen" + "\n" + "or type EXIT to exit the program: ");
        userDecision = userscnr.next().toLowerCase().trim();
        if (userDecision.equals("exit")) return;
    }
}




