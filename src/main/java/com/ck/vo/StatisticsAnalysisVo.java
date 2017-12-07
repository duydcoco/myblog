package com.ck.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 仪表盘统计vo
 */
@Data
@Builder
public class StatisticsAnalysisVo implements Serializable{

    private int totalPublishArticleCount;
    private int totalCommentCount;
    private int totalAttachCount;
}
