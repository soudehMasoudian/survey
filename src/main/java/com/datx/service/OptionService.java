package com.datx.service;

import com.datx.mapper.Option;
import com.datx.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 12/2/2016.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class OptionService<T, PK extends Serializable> implements IGeneralService<Option, Long> {

    @Autowired
    OptionRepository optionRepository;

    @Override
    public void saveOrUpdate(Option option) throws Exception {
        optionRepository.saveOrUpdate(option);
    }

    @Override
    public void saveOrUpdate(List<Option> t) throws Exception {
        optionRepository.saveOrUpdate(t);
    }

    @Override
    public void delete(Long id) throws Exception {
        optionRepository.delete(id);
    }

    @Override
    public Option findOne(Long id) throws Exception {
        return (Option) optionRepository.findOne(id);
    }

    @Override
    public List<Option> findAll() throws Exception {
        return optionRepository.findAll();
    }

    @Override
    public long count() throws Exception {
        return optionRepository.count();
    }

    public List<Option> addCounterAndUpdate(List ids)throws Exception {
        List<Option> options = optionRepository.findAllBySurveyId(ids);
        List<Option> newOptions = new ArrayList<>();
        for (Option option : options) {
            option.setCounter(option.getCounter() + 1);
            newOptions.add(option);
        }
//        optionRepository.saveOrUpdate(newOptions);
        return newOptions;
    }
}
