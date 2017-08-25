package org.frame.service.impl;

import java.util.List;
import java.util.Map;

import org.frame.common.page.PageBar;
import org.frame.persistence.TProvinceMapper;
import org.frame.persistence.model.TProvince;
import org.frame.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private TProvinceMapper provinceMapper;
	
	/*
	 * (non-Javadoc)
	 * @see org.frame.service.api.ProvinceService#findAll()
	 */
	public List<TProvince> findAll() {
		PageBounds pageBounds = new PageBounds();
		return provinceMapper.findAll(pageBounds);
	}

	/*
	 * (non-Javadoc)
	 * @see org.frame.service.api.ProvinceService#findByName(java.lang.String)
	 */
	public TProvince findByName(String name) {
		return provinceMapper.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see org.frame.service.api.ProvinceService#queryProvince(java.util.Map)
	 */
	public PageBar queryProvince(Map<String, String> para) throws Exception {
		PageBar pb = new PageBar(para);
		PageBounds pageBounds = new PageBounds();
		pb.setTotalNum(provinceMapper.findAll(pageBounds).size());
		
		pageBounds = new PageBounds((int)pb.getCurrentPageNum(),(int)pb.getEveryPageNum());
		
		pb.setResultList(provinceMapper.findAll(pageBounds));
		return pb;
	}

	

}
