import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadToFile {
    public static void main(String[] args) throws IOException {
        String fileName = "example.txt"; // The name of the file to read from

        // Initialize BufferedReader
        BufferedReader reader = new BufferedReader(new FileReader(fileName)); 
        String line; // Variable to hold each line read from the file
        // Read each line from the file until the end of the file is reached
        while ((line = reader.readLine()) != null) {
            System.out.println(line); // Print the line to the console
        }
        reader.close(); // Close the BufferedReader
    }
}
