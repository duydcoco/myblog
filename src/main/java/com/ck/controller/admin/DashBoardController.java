package com.ck.controller.admin;

import com.ck.service.StatisticService;
import com.ck.utils.Result;
import com.ck.vo.DashBoardVo;
import com.ck.vo.StatisticsAnalysisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dashboard")
@RestController
public class DashBoardController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result<DashBoardVo> getDashBoard(Long userId){
        DashBoardVo dashBoardVo =  statisticService.getDashBoard(userId);
        return Result.ok(dashBoardVo);
    }

}
