import java.util.ArrayList;
import java.util.Scanner;

public class PantryManager{

    private static ArrayList<String> pantryItems = new ArrayList<>();

    public static void main (String[] args){
        
        Scanner scanner = new Scanner(System.in);
        boolean inProgress = true;

        while(inProgress){
            System.out.println("Pantry Inventory:");
            System.out.println("1. Add an item");
            System.out.println("2. View all items");
            System.out.println("3. Close");
            System.out.println("4. Clear pantry");
            System.out.println("Type either 1, 2, 3, or 4");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){
                addItem(scanner);
            }
            else if (choice == 2){
                viewItems();
            }
            else if(choice == 3){
                inProgress=false;
            }
            else if(choice==4){
                clearItems(scanner);
            }
            else{
                System.out.println("Choice not found, please type in '1', '2', '3', or '4'");
            }
        }
        scanner.close();

    }

    private static void addItem(Scanner scanner){
        System.out.println("Please type the item you would like to add:");
        String item = scanner.nextLine();
        pantryItems.add(item);
        System.out.println(item + "added to pantry");
    }

    private static void viewItems(){
        System.out.println("The pantry currently consists of:");
        for (String i: pantryItems){
            System.out.println(" , " + i);
        }
    }

    private static void clearItems(Scanner scanner){
        System.out.println("Are you sure you would like to clear the pantry? Type 'yes' or 'no'");
        String answer = scanner.nextLine();

        if (answer.equals("yes") || answer.equals("Yes")){
            pantryItems.clear();
            System.out.println("All items have been cleared from the pantry.");
        } else {
            System.out.println("Clear operation successfully cancelled.");
        }
    }



}
