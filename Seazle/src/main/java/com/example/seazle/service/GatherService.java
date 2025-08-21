package com.example.seazle.service;

import com.example.seazle.domain.*;
import com.example.seazle.dto.response.GatherCommentResponse;
import com.example.seazle.dto.response.GatherDetailResponse;
import com.example.seazle.dto.response.GatherLocationResponse;
import com.example.seazle.dto.response.GatherMemberResponse;
import com.example.seazle.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.seazle.repository.GatherRepository;
import com.example.seazle.repository.ParticipateRepository;
import com.example.seazle.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GatherService {

    private final GatherRepository gatherRepository;
    private final ParticipateRepository participateRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public GatherDetailResponse getGatherDetail(Long gatherId) {
        Gather gather=gatherRepository.findById(gatherId).orElse(null);
        if(gather==null) return null;
        List<Participate> participates=participateRepository.findAllByGatherId(gatherId);
        return GatherDetailResponse.gatherDetailResponse(gather,(long)participates.size());
    }

    public List<GatherCommentResponse> getGatherComments(Long gatherId, HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        if(token!=null){
            String name=jwtUtil.getUsername(token);
        }

        Gather gather =gatherRepository.findById(gatherId).orElse(null);
        if(gather==null) return null;
        List<Comment> comments=gather.getComments();
        return comments.stream().map(GatherCommentResponse::gatherCommentResponse).toList();
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
