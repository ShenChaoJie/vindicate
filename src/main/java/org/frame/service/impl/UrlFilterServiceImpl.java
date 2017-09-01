package org.frame.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.frame.persistence.UrlFilterMapper;
import org.frame.persistence.model.UrlFilter;
import org.frame.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlFilterServiceImpl implements UrlFilterService {

    @Autowired
    private UrlFilterMapper urlFilterMapper;

    @Autowired
    private ShiroFilerChainManager shiroFilerChainManager;

    @Override
    public UrlFilter createUrlFilter(UrlFilter urlFilter) {
    	urlFilterMapper.createUrlFilter(urlFilter);
        initFilterChain();
        return urlFilter;
    }

    @Override
    public UrlFilter updateUrlFilter(UrlFilter urlFilter) {
    	urlFilterMapper.updateUrlFilter(urlFilter);
        initFilterChain();
        return urlFilter;
    }

    @Override
    public void deleteUrlFilter(Long urlFilterId) {
    	urlFilterMapper.deleteUrlFilter(urlFilterId);
        initFilterChain();
    }

    @Override
    public UrlFilter findOne(Long urlFilterId) {
        return urlFilterMapper.findOne(urlFilterId);
    }

    @Override
    public List<UrlFilter> findAll() {
        return urlFilterMapper.findAll();
    }

    @PostConstruct
    public void initFilterChain() {
        shiroFilerChainManager.initFilterChains(findAll());
    }

}
