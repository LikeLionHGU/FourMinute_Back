package com.example.seazle.domain.comparator;

import com.example.seazle.domain.Location;

import java.util.Comparator;

public class PriceComparator implements Comparator<Location> {
    @Override
    public int compare(Location location1, Location location2) {
        return Long.compare(location1.getPrice1() + location1.getPrice2(), location2.getPrice1() + location2.getPrice2());
    }
}
