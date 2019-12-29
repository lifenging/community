package com.gdpi.controller;

import com.gdpi.bean.Comment;
import com.gdpi.bean.User;
import com.gdpi.dto.CommentCreatorDTO;
import com.gdpi.dto.CommentDTO;
import com.gdpi.dto.ResultDTO;
import com.gdpi.enums.CommentTypeEnums;
import com.gdpi.exception.CustomizeErrorCode;
import com.gdpi.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreatorDTO commentCreatorDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreatorDTO == null || StringUtils.isBlank(commentCreatorDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreatorDTO.getParentId());
        comment.setContent(commentCreatorDTO.getContent());
        comment.setType(commentCreatorDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment, user);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnums.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
