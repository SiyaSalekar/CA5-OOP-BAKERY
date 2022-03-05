package org.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<Staff> staff_list = new ArrayList<>();

        staff_list.add(new Staff(0, "Tanya","Martin", 10.8, 20, "tm@gmail.com"));
        staff_list.add(new Staff(1, "Siya","Salekar", 11.8, 40, "ss@gmail.com"));
        staff_list.add(new Staff(2, "Josh","Butler", 12.8, 40, "jb@gmail.com"));
        staff_list.add(new Staff(3, "Claire","Martin", 12.8, 30, "cm@gmail.com"));
        staff_list.add(new Staff(4, "Efe","Leonard", 10.8, 20, "el@gmail.com"));
        staff_list.add(new Staff(5, "Aaron","McCabe", 12.8, 20, "am@gmail.com"));
        staff_list.add(new Staff(6, "John","Stewart", 11.8, 30, "jost@gmail.com"));
        staff_list.add(new Staff(7, "Jake","Cheruil", 11.8, 20, "jach@gmail.com"));
        staff_list.add(new Staff(8, "Patrick","Donchev", 10.8, 40, "patryck@gmail.com"));
        staff_list.add(new Staff(9, "Hayley","Dixon", 11.8, 30, "hayleydixon@gmail.com"));
        staff_list.add(new Staff(10, "Ira","Thete", 10.8, 20, "it@gmail.com"));


        final String MENU_ITEMS = "\n*** MAIN MENU ***\n"
                + "1. Display All Staff\n"
                + "2. Retrieve a Staff object by key from HashMap\n"
                + "3. Retrieve a Staff object by key from TreeMap\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int DISPLAY = 1;
        final int HASH_RETRIEVE = 2;
        final int TREE_RETRIEVE = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n"+MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case DISPLAY:
                        System.out.println("Passengers option chosen");
                        displayStaff(staff_list);
                        break;
                    case HASH_RETRIEVE:
                        System.out.println("Vehicles option chosen");
                        //hashRetrieve();
                        break;
                    case TREE_RETRIEVE:
                        System.out.println("Bookings option chosen");
                        //treeRetrieve();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |NumberFormatException e)
            {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    public static void displayStaff(ArrayList<Staff> staffArrayList) {

        for (Staff s : staffArrayList) {

        }
    }
}
