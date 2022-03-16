package org.project;

import java.io.IOException;
import java.util.*;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Arraylist Initialization
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
        staff_list.add(new Staff(9, "Hayley","Dixon", 11.8, 30, "hd@gmail.com"));
        staff_list.add(new Staff(10, "Ira","Thete", 10.8, 10, "it@gmail.com"));
        staff_list.add(new Staff(0, "Tanya","Martin", 10.8, 30, "tm@gmail.com"));
        staff_list.add(new Staff(1, "Siya","Salekar", 11.8, 20, "ss@gmail.com"));


        //Main Menu
        final String MENU_ITEMS = "\n*** MAIN MENU ***\n"
                + "1. Display All Staff\n"
                + "2. Retrieve a Staff object by key from HashMap\n"
                + "3. Display Staff-Station from TreeMap in order of Staff_First_Name\n"
                + "4. Priority Sequence Simulation\n"
                + "5. PriorityQueue Two-Field Comparison Demo\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int DISPLAY = 1;
        final int HASH_RETRIEVE = 2;
        final int TREE_RETRIEVE = 3;
        final int PriorityQueueDisplay = 4;
        final int TwoFieldComparison = 5;
        final int EXIT = 6;



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
                        System.out.println("All Staff");
                        System.out.println();
                        displayStaff(staff_list);
                        break;
                    case HASH_RETRIEVE:
                        System.out.println("Hash Map Retrieve option chosen");
                        hashRetrieve(staff_list);
                        break;
                    case TREE_RETRIEVE:
                        System.out.println("Display using TreeMap option chosen");
                        treeRetrieve(staff_list);
                        break;
                    case PriorityQueueDisplay:
                        System.out.println("Priority Sequence Simulation - Work Hours");
                        demoWorkHoursComparator();
                        break;
                    case TwoFieldComparison:
                        PriorityQueueTwoFieldComparisonDemo(staff_list);
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

        System.out.println("\nExiting Main Menu, Thank you!.");

    }

    //Feature1
    public static void displayStaff(ArrayList<Staff> staffArrayList) {
        System.out.printf("%5s\t%-15s\t%-15s\t%-2s\t%5s %15s\n","ID","First_Name","Last_Name","Rate_Per_Hour","Work_Hours","Email");

        for (Staff s : staffArrayList) {
            System.out.printf("%5d\t%-15s\t%-15s\t%-2.2f\t%10d\t\t\t\t\t%-20s\n",s.getStaff_id(),s.getFirst_name(),s.getLast_name(),s.getRate_per_hour(),s.getWork_hours(),s.getEmail());
        }
    }

    //Feature 2
    public static void hashRetrieve(ArrayList<Staff> staffArrayList) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Staff> map = new HashMap<>();
        for(Staff s: staffArrayList){
            map.put(s.getStaff_id(),s);
        }

        // Display all possible options to user
        Set<Integer> keySet = map.keySet();  // get all keys
        System.out.println("Choose from Staff IDs:");
        for (Integer id : keySet) {
            System.out.print(id + ", ");
        }

        try{
            System.out.println("\nEnter Staff ID");
            int key = sc.nextInt();
            if(map.containsKey(key)){
                System.out.println("Object with key " + key + " - " + map.get(key));
            } else {
                System.out.println("Staff object with key " + key+" is NOT found.");
            }
        }catch (InputMismatchException e){
            System.out.println("Id is not a Number");
        }

    }

    //Feature 3
    public static void treeRetrieve(ArrayList<Staff> staffArrayList){
        //Station objects
        Station s1 = new Station(1,"Baking");
        Station s2 = new Station(2,"Deserts");
        Station s3 = new Station(3,"Kneading");

        Map<Staff, Station> stationMap = new TreeMap<>(new StaffFirstNameComparator());

        // filling values in treeMap as per work hours just to avoid tedious hard coding

        for(Staff s: staffArrayList){
            if(s.getWork_hours()==20){
                stationMap.put(s, s1);
            }
            else if(s.getWork_hours()==20){
                stationMap.put(s,s2);
            }
            else{
                stationMap.put(s,s3);
            }
        }

        System.out.println("\nTreeMap: [ Staff -> Station ] in Order of Staff First Name\n");

        // for each Entry in the set of all entries
        for (Map.Entry<Staff, Station> entry : stationMap.entrySet()) {
            Staff staff = entry.getKey();
            Station station = entry.getValue();
            System.out.println("{"+staff+"} -> "+station);
        }

    }

    //Feature 4
    private static void demoWorkHoursComparator() {
        PriorityQueue<Staff> queue = new PriorityQueue<Staff>(
                new StaffWorkHoursComparator(SortType.Ascending));

        System.out.println();
        System.out.println("Added two third and second priority elements");

        queue.add(new Staff(2, "John","Stewart", 11.8, 30, "jost@gmail.com"));
        queue.add(new Staff(3, "Jake","Cheruil", 11.8, 42, "jach@gmail.com"));
        queue.add(new Staff(4, "Patrick","Donchev", 10.8, 40, "patryck@gmail.com"));
        queue.add(new Staff(5, "Hayley","Dixon", 11.8, 35, "hd@gmail.com"));


        System.out.println();
        System.out.println("Remove and display one element");
        System.out.println(queue.remove());

        //Added a top priority element
        queue.add(new Staff(1, "Aaron","McCabe", 12.8, 20, "am@gmail.com"));
        System.out.println();
        System.out.println("Added a top priority element");
        System.out.println();
        System.out.println("Display all Values in order of Work Hour Priority:");
        //iterator & remove
        Iterator<Staff> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(queue.remove());
        }
    }

    public static void PriorityQueueTwoFieldComparisonDemo(ArrayList<Staff> stafflist){


        PriorityQueue<Staff> queue = new PriorityQueue<Staff>();
        for(Staff s: stafflist){
            queue.add(s);
        }

        System.out.println("Staff in priority order of Work Hours Within FistName and LastName");
        Iterator<Staff> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(queue.remove());
        }
    }

}

