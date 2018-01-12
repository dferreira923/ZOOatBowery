import java.io.File;
import java.util.Scanner;

public class VeterinarianClass {
    public void initiateveterinarian() throws Exception {
        Scanner file = new Scanner(new File("veterinarian.txt"));

        while (file.hasNextLine()){
            String veterinarianLine = file.nextLine();
            System.out.println(veterinarianLine);
        }
        file.close();
    }
}
