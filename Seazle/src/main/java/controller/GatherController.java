package controller;

import dto.response.GatherCommentResponse;
import dto.response.GatherDetailResponse;
import dto.response.GatherLocationResponse;
import dto.response.GatherMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.GatherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gather")
public class GatherController {

    private final GatherService gatherService;

    @GetMapping("/detail/{gatherId}")
    public ResponseEntity<Object> getGatherDetail(@PathVariable Long gatherId) {
        GatherDetailResponse gatherDetail=gatherService.getGatherDetail(gatherId);
        if (gatherDetail==null) return ResponseEntity.badRequest().body("T.T");
        return ResponseEntity.ok().body(gatherDetail);
    }

    @GetMapping("/comments/{gatherId}")
    public ResponseEntity<Object> getGatherComments(@PathVariable Long gatherId) {
        List<GatherCommentResponse> gatherComments=gatherService.getGatherComments(gatherId);
        if (gatherComments==null) return ResponseEntity.badRequest().body("T.T");
        return ResponseEntity.ok().body(gatherComments);
    }

    @GetMapping("/members/{gatherId}")
    public ResponseEntity<Object> getGatherMembers(@PathVariable Long gatherId) {
        List<GatherMemberResponse> gatherMembers=gatherService.getGatherMembers(gatherId);
        if (gatherMembers==null) return ResponseEntity.badRequest().body("T.T");
        return ResponseEntity.ok().body(gatherMembers);
    }

    @GetMapping("/location/{gatherId}")
    public ResponseEntity<Object> getGatherLocation(@PathVariable Long gatherId) {
        GatherLocationResponse gatherLocation=gatherService.getGatherLocation(gatherId);
        if (gatherLocation==null) return ResponseEntity.badRequest().body("T.T");
        return ResponseEntity.ok().body(gatherLocation);
    }

}
