import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void main(String[] args) throws IOException {
        String fileName = "example.txt"; // The name of the file to write to
        String content = "Xyz123"; // The content to write to the file

        // Initialize BufferedWriter
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)); 
        writer.write(content); // Write the content to the file
        writer.close(); // Close the BufferedWriter
        System.out.println("Content written to file successfully."); // Inform the user that writing was successful
    }
}
