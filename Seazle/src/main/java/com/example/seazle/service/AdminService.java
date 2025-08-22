package com.example.seazle.service;

import com.example.seazle.domain.Gather;
import com.example.seazle.domain.Location;
import com.example.seazle.domain.User;
import com.example.seazle.dto.request.GatherRequest;
import com.example.seazle.dto.request.LocationRequest;
import com.example.seazle.repository.GatherRepository;
import com.example.seazle.repository.LocationRepository;
import com.example.seazle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final GatherRepository gatherRepository;

    public void addLocation(LocationRequest locationRequest) {
        locationRepository.save(Location.location(locationRequest));
    }

    public void addGather(GatherRequest gatherRequest) {
        User user=userRepository.findById(gatherRequest.getUserId()).orElse(null);
        Location location=locationRepository.findById(gatherRequest.getLocationId()).orElse(null);
        gatherRepository.save(Gather.gather(gatherRequest,user,location));
    }


}
