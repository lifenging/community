package com.gdpi.controller;

import com.gdpi.dto.CommentDTO;
import com.gdpi.dto.QuestionDTO;
import com.gdpi.enums.CommentTypeEnums;
import com.gdpi.service.CommentService;
import com.gdpi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id") Long id,
                           Model model) {

        QuestionDTO questionDTO = questionService.getById(Math.toIntExact(id));

        List<QuestionDTO> relatedQuestion = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments=commentService.listByTargetId(id, CommentTypeEnums.QUESTION);


        //累加评论
        questionService.incView(Math.toIntExact(id));
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestion",relatedQuestion);

        return "question";
    }
}
