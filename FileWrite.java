import java.io.*;

public class FileWrite{

    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(
                new FileWriter("/Users/pradyunbachu/Documents/CS_Projects/Side_Projects/KitchenPantry/output.txt"));

            bw.write("test\n");
            bw.write("test\n");
            bw.write("test\n");
            bw.close();
            
            
        } catch (Exception e) {
            return;
        }
    }
}