package com.datx.controller;

import com.datx.mapper.*;
import com.datx.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by Soudeh Masoudian on 11/28/2016.
 */
@Controller
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    AccountService accountService;

    @Autowired
    OptionService optionService;

    @Autowired
    ChosenOptionService chosenOptionService;

    protected static final String ACTION_NEW_SURVEY = "/newSurvey";
    protected static final String ACTION_VIEW_SURVEY = "/viewSurvey";
    protected static final String ACTION_VIEW_SURVEY_COMMENTS = "/Surveycomments";
    protected static final String ACTION_SURVEY_LIST = "/surveyList";
    protected static final String ACTION_SURVEY_USER = "/surveyUser";
    protected static final String ACTION_SURVEY_USER_VIEW = "/surveyUserView";
    protected static final String ACTION_SURVEY_USER_HAS_SURVEY_ALREADY= "/userHasSurveyAlready";
    protected static final String ACTION_LIST = "survey/surveyList";
    protected static final String ACTION_View = "survey/surveyView";
    protected static final String ACTION_CREATE = "survey/newSurvey";
    protected static final String ACTION_COMMENTS = "survey/surveyComments";
    protected static final String ACTION_USER = "survey/userSurvey";
    protected static final String ACTION_USER_VIEW = "survey/userSurveyView";
    protected static final String ACTION_USER_HAS_SURVEY_ALREADY = "survey/userHasSurveyAlready";

    @GetMapping("survey")
    public String survey() {
        return "survey/survey";
    }

    @GetMapping("/newSurvey")
    public String newSurvey(@ModelAttribute("survey") Survey survey, Model model) {
        model.addAttribute("actionUrl", ACTION_NEW_SURVEY);
        model.addAttribute("returnUrl", ACTION_SURVEY_LIST);
        model.addAttribute("survey", survey);
        return "survey/newSurvey";
    }
    @PostMapping("/newSurvey")
    public String newSurvey(@Valid @ModelAttribute("survey") Survey survey,
                            BindingResult result, Model model,
                            RedirectAttributes redirectAttrs, HttpServletRequest request) {
        if(result.hasErrors()) {
            model.addAttribute("user", survey);
            model.addAttribute("returnUrl", ACTION_SURVEY_LIST);
            model.addAttribute("actionUrl", ACTION_NEW_SURVEY);
            return ACTION_LIST;
        }
        try {
            int i = 0;
            List<Question> questions = new ArrayList<Question>();
            for (String str : survey.getQuestion()) {
                Question question = new Question();
                question.setDescription(str);
                question.setFkSurvey(survey);
                List<Option> options = new ArrayList<Option>();
                int precedence = 0;
                for (int j = i*4; j < (i+1)*4; j++){
                    Option option = new Option();
                    option.setDescription(survey.getOptions().get(j));
                    option.setFkQuestion(question);
                    option.setPrecedence(precedence);
                    options.add(option);
                    question.setOptions(options);
                    precedence++;
                }
                questions.add(question);
                survey.setQuestions(questions);
                i++;
            }
            surveyService.saveOrUpdate(survey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:" + ACTION_SURVEY_LIST;
    }


    /**
     *  find all survey data
     *
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/surveyList")
    public String findData(Model model) throws Exception {

        List<Survey> surveys = surveyService.findAll();
        model.addAttribute("results", surveys);
        model.addAttribute("viewUrl", ACTION_VIEW_SURVEY);
        model.addAttribute("listUrl", ACTION_SURVEY_LIST);
        model.addAttribute("createUrl", ACTION_NEW_SURVEY);
        return ACTION_LIST;
    }

    @GetMapping("/viewSurvey/{id}")
    public String findSurveyData(@PathVariable("id") long id, Model model) throws Exception {
        List<Survey> surveys = surveyService.findAllBySurveyId(id);
        model.addAttribute("results", surveys);
        model.addAttribute("returnUrl", ACTION_SURVEY_LIST);
        return ACTION_View;
    }

    @GetMapping("/surveycomments/{id}")
    public String findSurveyComments(@PathVariable("id") long id, Model model) throws Exception {
        List<Comment> surveys = commentService.findAllBySurveyId(id);
        model.addAttribute("results", surveys);
        model.addAttribute("returnUrl", ACTION_SURVEY_LIST);
        return ACTION_COMMENTS;
    }

    @GetMapping("/surveyUser")
    public String findMainSurveyForUser(Model model) throws Exception {
        Account account = loggedinUser();
        Survey survey = surveyService.findMainSurvey();
        Set<Survey>surveySet = new HashSet<Survey>();
        surveySet.add(survey);
        if (account.getSurveys().containsAll(surveySet)) {
            return "redirect:" + ACTION_SURVEY_USER_HAS_SURVEY_ALREADY;
        }
        model.addAttribute("result", survey);
        model.addAttribute("actionUrl", ACTION_SURVEY_USER);
        model.addAttribute("returnUrl", ACTION_SURVEY_LIST);
        return ACTION_USER;
    }

    @GetMapping("/userHasSurveyAlready")
    public String userHasSurveyAlready(Model model) throws Exception {
        model.addAttribute("actionUrl", ACTION_SURVEY_USER_HAS_SURVEY_ALREADY);
        model.addAttribute("returnUrl", ACTION_SURVEY_USER_HAS_SURVEY_ALREADY);
        return ACTION_USER_HAS_SURVEY_ALREADY;
    }

    @PostMapping("/surveyUser")
    public String submitUserSurvey(@Valid @ModelAttribute("survey") Survey survey,
                                   @ModelAttribute("comment") Comment comment,
                            BindingResult result, Model model, HttpServletRequest request) {
        if(result.hasErrors()) {
            model.addAttribute("survey", survey);
            model.addAttribute("returnUrl", ACTION_SURVEY_LIST);
            model.addAttribute("actionUrl", ACTION_NEW_SURVEY);
            return ACTION_USER;
        }
        try {
            Account account = loggedinUser();
            // First of all we should save account_survey
            Survey surveyForPersist = surveyService.selectSurveyForUpdate(survey.getId());
            Set<Survey> surveySet = new HashSet<>();
            surveySet.add(surveyForPersist);
            account.setSurveys(surveySet);

            //Second of all we should update survey with new counter
            surveyForPersist.setCounter(surveyForPersist.getCounter() + 1);

            //save comment
            Comment commentForPersist = new Comment();
            commentForPersist.setFkSurvey(surveyForPersist);
            commentForPersist.setFormSerialNo(surveyForPersist.getCounter());
            commentForPersist.setComment(comment.getComment());

            //add counter of option
            HashMap optionsFromRequest = new HashMap();
            optionsFromRequest = (HashMap) request.getParameterMap();
            List optionIds = new ArrayList<>();
            List<Long> ids = new ArrayList<Long>();
            Long id;
            for (Object key : optionsFromRequest.keySet()) {
                String strKey = (String) key;
                if (strKey.startsWith("options")) {
                    String[] str = (String[]) optionsFromRequest.get(strKey);
                    for (String obj : str) {
                        id = Long.valueOf(obj);
                        ids.add(id);
                    }
                }
            }

            List<Option> newOptions = optionService.addCounterAndUpdate(ids);

            // insert into chosen_option
            List<ChosenOption> chosenOptions = new ArrayList<>();
            for (Option option : newOptions) {
                ChosenOption chosenOption = new ChosenOption();
                chosenOption.setFormSerialNo(surveyForPersist.getCounter());
                chosenOption.setFkOption(option.getId());
                chosenOption.setQuestionId(option.getFkQuestion().getId());
                chosenOptions.add(chosenOption);
            }
            surveyService.saveUserSurvey(account, surveyForPersist, commentForPersist, newOptions, chosenOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:" + ACTION_SURVEY_USER_VIEW;
    }

    @GetMapping("/surveyUserView")
    public String surveyUserView(Model model) {
        model.addAttribute("actionUrl", ACTION_NEW_SURVEY);
        model.addAttribute("returnUrl", ACTION_SURVEY_LIST);
        return ACTION_USER_VIEW;
    }
    public Account loggedinUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUser userDetails = (CustomUser) auth.getPrincipal();
        Account account = new Account();
        try {
            account = accountService.findOne(userDetails.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
}
