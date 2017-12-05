package com.ck.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OptionsDto {
    private String key;
    private String option;
}
