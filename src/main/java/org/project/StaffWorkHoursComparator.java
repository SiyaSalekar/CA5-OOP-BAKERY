package org.project;

import org.project.DTO.Staff;

import java.util.Comparator;

public class StaffWorkHoursComparator
        implements Comparator<Staff>
{
    private SortType sortType;

    public StaffWorkHoursComparator(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare(Staff s1, Staff s2)
    {
        int direction = sortType.getValue();
        return direction * (s1.getWork_hours() - s2.getWork_hours());
    }
}
