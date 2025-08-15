package controller;

import dto.request.CompareDetailRequest;
import dto.response.CompareDetailResponse;
import dto.response.CompareThumbResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.CompareService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compare")
public class CompareController {

    private final CompareService compareService;

    @GetMapping("/detail")
    public List<CompareDetailResponse> getCompareDetails(@RequestBody CompareDetailRequest compareDetailRequest) {
        return compareService.getCompareDetails(compareDetailRequest);
    }

    @GetMapping("/thumb/{locationId}")
    public CompareThumbResponse getCompareThumb(@PathVariable Long locationId) {
        return compareService.getCompareThumb(locationId);
    }


}
