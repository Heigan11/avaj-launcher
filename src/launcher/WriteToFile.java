package launcher;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void writeToFile(String msg){
        try {
            FileWriter myWriter = new FileWriter("simulation.txt", true);
            myWriter.write(msg +"\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
