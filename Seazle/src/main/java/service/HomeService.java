package service;

import domain.Gather;
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
        List<Location> locations = new ArrayList<>();
        if (locationListRequest.getSport().isEmpty()) {
            locations = locationRepository.findAll();
        }
        else{
            String[] sports = locationListRequest.getSport().split(",");
            for(String sport : sports) {
                List<Location> locationsBySport = locationRepository.findAllBySport(sport);
                locations.removeAll(locationsBySport);
                locations.addAll(locationsBySport);
            }
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
        List<Location> locationSearch = new ArrayList<>();
        List<Location> locations = locationRepository.findAllBySport(input);
        for(Location location : locations) {
            if(location.getName().contains(input)) {
                locationSearch.add(location);
            }
        }
        return locationSearch.stream().map(LocationResponse::locationResponse).toList();
    }

    public List<GatherResponse> getGatherList(GatherListRequest gatherListRequest) {
        List<Gather> gathers=new ArrayList<>();
        if(gatherListRequest.getSport().isEmpty()){
            gathers=gatherRepository.findAll();
        }
        else{
            gathers=gatherRepository.findAllBySport(gatherListRequest.getSport());
        }
        if(!gatherListRequest.getDate().isEmpty()){
            for(Gather gather : gathers) {
                if (!gather.getTime().equals(gatherListRequest.getDate())) {
                    gathers.remove(gather);
                }
            }
        }
        return gathers.stream().map(GatherResponse::gatherResponse).toList();
    }


}
