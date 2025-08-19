package com.example.seazle.domain.comparator;

import com.example.seazle.domain.Location;

import java.util.Comparator;

public class ReviewComparator implements Comparator<Location> {
    @Override
    public int compare(Location location1, Location location2) {
        Long count1=(long)location1.getReviews().size();
        Long count2=(long)location2.getReviews().size();
        return count1.compareTo(count2)*-1;
    }
}
