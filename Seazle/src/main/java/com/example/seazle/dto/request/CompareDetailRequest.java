package com.example.seazle.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompareDetailRequest {

    private Long item1;
    private Long item2;
    private Long item3;

}
