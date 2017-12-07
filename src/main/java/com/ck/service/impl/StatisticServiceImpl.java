package com.ck.service.impl;

import com.ck.entity.Contents;
import com.ck.repository.AttachRepository;
import com.ck.repository.CommentRepository;
import com.ck.repository.ContentsRepository;
import com.ck.service.StatisticService;
import com.ck.vo.StatisticsAnalysisVo;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ck.utils.ValidateUtils.NOT_NULL;
import static com.ck.utils.ValidateUtils.validate;


public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private ContentsRepository contentsRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AttachRepository attachRepository;

    @Override
    public StatisticsAnalysisVo getCount(Long userId) {
        validate(userId,NOT_NULL,"用户不存在");

        return null;
    }
}
