package com.gdpi.mapper;

import com.gdpi.bean.Comment;
import com.gdpi.bean.CommentExample;
import com.gdpi.bean.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}