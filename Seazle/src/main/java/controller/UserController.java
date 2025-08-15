package controller;

import dto.request.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import security.MyUserDetails;
import service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/write/review")
    public Object writeReview(@RequestBody ReviewRequest reviewRequest, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        return userService.writeReview(reviewRequest, myUserDetails);
    }

    @PutMapping("/join/{gatherId}")
    public Object joinGather(@PathVariable Long gatherId, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        return userService.joinGather(gatherId, myUserDetails);
    }


}
