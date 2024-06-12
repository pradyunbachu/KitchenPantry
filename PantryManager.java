//Pantry Manager Program
//Pradyun Bachu

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class PantryManager {

    private static HashMap<String, HashMap<String, Integer>> pantry = new HashMap<>();
    private static String directoryPath = "/Users/pradyunbachu/Documents/CS_Projects/Side_Projects/KitchenPantry/Pantries/";
    private static String filePath;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("_______________________________________________________________________________________________________________________________________________");
        System.out.println("Welcome to the Pantry Manager Program");
        System.out.println("Please enter the name of the pantry file you want to load (or create a new one 'name.txt'):");
        String fileName = scanner.nextLine();
        filePath = directoryPath + fileName;

        loadPantryFromFile(); // Load pantry data at the start

        boolean inProgress = true;

        while (inProgress) {
            System.out.println("Pantry Inventory:");
            System.out.println("1. Add a shelf");
            System.out.println("2. Add an item to a shelf");
            System.out.println("3. View items on a shelf");
            System.out.println("4. View all items");
            System.out.println("5. Clear a shelf");
            System.out.println("6. Save pantry to file");
            System.out.println("7. Close");
            System.out.println("Type either 1, 2, 3, 4, 5, 6, or 7");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addShelf(scanner);
            } else if (choice == 2) {
                addItem(scanner);
            } else if (choice == 3) {
                viewShelf(scanner);
            } else if (choice == 4) {
                viewAllItems();
            } else if (choice == 5) {
                clearShelf(scanner);
            } else if (choice == 6) {
                savePantryToFile();
            } else if (choice == 7) {
                inProgress = false;
                savePantryToFile(); // Save pantry data before closing
            } else {
                System.out.println("Choice not found, please type in '1', '2', '3', '4', '5', '6', or '7'");
            }
        }
        scanner.close();
    }

    private static void addShelf(Scanner scanner) {
        System.out.println("Please type the name of the shelf you would like to add:");
        String shelf = scanner.nextLine();
        if (!pantry.containsKey(shelf)) {
            pantry.put(shelf, new HashMap<>());
            System.out.println("Shelf " + shelf + " added to pantry.");
        } else {
            System.out.println("Shelf " + shelf + " already exists.");
        }
    }

    private static void addItem(Scanner scanner) {
        System.out.println("Please type the name of the shelf to add an item to:");
        String shelf = scanner.nextLine();
        if (pantry.containsKey(shelf)) {
            System.out.println("Please type the item you would like to add:");
            String item = scanner.nextLine();
            HashMap<String, Integer> items = pantry.get(shelf);
            items.put(item, items.getOrDefault(item, 0) + 1);
            System.out.println(item + " added to shelf " + shelf);
        } else {
            System.out.println("Shelf " + shelf + " does not exist. Please add the shelf first.");
        }
    }

    private static void viewShelf(Scanner scanner) {
        System.out.println("Please type the name of the shelf you would like to view:");
        String shelf = scanner.nextLine();
        if (pantry.containsKey(shelf)) {
            System.out.println("Items on shelf " + shelf + ":");
            for (HashMap.Entry<String, Integer> entry : pantry.get(shelf).entrySet()) {
                System.out.println(" - " + entry.getKey() + " (" + entry.getValue() + ")");
            }
        } else {
            System.out.println("Shelf " + shelf + " does not exist.");
        }
    }

    private static void viewAllItems() {
        System.out.println("The pantry currently consists of:");
        for (String shelf : pantry.keySet()) {
            System.out.println("Shelf " + shelf + ":");
            for (HashMap.Entry<String, Integer> entry : pantry.get(shelf).entrySet()) {
                System.out.println(" - " + entry.getKey() + " (" + entry.getValue() + ")");
            }
        }
    }

    private static void clearShelf(Scanner scanner) {
        System.out.println("Please type the name of the shelf you would like to clear:");
        String shelf = scanner.nextLine();
        if (pantry.containsKey(shelf)) {
            System.out.println("Are you sure you would like to clear shelf " + shelf + "? Type 'yes' or 'no'");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                pantry.get(shelf).clear();
                System.out.println("All items have been cleared from shelf " + shelf);
            } else {
                System.out.println("Clear operation successfully cancelled.");
            }
        } else {
            System.out.println("Shelf " + shelf + " does not exist.");
        }
    }

    private static void savePantryToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String shelf : pantry.keySet()) {
                bw.write(shelf + ":");
                for (HashMap.Entry<String, Integer> entry : pantry.get(shelf).entrySet()) {
                    bw.write(entry.getKey() + " (" + entry.getValue() + "),");
                }
                bw.newLine();
            }
            System.out.println("Pantry saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving pantry to file.");
            e.printStackTrace();
        }
    }

    private static void loadPantryFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist. Starting with an empty pantry.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            pantry.clear();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) continue; // Handle malformed lines
                String shelf = parts[0];
                String[] items = parts[1].split(",");
                HashMap<String, Integer> itemList = new HashMap<>();
                for (String item : items) {
                    String[] itemParts = item.trim().split(" \\(");
                    String itemName = itemParts[0];
                    int itemCount = Integer.parseInt(itemParts[1].replace(")", ""));
                    itemList.put(itemName, itemCount);
                }
                pantry.put(shelf, itemList);
            }
            System.out.println("Pantry loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("Error loading pantry from file.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing pantry file. Please check the file format.");
            e.printStackTrace();
        }
    }
}
