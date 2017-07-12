package org.frame.persistence;

import java.util.List;

import org.frame.persistence.model.TProvince;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


public interface TProvinceMapper {
	
	public List<TProvince> findAll(PageBounds pageBounds);
	
	public TProvince findByName(String name);
	
}
