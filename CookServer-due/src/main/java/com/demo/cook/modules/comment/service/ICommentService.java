package com.demo.cook.modules.comment.service;

import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.comment.model.Comment;
import com.demo.cook.modules.comment.model.CommentDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ICommentService {

    RtnResult publishComment(Comment product) throws Exception;

    RtnResult<List<CommentDetails>> queryCommentList(Map<String,String> request) throws Exception;

}
