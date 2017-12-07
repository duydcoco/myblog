package com.ck.controller;

import com.ck.utils.Result;
import com.ck.vo.StatisticsAnalysisVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dashboard")
@RestController
public class DashBoardController {

    public Result<StatisticsAnalysisVo> getCount(){

    }
}
