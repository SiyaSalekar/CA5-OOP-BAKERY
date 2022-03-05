package org.project;

import java.util.ArrayList;

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

    }
}
