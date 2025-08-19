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

    public Boolean joinGather(Long gatherId, MyUserDetails myUserDetails) {
        try{
            if (!gatherRepository.existsById(gatherId)) return false;
            User user = userRepository.findByUsername(myUserDetails.getUser().getUsername()).orElse(null);
            if(user == null) return false;
            participateRepository.save(new Participate(gatherId, user.getId()));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Boolean writeComment(CommentRequest commentRequest, MyUserDetails myUserDetails) {
        try{
            Gather gather = gatherRepository.findById(commentRequest.getId()).orElse(null);
            if (gather == null) return false;
            User user = userRepository.findByUsername(myUserDetails.getUser().getUsername()).orElse(null);
            if(user == null) return false;
            commentRepository.save(Comment.comment(commentRequest.getContent(),gather,user));
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
            if(location == null) return false;
            User user = userRepository.findByName(myUserDetails.getUsername()).orElse(null);
            if(user == null) return false;
            Review review = Review.review(reviewRequest.getContent(),reviewRequest.getScore(),location,user);
            reviewRepository.save(review);
            location.updateScore(review.getScore());
            location.updateAnalysis(review.getContent());
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
            User user = new User(
                    registerRequest.getUsername(),
                    passwordEncoder.encode(registerRequest.getPassword()),
                    "USER",
                    registerRequest.getName(),
                    "",
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
