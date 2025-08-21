package com.example.seazle.service;

import com.example.seazle.domain.Gather;
import com.example.seazle.domain.Location;
import com.example.seazle.domain.comparator.PriceComparator;
import com.example.seazle.domain.comparator.ReviewComparator;
import com.example.seazle.domain.comparator.ScoreComparator;
import com.example.seazle.dto.request.GatherListRequest;
import com.example.seazle.dto.request.LocationListRequest;
import com.example.seazle.dto.response.GatherResponse;
import com.example.seazle.dto.response.LocationResponse;
import com.example.seazle.repository.ParticipateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.seazle.repository.GatherRepository;
import com.example.seazle.repository.LocationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HomeService {

    private final LocationRepository locationRepository;
    private final GatherRepository gatherRepository;
    private final ParticipateRepository participateRepository;

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
        List<Location> locations = locationRepository.findAll();
        for(Location location : locations) {
            if(location.getName().contains(input)||location.getDescription().contains(input)) {
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
            String[] sports = gatherListRequest.getSport().split(",");
            for(String sport : sports) {
                List<Gather> gathersBySport = gatherRepository.findAllBySport(sport);
                gathers.removeAll(gathersBySport);
                gathers.addAll(gathersBySport);
            }
        }
        if(!gatherListRequest.getDate().isEmpty()){
            LocalDate date=LocalDate.parse(gatherListRequest.getDate());
            for(Gather gather : gathers) {
                LocalDateTime time=LocalDateTime.parse(gather.getTime());
                if(!date.isEqual(time.toLocalDate())) gathers.remove(gather);
            }
        }
        return gathers.stream().map(gather->GatherResponse.gatherResponse(gather,(long)participateRepository.findAllByGatherId(gather.getId()).size())).toList();
    }


}
