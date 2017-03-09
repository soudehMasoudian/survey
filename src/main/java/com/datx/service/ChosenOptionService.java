package com.datx.service;

import com.datx.mapper.ChosenOption;
import com.datx.repository.ChosenOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soudeh Masoudian on 12/2/2016.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ChosenOptionService<T, PK extends Serializable> implements IGeneralService<ChosenOption, Long> {

    @Autowired
    ChosenOptionRepository repository;

    @Override
    public void saveOrUpdate(ChosenOption chosenOption) throws Exception {
        repository.saveOrUpdate(chosenOption);
    }

    @Override
    public void saveOrUpdate(List<ChosenOption> t) throws Exception {
        repository.saveOrUpdate(t);
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.delete(id);
    }

    @Override
    public ChosenOption findOne(Long id) throws Exception {
        return (ChosenOption) repository.findOne(id);
    }

    @Override
    public List<ChosenOption> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public long count() throws Exception {
        return repository.count();
    }
}
