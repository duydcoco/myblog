package com.ck.service.impl;

import com.ck.constant.Types;
import com.ck.entity.Comments;
import com.ck.entity.Contents;
import com.ck.entity.Logs;
import com.ck.entity.User;
import com.ck.repository.AttachRepository;
import com.ck.repository.CommentRepository;
import com.ck.repository.ContentsRepository;
import com.ck.repository.LogRepository;
import com.ck.service.StatisticService;
import com.ck.vo.DashBoardVo;
import com.ck.vo.StatisticsAnalysisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.ck.utils.ValidateUtils.NOT_NULL;
import static com.ck.utils.ValidateUtils.validate;


public class StatisticServiceImpl implements StatisticService{

    @Autowired
    private ContentsRepository contentsRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AttachRepository attachRepository;
    @Autowired
    private LogRepository logRepository;

    @Override
    public DashBoardVo getDashBoard(Long userId) {
        validate(userId,NOT_NULL,"用户不存在");
        User user = User.builder()
                .uid(userId)
                .build();
        int contentCount = contentsRepository.countByUserAndTypeAndStatus(user, Types.ARTICLE,Types.PUBLISH);
        int commentCount = commentRepository.countByAuthor_id(userId);
        int attachCount = attachRepository.countByAuthor(user);
        StatisticsAnalysisVo statisticsAnalysisVo = StatisticsAnalysisVo
                .builder()
                .totalAttachCount(attachCount)
                .totalCommentCount(commentCount)
                .totalPublishArticleCount(contentCount)
                .build();

        Pageable pageable = new PageRequest(1,10);
        Page<Comments> commentsPage = commentRepository.findAllByAuthor_idOrderByCreated(userId,pageable);
        Page<Contents> contentsPage = contentsRepository.findAllByUserAndTypeAndStatusOrderByCreated(user,Types.ARTICLE,Types.PUBLISH,pageable);
        Page<Logs> logsPage = logRepository.findAllByAuthorOrderByCreated(user,pageable);
        DashBoardVo dashBoardVo = DashBoardVo
                .builder()
                .statisticsAnalysisVo(statisticsAnalysisVo)
                .commentsList(commentsPage.getContent())
                .contentsList(contentsPage.getContent())
                .logsList(logsPage.getContent())
                .build();
        return dashBoardVo;
    }
}
