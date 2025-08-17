package com.example.seazle.controller;

import com.example.seazle.domain.Location;
import com.example.seazle.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final LocationRepository locationRepository;

    @PostMapping("/add")
    public Location addLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }


}
