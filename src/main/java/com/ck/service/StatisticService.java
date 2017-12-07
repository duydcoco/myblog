package com.ck.service;

import com.ck.entity.User;
import com.ck.vo.StatisticsAnalysisVo;

public interface StatisticService {

    StatisticsAnalysisVo getCount(Long userId);
}
