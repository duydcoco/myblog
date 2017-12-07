package com.ck.service;

import com.ck.dto.OptionsDto;
import com.ck.entity.Options;

import java.util.List;
import java.util.Map;

public interface OptionService{

    void saveOptions(List<OptionsDto> optionsDtoList);

    void saveOption(String key,String value);

    List<Options> getAllOptions();

    List<Options> getOptionsByKey(String key);

    /**
     * 删除所有 带有key的
     * @param key
     */
    void delete(String key);
}
