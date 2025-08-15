package controller;

import dto.request.GatherListRequest;
import dto.response.GatherResponse;
import dto.request.LocationListRequest;
import dto.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.HomeService;
import service.DetailService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final DetailService detailService;
    private final HomeService homeService;

    @GetMapping("/location/list")
    public List<LocationResponse> getLocationList(@RequestBody LocationListRequest locationListRequest) {
        return homeService.getLocationList(locationListRequest);
    }

    @GetMapping("/location/search")
    public List<LocationResponse> getLocationSearch(@RequestParam String input) {
        return homeService.getLocationSearch(input);
    }

    @GetMapping("/gather/list")
    public List<GatherResponse> getGatherList(@RequestBody GatherListRequest gatherListRequest) {
        return homeService.getGatherList(gatherListRequest);
    }


}
