package com.gdpi.controller;

import com.gdpi.dto.QuestionDTO;
import com.gdpi.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "search", required = false) String search) {

        PageInfo<QuestionDTO> questionList = questionService.list(search, page);
        model.addAttribute("questions", questionList);
        model.addAttribute("search", search);

        return "index";
    }


}
