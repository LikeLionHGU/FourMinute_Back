package com.example.seazle.controller;

import com.example.seazle.domain.Gather;
import com.example.seazle.domain.Location;
import com.example.seazle.dto.request.GatherRequest;
import com.example.seazle.dto.request.LocationRequest;
import com.example.seazle.repository.GatherRepository;
import com.example.seazle.repository.LocationRepository;
import com.example.seazle.service.AdminService;
import com.example.seazle.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add/location")
    public void addLocation(@RequestBody LocationRequest locationRequest) {
        adminService.addLocation(locationRequest);
    }

    @PostMapping("/add/gather")
    public void addGather(@RequestBody GatherRequest GatherRequest) {
        adminService.addGather(GatherRequest);
    }


}
