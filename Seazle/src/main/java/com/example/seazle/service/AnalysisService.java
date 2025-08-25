package com.example.seazle.service;

import com.example.seazle.domain.Location;
import com.example.seazle.repository.LocationRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AnalysisService {

    public static String[] keyWords={"강사님이 친절하고 세세하게 알려주세요","유머러스해서 배우는 내내 즐거웠어요","칭찬과 피드백이 많아서 자신감이 생겼어요","수업이 체계적이고 이해하기 쉬워요","실습 위주라 금방 서핑에 익숙해져요","안전에 신경을 많이 써주셔서 안심돼요","자유시간도 있어서 여유롭게 즐길 수 있어요","기초부터 자세 교정까지 꼼꼼하게 해줘요","짧은 시간에 실력이 늘어요","샤워실이 깨끗하고 온수도 잘 나와요","세면도구가 준비되어 있어 편리해요","주차하기 편해서 좋습니다","휴식공간이 잘 마련돼 있어요","숙박까지 가능해서 멀리서 와도 편해요","시설이 전체적으로 청결해요","서핑 샵과 서핑 위치가 가까워요","커피와 음료가 맛있어요","음식이 맛있어요","가족과 함께 즐기기 좋아요","아이들도 즐겁게 체험할 수 있어요","친구랑 와서 재밌는 추억 만들었어요","혼자 와도 분위기가 좋아서 어색하지 않아요","분위기가 좋아서 꼭 다시 오고 싶어요","주변에도 추천하고 싶은 곳이에요"};

    private final static RestTemplate restTemplate = new RestTemplate();

    private final LocationRepository locationRepository;

    private final ChatClient chatClient;
    public AnalysisService(LocationRepository locationRepository, ChatClient.Builder chatClientBuilder) {
        this.locationRepository = locationRepository;
        this.chatClient = chatClientBuilder.build();
    }

    public static List<String> reviewAnalysis(String review){
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    public void updateLocationAiReview(Long locationId) {
        Location location=locationRepository.findById(locationId).orElse(null);
        if(location==null) return;
        String result = summarizeAnalysis(location);
        location.updateAiReview(result);
    }

    public String summarizeAnalysis(Location location){
        StringBuilder analysis = new StringBuilder("{");
        List<Long> list = location.getAnalysis();
        List<Long> newList = new ArrayList<>(list);
        for(int i=0;i<10;i++){
            Long max=Collections.max(newList);
            int index=newList.indexOf(max);
            analysis.append(keyWords[index]);
            newList.set(index,-1L);
        }
        analysis.append("}");
        return aiGenerate("다음 문장들을 보고 강습 내용을 제외한 중요한 내용을 요약해서 소개해줘: "+analysis);
    }

    public String aiGenerate(String input) {
        return this.chatClient.prompt()
                .system("너는 친절한 안내원이야. 10단어 이내의 한 문장으로 답변해줘.")
                .user(input)
                .call()
                .content();
    }

}

