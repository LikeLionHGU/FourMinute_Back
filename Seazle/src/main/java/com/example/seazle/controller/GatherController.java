package com.example.seazle.controller;

import com.example.seazle.dto.response.*;
import com.example.seazle.service.GatherService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gather")
public class GatherController {

    private final GatherService gatherService;

    @GetMapping("/detail/{gatherId}")
    public ResponseEntity<Object> getGatherDetail(@PathVariable Long gatherId, HttpServletRequest request) {
        GatherDetailResponse gatherDetail=gatherService.getGatherDetail(gatherId,request);
        if (gatherDetail==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(gatherDetail);
    }

    @GetMapping("/comments/{gatherId}")
    public ResponseEntity<Object> getGatherComments(@PathVariable Long gatherId, HttpServletRequest request) {
        List<GatherCommentResponse> gatherComments=gatherService.getGatherComments(gatherId,request);
        if (gatherComments==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(gatherComments);
    }

    @GetMapping("/members/{gatherId}")
    public ResponseEntity<Object> getGatherMembers(@PathVariable Long gatherId) {
        List<GatherMemberResponse> gatherMembers=gatherService.getGatherMembers(gatherId);
        if (gatherMembers==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(gatherMembers);
    }

    @GetMapping("/location/{gatherId}")
    public ResponseEntity<Object> getGatherLocation(@PathVariable Long gatherId) {
        GatherLocationResponse gatherLocation=gatherService.getGatherLocation(gatherId);
        if (gatherLocation==null) return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
        return ResponseEntity.ok().body(gatherLocation);
    }

}
