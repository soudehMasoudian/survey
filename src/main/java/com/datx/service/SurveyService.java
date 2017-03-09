package com.datx.service;

import com.datx.mapper.*;
import com.datx.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 11/30/2016.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SurveyService<T, PK extends Serializable> implements IGeneralService<Survey, Long>{

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private ChosenOptionService chosenOptionService;

    @Override
    public void saveOrUpdate(Survey survey) throws Exception {
        List<Survey> surveys = surveyRepository.findMainSurveys();
        if (survey.getIsMain() && surveys.size() > 0) {
            for(Survey newSurvey : surveys) {
                newSurvey.setIsMain(false);
                surveyRepository.saveOrUpdate(newSurvey);
            }
        }
        surveyRepository.saveOrUpdate(survey);
    }

    public void update(Survey survey) throws Exception {
        surveyRepository.merge(survey);
    }
    @Override
    public void saveOrUpdate(List<Survey> t) throws Exception {
        surveyRepository.saveOrUpdate(t);
    }

    @Override
    public void delete(Long id) throws Exception {
        surveyRepository.delete(id);
    }

    @Override
    public Survey findOne(Long id) throws Exception {
        return (Survey) surveyRepository.findOne(id);
    }

    public Survey selectSurveyForUpdate(long id) throws Exception {
        return (Survey) surveyRepository.selectSurveyForUpdate(id);
    }

    @Override
    public List<Survey> findAll() throws Exception {
        return surveyRepository.findAll();
    }

    public List<Survey> findAllBySurveyId(long surveyId) throws Exception {
        return surveyRepository.findAllBySurveyId(surveyId);
    }

    @Override
    public long count() throws Exception {
        return surveyRepository.count();
    }


    public Survey findMainSurvey() throws Exception {
        return surveyRepository.findMainSurvey();
    }

    public void saveUserSurvey(Account account, Survey survey, Comment comment, List<Option> options, List<ChosenOption> chosenOptions) throws Exception {
        accountService.update(account);
        update(survey);
        commentService.saveOrUpdate(comment);
        optionService.saveOrUpdate(options);
        chosenOptionService.saveOrUpdate(chosenOptions);
    }
}
