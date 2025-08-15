package controller;

import dto.response.GatherCommentResponse;
import dto.response.GatherDetailResponse;
import dto.response.GatherMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DetailService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gather")
public class GatherController {

    private final DetailService detailService;

    @GetMapping("/detail/{gatherId}")
    public GatherDetailResponse getGatherDetail(@PathVariable Long gatherId) {
        return detailService.getGatherDetail(gatherId);
    }

    @GetMapping("/comments/{gatherId}")
    public List<GatherCommentResponse> getGatherComments(@PathVariable Long gatherId) {
        return detailService.getGatherComments(gatherId);
    }

    @GetMapping("/members/{gatherId}")
    public List<GatherMemberResponse> getGatherMembers(@PathVariable Long gatherId) {
        return detailService.getGatherMembers(gatherId);
    }
}
