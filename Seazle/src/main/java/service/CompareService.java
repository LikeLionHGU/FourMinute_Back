package service;

import domain.Location;
import dto.request.CompareDetailRequest;
import dto.response.CompareDetailResponse;
import dto.response.CompareThumbResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.GatherRepository;
import repository.LocationRepository;

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
            else compareDetails.add(null);
        }
        if(compareDetailRequest.getItem2()>=0){
            Location location = locationRepository.findById(compareDetailRequest.getItem2()).orElse(null);
            if(location!=null){
                compareDetails.add(CompareDetailResponse.compareDetailResponse(location));
            }
            else compareDetails.add(null);
        }
        if(compareDetailRequest.getItem3()>=0){
            Location location = locationRepository.findById(compareDetailRequest.getItem3()).orElse(null);
            if(location!=null){
                compareDetails.add(CompareDetailResponse.compareDetailResponse(location));
            }
            else compareDetails.add(null);
        }
        return compareDetails;
    }

    public CompareThumbResponse getCompareThumb(Long locationId) {

    }


}
