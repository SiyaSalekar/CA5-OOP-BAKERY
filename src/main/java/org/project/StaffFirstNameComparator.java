package org.project;


import java.util.Comparator;

public class StaffFirstNameComparator implements Comparator<Staff>
{
    @Override
    public int compare( Staff s1, Staff s2)
    {
        return s1.getFirst_name().compareTo(s2.getFirst_name());
    }
}
