package com.demo.cook.modules.comment.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.comment.mapper.CommentMapper;
import com.demo.cook.modules.comment.model.Comment;
import com.demo.cook.modules.comment.model.CommentDetails;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements ICommentService {

    private CommentMapper commentMapper;

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public RtnResult publishComment(Comment comment) throws Exception {
        return new RtnResult(Rtn.success,commentMapper.insert(comment));
    }

    @Override
    public RtnResult<List<CommentDetails>> queryCommentList(Map<String,String> request) throws Exception {
        String targetId=  request.get("targetId");
        if (StringUtils.isNullOrEmpty(targetId)){
            return new RtnResult<>(Rtn.missingParameter);
        }
        return new RtnResult<>(Rtn.success,commentMapper.queryCommentList(targetId, request.get("loginUserName")));
    }
}
