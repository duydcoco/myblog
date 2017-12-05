package com.ck.service.impl;

import com.ck.dto.OptionsDto;
import com.ck.entity.Options;
import com.ck.repository.OptionRepository;
import com.ck.service.OptionService;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class OptionServiceImpl implements OptionService{

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public void saveOptions(List<OptionsDto> optionsDtoList) {
        if(!CollectionUtils.isEmpty(optionsDtoList)){
            optionsDtoList.forEach(item->saveOption(item.getKey(),item.getOption()));
        }
    }

    @Override
    public void saveOption(String key, String value) {
        if(!Strings.isNullOrEmpty(key)&&!Strings.isNullOrEmpty(value)){
            Options options = optionRepository.findByOptionName(key);
            if(Objects.isNull(options)){
                options = Options.builder().build();
                options.setOptionName(key);
            }
            options.setValue(value);
            optionRepository.save(options);
        }
    }
}
