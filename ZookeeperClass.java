import java.io.File;
import java.util.Scanner;

public class ZookeeperClass {
    public void initiatezookeper() throws Exception {
        Scanner file = new Scanner(new File("zookeeper.txt"));

        while (file.hasNextLine()){
            String zookeeperLine = file.nextLine();
            System.out.println(zookeeperLine);
        }
        file.close();



    }
}
