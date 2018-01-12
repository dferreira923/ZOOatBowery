import java.io.File;
import java.util.Scanner;

public class AdminClass {
    public void initiateadmin() throws Exception {
        Scanner file = new Scanner(new File("admin.txt"));

        while (file.hasNextLine()){
            String adminLine = file.nextLine();
            System.out.println(adminLine);
        }
        file.close();
    }
}
