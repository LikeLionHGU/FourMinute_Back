package service;

import domain.Gather;
import domain.Location;
import domain.Review;
import dto.response.LocationAnalysisResponse;
import dto.response.LocationDetailResponse;
import dto.response.LocationGatherResponse;
import dto.response.LocationReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.LocationRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final String[] keyWords={"교통","주차","친절","사진","휴식","안전","수업","시설","가격","장비","경치","파도","재미"};

    private final LocationRepository locationRepository;

    public LocationDetailResponse getLocationDetail(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        return LocationDetailResponse.locationDetailResponse(location);
    }

    public List<LocationReviewResponse> getLocationReviews(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        List<Review> reviews = location.getReviews();
        return reviews.stream().map(LocationReviewResponse::locationReviewResponse).toList();
    }

    public List<LocationGatherResponse> getLocationGathers(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        List<Gather> gathers = location.getGathers();
        return gathers.stream().map(LocationGatherResponse::locationGatherResponse).toList();

    }

    public List<LocationAnalysisResponse> getLocationAnalysis(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        List<LocationAnalysisResponse> locationAnalysis = new ArrayList<>();
        List<Long> analysis = location.getAnalysis();
        for(int i=0;i<5;i++){
            Long max= Collections.max(analysis);
            int index=analysis.indexOf(max);
            locationAnalysis.add(LocationAnalysisResponse.locationAnalysisResponse(keyWords[index],max));
            analysis.remove(index);
        }
        return locationAnalysis;
    }

}
