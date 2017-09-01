package org.frame.persistence;

import java.util.List;

import org.frame.persistence.model.UrlFilter;

public interface UrlFilterMapper {

	  public void createUrlFilter(UrlFilter urlFilter);
	  public void updateUrlFilter(UrlFilter urlFilter);
	  public void deleteUrlFilter(Long urlFilterId);

	  public UrlFilter findOne(Long urlFilterId);
	  public List<UrlFilter> findAll();
}
