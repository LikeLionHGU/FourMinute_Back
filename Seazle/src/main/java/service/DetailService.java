package service;

import domain.Comment;
import domain.Gather;
import domain.Location;
import domain.Review;
import dto.request.CompareDetailRequest;
import dto.request.LocationListRequest;
import dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.GatherRepository;
import repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final LocationRepository locationRepository;
    private final GatherRepository gatherRepository;


    public LocationDetailResponse getLocationDetail(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location!=null){
            return LocationDetailResponse.locationDetailResponse(location);
        }
        else return null;
    }

    public List<LocationReviewResponse> getLocationReviews(Long locationId) {
        List<Review> reviews = locationRepository.findById(locationId).get().getReviews();
        return reviews.stream().map(LocationReviewResponse::locationReviewResponse).toList();
    }

    public List<LocationGatherResponse> getLocationGathers(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location!=null){
            List<Gather> gathers = location.getGathers();
            return gathers.stream().map(LocationGatherResponse::locationGatherResponse).toList();
        }
        else return null;
    }

    public GatherDetailResponse getGatherDetail(Long gatherId) {
        Gather gather=gatherRepository.findById(gatherId).orElse(null);
        if(gather!=null) return GatherDetailResponse.gatherDetailResponse(gather);
        else return null;
    }

    public List<GatherCommentResponse> getGatherComments(Long gatherId) {
        List<Comment> comments=gatherRepository.findById(gatherId).get().getComments();
        return comments.stream().map(GatherCommentResponse::gatherCommentResponse).toList();
    }

    public List<GatherMemberResponse> getGatherMembers(Long gatherId) {

    }

    public List<LocationAnalysisResponse> getLocationAnalysis(Long locationId) {

    }
}
