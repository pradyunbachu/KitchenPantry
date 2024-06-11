import java.io.*;

public class FileRead{

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(
                new FileReader("/Users/pradyunbachu/Documents/CS_Projects/Side_Projects/KitchenPantry/output.txt"));
            String s;
            while((s = br.readLine())  != null){
                System.out.println(s);
            }
            br.close();
        } catch (Exception e) {
            return;
        }
    }
}