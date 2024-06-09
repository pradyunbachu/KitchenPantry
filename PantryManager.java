//Pantry Manager Program
//Pradyun Bachu

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PantryManager{

    private static HashMap<String, ArrayList<String>> pantry = new HashMap<>();

    public static void main (String[] args){
        
        Scanner scanner = new Scanner(System.in);
        boolean inProgress = true;

        while(inProgress){
            System.out.println("Pantry Inventory:");
            System.out.println("1. Add a shelf");
            System.out.println("2. Add an item to a shelf");
            System.out.println("3. View items on a shelf");
            System.out.println("4. View all items");
            System.out.println("5. Clear a shelf");
            System.out.println("6. Close");
            System.out.println("Type either 1, 2, 3, 4, 5, or 6");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){
                addShelf(scanner);
            }
            else if (choice == 2){
                addItem(scanner);
            }
            else if (choice == 3){
                viewShelf(scanner);
            }
            else if (choice == 4){
                viewAllItems();
            }
            else if (choice == 5){
                clearShelf(scanner);
            }
            else if (choice == 6){
                inProgress = false;
            }
            else{
                System.out.println("Choice not found, please type in '1', '2', '3', '4', '5', or '6'");
            }
        }
        scanner.close();
    }

    private static void addShelf(Scanner scanner){
        System.out.println("Please type the name of the shelf you would like to add:");
        String shelf = scanner.nextLine();
        if (!pantry.containsKey(shelf)) {
            pantry.put(shelf, new ArrayList<>());
            System.out.println("Shelf " + shelf + " added to pantry.");
        } else {
            System.out.println("Shelf " + shelf + " already exists.");
        }
    }

    private static void addItem(Scanner scanner){
        System.out.println("Please type the name of the shelf to add an item to:");
        String shelf = scanner.nextLine();
        if (pantry.containsKey(shelf)) {
            System.out.println("Please type the item you would like to add:");
            String item = scanner.nextLine();
            pantry.get(shelf).add(item);
            System.out.println(item + " added to shelf " + shelf);
        } else {
            System.out.println("Shelf " + shelf + " does not exist. Please add the shelf first.");
        }
    }

    private static void viewShelf(Scanner scanner){
        System.out.println("Please type the name of the shelf you would like to view:");
        String shelf = scanner.nextLine();
        if (pantry.containsKey(shelf)) {
            System.out.println("Items on shelf " + shelf + ":");
            for (String item : pantry.get(shelf)) {
                System.out.println(" - " + item);
            }
        } else {
            System.out.println("Shelf " + shelf + " does not exist.");
        }
    }

    private static void viewAllItems(){
        System.out.println("The pantry currently consists of:");
        for (String shelf : pantry.keySet()) {
            System.out.println("Shelf " + shelf + ":");
            for (String item : pantry.get(shelf)) {
                System.out.println(" - " + item);
            }
        }
    }

    private static void clearShelf(Scanner scanner){
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
}
