package com.ck.service;

import com.ck.dto.OptionsDto;

import java.util.List;

public interface OptionService{

    void saveOptions(List<OptionsDto> optionsDtoList);

    void saveOption(String key,String value);
}
