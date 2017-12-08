package com.ck.service;

import com.ck.entity.User;
import com.ck.vo.DashBoardVo;
import com.ck.vo.StatisticsAnalysisVo;

public interface StatisticService {

    DashBoardVo getDashBoard(Long userId);
}
