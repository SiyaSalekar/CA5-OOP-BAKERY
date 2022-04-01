package org.project;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.project.BusinessObjects.App;
import org.project.DAO.MySqlStaffDAO;
import org.project.DAO.StaffDAOInterface;
import org.project.DTO.Staff;
import org.project.Exceptions.DaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for BakeryStaff App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    /** findStaffByID() Tests */
    @Test
    public void findStaffByIDTest1()
    {
        System.out.println("Test 1 - findStaffbyID(); ID = 3; Staff with ID 3 exists");
        Staff sOriginal = new Staff(3, "Josh", "Butler", 12.8, 40, "jb@gmail.com");
        int id = 3;
        Staff sTest = null;
        StaffDAOInterface IStaffDao = new MySqlStaffDAO();
        try{
        sTest =  IStaffDao.findStaffbyID(id);
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
        assertEquals(sOriginal, sTest);

    }
    @Test
    public void findStaffByIDTest2()
    {
        System.out.println("Test 2 - findStaffbyID(); ID = 90; Staff with ID 90 doesn't exist.");
        Staff sOriginal = null;
        int id = 90;
        Staff sTest = null;
        StaffDAOInterface IStaffDao = new MySqlStaffDAO();
        try{
            sTest =  IStaffDao.findStaffbyID(id);
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
        assertEquals(sOriginal, sTest);

    }

    /** Filter Staff using Work Hour Comparator Test */
    /** The test may not pass if changes are made to the Database since it is designed for the DB at this particular time*/
//    @Test
//    public void findStaffUsingFilterWorkHourComparatorTest()
//    {
//        System.out.println("Test 3 - findStaffUsingFilterWorkHourComparator()");
//
//        List<Staff> staffListTest = new ArrayList<>();
//        List<Staff> staffList = new ArrayList<>();
//        staffList.add(new Staff(11,"Ira","Thete",10.80,10	,"it@gmail.com"));
//        staffList.add(new Staff(1,"Tanya" ,"Martin",10.80,20,"tm@gmail.com"));
//        staffList.add(new Staff(5,"Efe","Leonard",10.80,20,"el@gmail.com"));
//        staffList.add(new Staff(6,"Aaron","McCabe",12.80,20,"am@gmail.com"));
//        staffList.add(new Staff(8,	"Jake"           	,"Cheruil",        	11.80,	        20,					"jach@gmail.com"));
//        staffList.add(new Staff(4,	"Claire",         	"Martin",         	12.80,	        30,					"cm@gmail.com"    ));
//        staffList.add(new Staff(7,	"John",           	"Stewart",        	11.80,	        30,"jost@gmail.com"));
//        staffList.add(new Staff(10,	"Hayley",         	"Dixon",          	11.80,	        30,					"hd@gmail.com"));
//        staffList.add(new Staff(2, "Siya", "Salekar", 11.8, 40, "ss@gmail.com"));
//        staffList.add(new Staff(3, "Josh", "Butler", 12.8, 40, "jb@gmail.com"));
//        staffList.add(new Staff(9, "Patrick", "Donchev", 10.8, 40, "ptr@gmail.com"));
//
//        StaffDAOInterface IStaffDao = new MySqlStaffDAO();
//        try{
//            staffListTest =  IStaffDao.findStaffUsingFilterWorkHourComparator();
//        }
//        catch( DaoException e )
//        {
//            e.printStackTrace();
//        }
//        assertEquals(staffList, staffListTest);
//
//    }

    /** Insert Staff Test*/
    @Test
    public void InsertStaffTest()
    {
        String res = "";
        String test = "Added Successfully";
        System.out.println("Test 4 - insertStaff()");
        int id = 46;
        String first_name = "Tina";
        String last_name = "Martin";
        double rate_per_hour = 13.80;
        int work_hours = 40;
        String email = "tim@gmail.com";

        StaffDAOInterface IStaffDao = new MySqlStaffDAO();

        try {
            res = IStaffDao.addStaff(new Staff(id, first_name, last_name, rate_per_hour, work_hours, email));
        }catch( DaoException e )
        {
            e.printStackTrace();
        }
        assertEquals(res,test);

    }

    /** JSON String Staff by ID Test*/
    @Test
    public void JSONStringFindStaffByIDTest()
    {
        System.out.println("Test 5 - JSONStringFindStaffByID()");
        String res="";
        String test = "{\n" +
                "  \"staff_id\": 3,\n" +
                "  \"first_name\": \"Josh\",\n" +
                "  \"rate_per_hour\": 12.8,\n" +
                "  \"last_name\": \"Butler\",\n" +
                "  \"work_hours\": 40,\n" +
                "  \"email\": \"jb@gmail.com\"\n" +
                "}";
        StaffDAOInterface IStaffDao = new MySqlStaffDAO();
        int id = 3;
        try {
            res = IStaffDao.findStaffbyIDJSON(id);

        }catch( DaoException e )
            {
                e.printStackTrace();
            }

        assertEquals(res,test);
    }

    /** Delete Staff Test*/
    @Test
    public void DeleteStaffByIDTest()
    {
        String res = "";
        String test = "Deleted Successfully";
        System.out.println("Test 6 - deleteStaffByID()");
        int id = 5;


        StaffDAOInterface IStaffDao = new MySqlStaffDAO();

        try {
             res = IStaffDao.deleteById(id);
        }catch( DaoException e )
        {
            e.printStackTrace();
        }
        assertEquals(res,test);

    }


}
