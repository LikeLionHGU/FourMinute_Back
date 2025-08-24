package com.example.seazle.service;


import com.example.seazle.domain.*;
import com.example.seazle.dto.request.CommentRequest;
import com.example.seazle.dto.request.RegisterRequest;
import com.example.seazle.dto.request.ReviewRequest;
import com.example.seazle.dto.response.UserNameResponse;
import com.example.seazle.repository.*;
import com.example.seazle.security.MyUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserService {

    private final ReviewRepository reviewRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final GatherRepository gatherRepository;
    private final CommentRepository commentRepository;
    private final ParticipateRepository participateRepository;
    private final PasswordEncoder passwordEncoder;
    private final AnalysisService analysisService;

    private final String[] images={"https://testalltest.s3.ap-northeast-2.amazonaws.com/likelion/KakaoTalk_Photo_2025-08-24-01-43-06.png","https://testalltest.s3.ap-northeast-2.amazonaws.com/likelion/%E1%84%91%E1%85%A6%E1%86%BC%E1%84%80%E1%85%B1%E1%86%AB.png","https://testalltest.s3.ap-northeast-2.amazonaws.com/likelion/%E1%84%80%E1%85%A5%E1%84%87%E1%85%AE%E1%86%A8%E1%84%8B%E1%85%B5.png"};

    public String joinGather(Long gatherId, MyUserDetails myUserDetails) {
        try{
            Gather gather = gatherRepository.findById(gatherId).orElse(null);
            if (gather==null) return null;
            User user = userRepository.findByName(myUserDetails.getUsername()).orElse(null);
            if(user == null) return null;
            Participate participate = participateRepository.findByGatherIdAndUserId(gatherId,user.getId()).orElse(null);
            if(participate != null) return "duplicated";
            if(Objects.equals(participateRepository.countByGatherId(gatherId), gather.getCapacity())){
                return "full";
            }
            participateRepository.save(new Participate(gatherId, user.getId()));
            return "saved";
        }
        catch (Exception e){
            return null;
        }
    }

    public Boolean writeComment(CommentRequest commentRequest, MyUserDetails myUserDetails) {
        try{
            Gather gather = gatherRepository.findById(commentRequest.getId()).orElse(null);
            if (gather == null) return false;
            User user = userRepository.findByName(myUserDetails.getUsername()).orElse(null);
            if(user == null) return false;
            commentRepository.save(Comment.comment(commentRequest.getContent(),gather,user,commentRequest.getSecrete()));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Transactional
    public Boolean writeReview(ReviewRequest reviewRequest, MyUserDetails myUserDetails) {
        try{
            Location location = locationRepository.findById(reviewRequest.getId()).orElse(null);
            if(location == null) {
                return false;
            }
            User user = userRepository.findByName(myUserDetails.getUsername()).orElse(null);
            if(user == null) {
                return false;
            }
            Review review = Review.review(reviewRequest.getContent(),reviewRequest.getScore(),location,user);
            reviewRepository.save(review);
            location.updateScore(review.getScore());
            location.updateAnalysis(review.getContent());
            analysisService.updateLocationAiReview(reviewRequest.getId());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Object register(RegisterRequest registerRequest) {
        try{
            if(userRepository.existsByUsername(registerRequest.getUsername())){
                return "duplicate";
            }
            int random = (int)(Math.random()*images.length);
            User user = new User(
                    registerRequest.getUsername(),
                    passwordEncoder.encode(registerRequest.getPassword()),
                    "USER",
                    registerRequest.getName(),
                    images[random],
                    registerRequest.getStatement()
            );
            userRepository.save(user);
            return new UserNameResponse(user.getName());
        }
        catch(Exception e){
            return null;
        }
    }




}
