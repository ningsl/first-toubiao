package toubiao.service;

import java.util.List;

import toubiao.pageModel.ResourceType;

/**
 * 资源类型服务
 * 
 * @author 孙宇
 * 
 */
public interface ResourceTypeServiceI {

	/**
	 * 获取资源类型
	 * 
	 * @return
	 */
	public List<ResourceType> getResourceTypeList();

}
