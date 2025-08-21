package com.example.seazle.service;

import com.example.seazle.domain.Gather;
import com.example.seazle.domain.Location;
import com.example.seazle.domain.Review;
import com.example.seazle.dto.response.*;
import com.example.seazle.repository.ParticipateRepository;
import com.example.seazle.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.seazle.repository.LocationRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final AnalysisService analysisService;
    private final ParticipateRepository participateRepository;

    @Transactional
    public LocationDetailResponse getLocationDetail(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime modified = location.getModified();
        Duration duration = Duration.between(modified, now);
        if(duration.toDays()>1) location.updateAiReview(analysisService.summarizeAnalysis(location));
        return LocationDetailResponse.locationDetailResponse(location);
    }

    public List<LocationReviewResponse> getLocationReviews(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        List<Review> reviews = location.getReviews();
        Collections.reverse(reviews);
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
            Long max= Collections.max(analysis);
            int index=analysis.indexOf(max);
            locationAnalysis.add(LocationAnalysisResponse.locationAnalysisResponse(AnalysisService.keyWords[index],max));
            analysis.remove(index);
        }
        return locationAnalysis;
    }

}
