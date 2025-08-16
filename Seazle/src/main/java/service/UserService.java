package service;

import domain.Location;
import domain.Review;
import domain.User;
import dto.request.CommentRequest;
import dto.request.ReviewRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repository.LocationRepository;
import repository.ReviewRepository;
import security.MyUserDetails;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String[] keyWords={"교통","주차","친절","사진","휴식","안전","수업","시설","가격","장비","경치","파도","재미"};

    private final ReviewRepository reviewRepository;
    private final LocationRepository locationRepository;

    public Object joinGather(Long gatherId, MyUserDetails myUserDetails) {

    }

    public Object writeComment(CommentRequest commentRequest, MyUserDetails myUserDetails) {


    }

    @Transactional
    public String writeReview(ReviewRequest reviewRequest, MyUserDetails myUserDetails) {
        try{
            Location location = locationRepository.findById(reviewRequest.getId()).orElse(null);
            if(location == null) return null;
            User user = myUserDetails.getUser();
            Review review = Review.review(reviewRequest.getContent(),reviewRequest.getScore(),location,user);
            reviewRepository.save(review);
            List<String> topWords = reviewAnalysis(review.getContent());
            for(String word : topWords){
                for(int i=0;i<keyWords.length;i++){
                    if(word.equals(keyWords[i])){
                        location.getAnalysis().set(i,location.getAnalysis().get(i)+1);
                    }
                }
            }
        }
        catch(Exception e){
            return null;
        }
        return "saved";
    }


    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> reviewAnalysis(String review){
        Map<String, String> body = new HashMap<>();
        body.put("input", review);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String[]> response = restTemplate.postForEntity(
                "http://localhost:5000/generate",
                requestEntity,
                String[].class
        );
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }


}
