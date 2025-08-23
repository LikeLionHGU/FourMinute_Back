package com.example.seazle.controller;


import com.example.seazle.dto.request.CommentRequest;
import com.example.seazle.dto.request.ReviewRequest;
import com.example.seazle.dto.response.MessageResponse;
import com.example.seazle.security.MyUserDetails;
import com.example.seazle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/review")
    public ResponseEntity<Object> writeReview(@RequestBody ReviewRequest reviewRequest, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Boolean state = userService.writeReview(reviewRequest, myUserDetails);
        if(state){
            return ResponseEntity.ok().body(new MessageResponse("saved"));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
    }

    @PostMapping("/comment")
    public ResponseEntity<Object> writeComment(@RequestBody CommentRequest commentRequest, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        Boolean state =  userService.writeComment(commentRequest, myUserDetails);
        if(state){
            return ResponseEntity.ok().body(new MessageResponse("saved"));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
    }

    @PostMapping("/join/{gatherId}")
    public ResponseEntity<Object> joinGather(@PathVariable Long gatherId, @AuthenticationPrincipal MyUserDetails myUserDetails) {
        String state =  userService.joinGather(gatherId, myUserDetails);
        if(state.equals("duplicated")){
            return ResponseEntity.ok().body(new MessageResponse("duplicated"));
        }
        else if(state.equals("full")){
            return ResponseEntity.ok().body(new MessageResponse("full"));
        }
        else if(state.equals("saved")){
            return ResponseEntity.ok().body(new MessageResponse("saved"));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("T.T"));
    }


}
