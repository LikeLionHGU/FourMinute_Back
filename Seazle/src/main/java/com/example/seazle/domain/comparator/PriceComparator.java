package com.example.seazle.domain.comparator;

import com.example.seazle.domain.Location;

import java.util.Comparator;

public class PriceComparator implements Comparator<Location> {
    @Override
    public int compare(Location location1, Location location2) {
        return location1.getPrice1().compareTo(location2.getPrice1());
    }
}
