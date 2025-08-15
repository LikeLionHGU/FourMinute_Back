package service;

import domain.Location;
import domain.comparator.PriceComparator;
import domain.comparator.ReviewComparator;
import domain.comparator.ScoreComparator;
import dto.request.GatherListRequest;
import dto.request.LocationListRequest;
import dto.response.GatherResponse;
import dto.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.GatherRepository;
import repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HomeService {

    private final LocationRepository locationRepository;
    private final GatherRepository gatherRepository;

    public List<LocationResponse> getLocationList(LocationListRequest locationListRequest) {
        String[] sports = locationListRequest.getSport().split(",");
        List<Location> locations = new ArrayList<>();
        for(String sport : sports) {
            List<Location> locationsBySport = locationRepository.findAllBySport(sport);
            locations.addAll(locationsBySport);
        }
        if(locationListRequest.getSorting()==0) {
            locations.sort(new ScoreComparator());
        }
        if(locationListRequest.getSorting()==1) {
            locations.sort(new PriceComparator());
        }
        if(locationListRequest.getSorting()==2) {
            locations.sort(new ReviewComparator());
        }
        return locations.stream().map(LocationResponse::locationResponse).toList();
    }

    public List<LocationResponse> getLocationSearch(String input) {

    }

    public List<GatherResponse> getGatherList(GatherListRequest gatherListRequest) {

    }


}
