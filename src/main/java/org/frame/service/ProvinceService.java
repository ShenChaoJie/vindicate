package org.frame.service;

import java.util.List;
import java.util.Map;

import org.frame.common.page.PageBar;
import org.frame.persistence.model.TProvince;

public interface ProvinceService {
	/*public Map<String, String> addProvince(Map<String, String> para) throws Exception;

	public Map<String, String> upProvince(Map<String, String> para) throws Exception;

	public TProvince getProvince(Map<String, String> para) throws Exception;

	public PageBar queryProvince(Map<String, String> para) throws Exception;

	public PageBar queryCity(Map<String, String> para) throws Exception;

	public PageBar queryProvinceDetail(Map<String, String> para) throws Exception;
	
	public PageBar queryProvinceForChoose(Map<String, String> para) throws Exception;

	public Map<String, String> delProvince(Map<String, String> para) throws Exception;

	public List queryProvince() throws Exception;
	
	public TProvince findById(Integer id) throws Exception;

	public List<TProvince> findByPid(Integer pid) throws Exception;
	
	public List<TProvince> findByIds(String ids) throws Exception;
	
	public List<TProvince> getProvinceAndCity();*/
	
	public PageBar queryProvince(Map<String, String> para) throws Exception;
		
	public List<TProvince> findAll();
	
	public TProvince findByName(String name);
	

}
