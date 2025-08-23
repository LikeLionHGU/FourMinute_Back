package com.example.seazle.service;

import com.example.seazle.domain.*;
import com.example.seazle.dto.response.GatherCommentResponse;
import com.example.seazle.dto.response.GatherDetailResponse;
import com.example.seazle.dto.response.GatherLocationResponse;
import com.example.seazle.dto.response.GatherMemberResponse;
import com.example.seazle.repository.GatherRepository;
import com.example.seazle.repository.ParticipateRepository;
import com.example.seazle.repository.UserRepository;
import com.example.seazle.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GatherService {

    private final GatherRepository gatherRepository;
    private final ParticipateRepository participateRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public GatherDetailResponse getGatherDetail(Long gatherId, HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        Gather gather=gatherRepository.findById(gatherId).orElse(null);
        if(gather==null) return null;
        String state="";
        if(token==null) state="ok";
        else{
            try{
                token = token.substring(7);
                String name=jwtUtil.getUsername(token);
                User user=userRepository.findByName(name).orElse(null);
                Long userId=user.getId();
                if(participateRepository.findByGatherIdAndUserId(gatherId, userId).isPresent()){
                    state="joined";
                }
                if(Objects.equals(participateRepository.countByGatherId(gatherId), gather.getCapacity())){
                    state="full";
                }
            }
            catch(Exception e){
                state="ok";
            }
        }
        List<Participate> participates=participateRepository.findAllByGatherId(gatherId);
        return GatherDetailResponse.gatherDetailResponse(gather,(long)participates.size(),state);
    }

    public List<GatherCommentResponse> getGatherComments(Long gatherId, HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        Gather gather = gatherRepository.findById(gatherId).orElse(null);
        if (gather == null) return null;
        List<Comment> comments = gather.getComments();
        if(token==null) {
            return comments.stream().map(comment->comment.getSecrete()==0?GatherCommentResponse.gatherCommentResponse(comment):GatherCommentResponse.gatherCommentResponseSecrete(comment)).toList();
        }
        try{
            token = token.substring(7);
            String name=jwtUtil.getUsername(token);
            return comments.stream().map(comment->comment.getSecrete()==1&&!comment.getUser().getName().equals(name)?GatherCommentResponse.gatherCommentResponseSecrete(comment):GatherCommentResponse.gatherCommentResponse(comment)).toList();
        }
        catch(Exception e){
            return comments.stream().map(comment->comment.getSecrete()==0?GatherCommentResponse.gatherCommentResponse(comment):GatherCommentResponse.gatherCommentResponseSecrete(comment)).toList();
        }
    }

    public List<GatherMemberResponse> getGatherMembers(Long gatherId) {
        List<Participate> participates = participateRepository.findAllByGatherId(gatherId);
        List<User> users=new ArrayList<>();
        for(Participate participate : participates){
            User user=userRepository.findById(participate.getUserId()).orElse(null);
            if(user!=null) users.add(user);
        }
        return users.stream().map(GatherMemberResponse::gatherMemberResponse).toList();
    }

    public GatherLocationResponse getGatherLocation(Long gatherId) {
        Gather gather=gatherRepository.findById(gatherId).orElse(null);
        if(gather==null) return null;
        Location location=gather.getLocation();
        return GatherLocationResponse.gatherLocationResponse(location);
    }


}
