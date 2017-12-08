package com.ck.vo;

import com.ck.entity.Comments;
import com.ck.entity.Contents;
import com.ck.entity.Logs;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashBoardVo {
    private List<Comments> commentsList;
    private List<Contents> contentsList;
    private List<Logs> logsList;
    private StatisticsAnalysisVo statisticsAnalysisVo;
}
