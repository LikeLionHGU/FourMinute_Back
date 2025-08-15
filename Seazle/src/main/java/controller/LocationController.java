package controller;

import dto.response.LocationAnalysisResponse;
import dto.response.LocationDetailResponse;
import dto.response.LocationGatherResponse;
import dto.response.LocationReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DetailService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final DetailService detailService;


    @GetMapping("/detail/{locationId}")
    public LocationDetailResponse getLocationDetail(@PathVariable Long locationId) {
        return detailService.getLocationDetail(locationId);
    }

    @GetMapping("/reviews/{locationId}")
    public List<LocationReviewResponse> getLocationReviews(@PathVariable Long locationId) {
        return detailService.getLocationReviews(locationId);
    }

    @GetMapping("/gathers/{locationId}")
    public List<LocationGatherResponse> getLocationGathers(@PathVariable Long locationId) {
        return detailService.getLocationGathers(locationId);
    }

    @GetMapping("/analysis/{locationId}")
    public List<LocationAnalysisResponse> getLocationAnalysis(@PathVariable Long locationId) {
        return detailService.getLocationAnalysis(locationId);
    }
}
