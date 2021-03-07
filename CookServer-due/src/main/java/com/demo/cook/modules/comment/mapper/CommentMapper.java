package com.demo.cook.modules.comment.mapper;

import com.demo.cook.modules.comment.model.Comment;
import com.demo.cook.modules.comment.model.CommentDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int insert(Comment record) throws Exception;

    List<CommentDetails> queryCommentList(String targetId,String loginUserName) throws Exception;

}