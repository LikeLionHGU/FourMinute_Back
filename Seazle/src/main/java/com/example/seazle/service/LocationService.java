package com.example.seazle.service;

import com.example.seazle.domain.Gather;
import com.example.seazle.domain.Location;
import com.example.seazle.domain.Review;
import com.example.seazle.dto.response.LocationAnalysisResponse;
import com.example.seazle.dto.response.LocationDetailResponse;
import com.example.seazle.dto.response.LocationGatherResponse;
import com.example.seazle.dto.response.LocationReviewResponse;
import com.example.seazle.repository.LocationRepository;
import com.example.seazle.repository.ParticipateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final ParticipateRepository participateRepository;

    public LocationDetailResponse getLocationDetail(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        return LocationDetailResponse.locationDetailResponse(location);
    }

    public List<LocationReviewResponse> getLocationReviews(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        List<Review> reviews = location.getReviews();
        Collections.sort(reviews);
        return reviews.stream().map(LocationReviewResponse::locationReviewResponse).toList();
    }

    public List<LocationGatherResponse> getLocationGathers(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        List<Gather> gathers = location.getGathers();
        return gathers.stream().map(gather-> LocationGatherResponse.locationGatherResponse(gather,(long)participateRepository.findAllByGatherId(gather.getId()).size())).toList();
    }

    public List<LocationAnalysisResponse> getLocationAnalysis(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        List<LocationAnalysisResponse> locationAnalysis = new ArrayList<>();
        List<Long> analysis = location.getAnalysis();
        for(int i=0;i<5;i++){
            Long max=Collections.max(analysis);
            int index=analysis.indexOf(max);
            locationAnalysis.add(LocationAnalysisResponse.locationAnalysisResponse(AnalysisService.keyWords[index],max));
            analysis.set(index,-1L);
        }
        return locationAnalysis;
    }

}
