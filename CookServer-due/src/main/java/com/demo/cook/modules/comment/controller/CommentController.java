package com.demo.cook.modules.comment.controller;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.common.utils.ReqUtil;
import com.demo.cook.modules.comment.model.Comment;
import com.demo.cook.modules.comment.model.CommentDetails;
import com.demo.cook.modules.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    private ICommentService commentService;

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public RtnResult publishComment(@RequestBody Comment product){

        try {
            return commentService.publishComment(product);
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/queryCommentList",method = RequestMethod.GET)
    RtnResult<List<CommentDetails>> queryCommentList(HttpServletRequest request){
        try {
            return commentService.queryCommentList(ReqUtil.getParams(request));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }
}
