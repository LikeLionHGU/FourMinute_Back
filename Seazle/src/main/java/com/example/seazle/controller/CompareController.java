package com.example.seazle.controller;


import com.example.seazle.dto.request.CompareDetailRequest;
import com.example.seazle.dto.response.CompareDetailResponse;
import com.example.seazle.dto.response.CompareThumbResponse;
import com.example.seazle.dto.response.MessageResponse;
import com.example.seazle.service.CompareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compare")
public class CompareController {

    private final CompareService compareService;

    @GetMapping("/detail")
    public ResponseEntity<Object> getCompareDetails(@RequestBody CompareDetailRequest compareDetailRequest) {
        List<CompareDetailResponse> compareDetailResponses=compareService.getCompareDetails(compareDetailRequest);
        if(compareDetailResponses==null)  return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok(compareDetailResponses);
    }

    @GetMapping("/thumb/{locationId}")
    public ResponseEntity<Object> getCompareThumb(@PathVariable Long locationId) {
        CompareThumbResponse compareThumbResponse=compareService.getCompareThumb(locationId);
        if(compareThumbResponse==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(compareThumbResponse);
    }


}
