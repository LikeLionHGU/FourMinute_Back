package service;

import domain.*;
import dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.GatherRepository;
import repository.JoinRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GatherService {

    private final GatherRepository gatherRepository;
    private final JoinRepository joinRepository;
    private final UserRepository userRepository;

    public GatherDetailResponse getGatherDetail(Long gatherId) {
        Gather gather=gatherRepository.findById(gatherId).orElse(null);
        if(gather==null) return null;
        return GatherDetailResponse.gatherDetailResponse(gather);
    }

    public List<GatherCommentResponse> getGatherComments(Long gatherId) {
        Gather gather =gatherRepository.findById(gatherId).orElse(null);
        if(gather==null) return null;
        List<Comment> comments=gather.getComments();
        return comments.stream().map(GatherCommentResponse::gatherCommentResponse).toList();
    }

    public List<GatherMemberResponse> getGatherMembers(Long gatherId) {
        List<Join> joins=joinRepository.findAllByGatherId(gatherId).orElse(null);
        if(joins==null) return null;
        List<User> users=new ArrayList<>();
        for(Join join:joins){
            User user=userRepository.findById(join.getUserId()).orElse(null);
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
