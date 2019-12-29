package com.gdpi.mapper;

import com.gdpi.bean.Question;
import com.gdpi.dto.SearchDTO;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    List<Question> countBySearch(SearchDTO searchDTO);
}