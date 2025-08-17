package com.example.seazle.domain.comparator;

import com.example.seazle.domain.Location;

import java.util.Comparator;

public class ReviewComparator implements Comparator<Location> {
    @Override
    public int compare(Location location1, Location location2) {
        return location1.getReviewCount().compareTo(location2.getReviewCount())*-1;
    }
}
