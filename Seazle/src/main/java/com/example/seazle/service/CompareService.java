package com.example.seazle.service;


import com.example.seazle.domain.Location;
import com.example.seazle.dto.request.CompareDetailRequest;
import com.example.seazle.dto.response.CompareDetailResponse;
import com.example.seazle.dto.response.CompareThumbResponse;
import com.example.seazle.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompareService {

    private final LocationRepository locationRepository;

    public List<CompareDetailResponse> getCompareDetails(CompareDetailRequest compareDetailRequest) {
        List<CompareDetailResponse> compareDetails = new ArrayList<>();
        if(compareDetailRequest.getItem1()>=0){
            Location location = locationRepository.findById(compareDetailRequest.getItem1()).orElse(null);
            if(location!=null){
                compareDetails.add(CompareDetailResponse.compareDetailResponse(location));
            }
            else return null;
        }
        if(compareDetailRequest.getItem2()>=0){
            Location location = locationRepository.findById(compareDetailRequest.getItem2()).orElse(null);
            if(location!=null){
                compareDetails.add(CompareDetailResponse.compareDetailResponse(location));
            }
            else return null;
        }
        if(compareDetailRequest.getItem3()>=0){
            Location location = locationRepository.findById(compareDetailRequest.getItem3()).orElse(null);
            if(location!=null){
                compareDetails.add(CompareDetailResponse.compareDetailResponse(location));
            }
            else return null;
        }
        return compareDetails;
    }

    public CompareThumbResponse getCompareThumb(Long locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location==null) return null;
        return new CompareThumbResponse(location.getImageUrl());
    }


}
