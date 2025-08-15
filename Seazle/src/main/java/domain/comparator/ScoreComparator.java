package domain.comparator;

import domain.Location;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Location> {
    @Override
    public int compare(Location location1, Location location2) {
        return location1.getScore().compareTo(location2.getScore());
    }
}
