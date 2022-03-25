package org.project.DAO;

import org.project.DTO.Staff;
import org.project.Exceptions.DaoException;
import java.util.List;

public interface StaffDAOInterface
{
    public List<Staff> findAllStaff() throws DaoException;
    public Staff findStaffbyID(int StaffID) throws DaoException;


}