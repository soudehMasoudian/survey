package com.datx.service;

import com.datx.mapper.Comment;
import com.datx.mapper.Survey;
import com.datx.repository.CommentRepository;
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
public class CommentService<T, PK extends Serializable> implements IGeneralService<Comment, Long> {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void saveOrUpdate(Comment comment) throws Exception {
        commentRepository.saveOrUpdate(comment);
    }

    @Override
    public void saveOrUpdate(List<Comment> t) throws Exception {
        commentRepository.saveOrUpdate(t);
    }

    @Override
    public void delete(Long id) throws Exception {
        commentRepository.delete(id);
    }

    @Override
    public Comment findOne(Long id) throws Exception {
        return (Comment) commentRepository.findOne(id);
    }

    @Override
    public List<Comment> findAll() throws Exception {
        return commentRepository.findAll();
    }

    @Override
    public long count() throws Exception {
        return commentRepository.count();
    }

    public List<Comment> findAllBySurveyId(long surveyId) throws Exception {
        return commentRepository.findAllBySurveyId(surveyId);
    }

}
