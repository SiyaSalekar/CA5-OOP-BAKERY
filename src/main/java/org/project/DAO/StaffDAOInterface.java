package org.project.DAO;

import org.project.DTO.Staff;
import org.project.Exceptions.DaoException;
import org.project.StaffFirstNameComparator;

import java.util.List;

public interface StaffDAOInterface
{
    public List<Staff> findAllStaff() throws DaoException;
    public Staff findStaffbyID(int StaffID) throws DaoException;
    public void deleteById(int Id) throws DaoException;
    public void addStaff(Staff staff) throws DaoException;
    public List<Staff> findStaffUsingFilterWorkHour() throws DaoException;
    public List<Staff> findStaffUsingFilterFirstName() throws DaoException;

}