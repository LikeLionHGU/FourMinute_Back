package com.example.seazle.controller;

import com.example.seazle.dto.request.GatherListRequest;
import com.example.seazle.dto.response.GatherResponse;
import com.example.seazle.dto.request.LocationListRequest;
import com.example.seazle.dto.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.seazle.service.HomeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/location/list")
    public ResponseEntity<Object> getLocationList(@RequestBody LocationListRequest locationListRequest) {
        List<LocationResponse> locationList=homeService.getLocationList(locationListRequest);
        return ResponseEntity.ok().body(locationList);
    }

    @GetMapping("/location/search")
    public ResponseEntity<Object> getLocationSearch(@RequestParam String input) {
        List<LocationResponse> locationList=homeService.getLocationSearch(input);
        return ResponseEntity.ok().body(locationList);
    }

    @GetMapping("/gather/list")
    public ResponseEntity<Object> getGatherList(@RequestBody GatherListRequest gatherListRequest) {
        List<GatherResponse> gatherList=homeService.getGatherList(gatherListRequest);
        return ResponseEntity.ok().body(gatherList);
    }


}
