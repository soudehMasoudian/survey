package com.datx.service;

import com.datx.mapper.Question;
import com.datx.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 12/1/2016.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class QuestionService<T, PK extends Serializable> implements IGeneralService<Question, Long> {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void saveOrUpdate(Question question) throws Exception {
        questionRepository.saveOrUpdate(question);
    }

    @Override
    public void saveOrUpdate(List<Question> t) throws Exception {
        questionRepository.saveOrUpdate(t);
    }

    @Override
    public void delete(Long id) throws Exception {
        questionRepository.delete(id);
    }

    @Override
    public Question findOne(Long id) throws Exception {
        return (Question) questionRepository.findOne(id);
    }

    @Override
    public List<Question> findAll() throws Exception {
        return questionRepository.findAll();
    }


    @Override
    public long count() throws Exception {
        return 0;
    }
}
