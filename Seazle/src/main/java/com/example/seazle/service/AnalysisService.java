package com.example.seazle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    public static String[] keyWords={"교통","주차","친절","사진","휴식","안전","수업","시설","가격","장비","경치","파도","재미"};

    private final static RestTemplate restTemplate = new RestTemplate();

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

}
