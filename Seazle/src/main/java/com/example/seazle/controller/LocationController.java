package com.example.seazle.controller;

import com.example.seazle.dto.response.*;
import com.example.seazle.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/detail/{locationId}")
    public ResponseEntity<Object> getLocationDetail(@PathVariable Long locationId) {
        LocationDetailResponse locationDetail = locationService.getLocationDetail(locationId);
        if (locationDetail==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(locationDetail);
    }

    @GetMapping("/reviews/{locationId}")
    public ResponseEntity<Object> getLocationReviews(@PathVariable Long locationId) {
        List<LocationReviewResponse> locationReviews = locationService.getLocationReviews(locationId);
        if (locationReviews==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(locationReviews);
    }

    @GetMapping("/gathers/{locationId}")
    public ResponseEntity<Object> getLocationGathers(@PathVariable Long locationId) {
        List<LocationGatherResponse> locationGathers = locationService.getLocationGathers(locationId);
        if (locationGathers==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(locationGathers);
    }

    @GetMapping("/analysis/{locationId}")
    public ResponseEntity<Object> getLocationAnalysis(@PathVariable Long locationId) {
        List<LocationAnalysisResponse> locationAnalysis = locationService.getLocationAnalysis(locationId);
        if(locationAnalysis==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(locationAnalysis);
    }

}
