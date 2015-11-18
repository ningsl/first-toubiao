package toubiao.service.impl;

import java.util.ArrayList;
import java.util.List;

import nsl.utils.EasyUI133Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import toubiao.dao.DesignerDaoI;
import toubiao.model.TcertificatePhoto;
import toubiao.model.Tdesigner;
import toubiao.model.Tproject;
import toubiao.pageModel.CertificatePhoto;
import toubiao.pageModel.DataGrid;
import toubiao.pageModel.Designer;
import toubiao.pageModel.FilterCondition;
import toubiao.pageModel.PageHelper;
import toubiao.pageModel.Project;
import toubiao.utils.FilterHqlFactory;

@Service
public class DesignerServiceImpl {
	static Logger logger = LogManager.getLogger(DesignerServiceImpl.class.getName());
	
	@Autowired
	private DesignerDaoI designerDao;

	/** get all designers
	 * @return
	 */
	public List<Tdesigner> getAllTdesigners(){
		String hql="from Tdesigner designer left join fetch designer.department ";
		List<Tdesigner> list=designerDao.find(hql);
		return list;
	}
	
	/** 返回datagird ，生成jsp desigenr grid
	 * @param conditionList
	 * @param ph
	 * @return
	 */
	public DataGrid dataGridByConditions(List<FilterCondition> conditionList,
			PageHelper ph) {
		// TODO Auto-generated method stub
		DataGrid dg = new DataGrid();
		String conditionHql=FilterHqlFactory.buildHqlByList(conditionList);
		String ordHql=EasyUI133Utils.buildOrderHql(ph);
		System.out.println(conditionHql);
		String hql = "select distinct designer from Tdesigner designer left join fetch designer.department department  where 1=1";//查询list
		String countHql="select count(*) from Tdesigner designer  where 1=1";//查询数量
		if(conditionHql!=null  &&  !conditionHql.equals("")){
			hql=hql+" and "+conditionHql;
			countHql+=" and "+conditionHql;
		}
		hql+=ordHql;
		//System.out.println(hql);
		
		List<Tdesigner> tList = designerDao.find(hql, ph.getPage(), ph.getRows());
		List<Designer> designerList=dataBaseDesignerList2PageDesignerList(tList);
		dg.setRows(designerList);

		dg.setTotal(designerDao.count(countHql));
		
		String jsonString=JSON.toJSONString(dg);
		System.out.println(jsonString);
		
		return dg;
	}

	public Designer dataBaseDesigner2PageDesigner(Tdesigner designerSrc) {
		// TODO Auto-generated method stub
		Designer designerDes=new Designer();
		logger.debug(designerSrc);
		BeanUtils.copyProperties(designerSrc,designerDes);
		if(designerSrc.getDepartment()!=null){
			designerDes.setDepartmentName(designerSrc.getDepartment().getName());
		}
		logger.debug(designerDes);
		return designerDes;
	}

	public List<Designer> dataBaseDesignerList2PageDesignerList(List<Tdesigner> tList){
		List<Designer> dList=new ArrayList<Designer>(0);
		if(tList!=null){
			for(Tdesigner d:tList){
				Designer project=dataBaseDesigner2PageDesigner(d);
				dList.add(project);
			}
		}
		return dList;
	}
	
	public Designer getDesignerDetail(Long idLong) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CertificatePhoto> getCertificatePhotoList(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteDesignerById(Long idLong) {
		// TODO Auto-generated method stub
		// 1.删除相应文件夹  2.删除 关联的photo  3.delete designer
		
		// 1.删除相应文件夹：待补充
		
		Tdesigner designer=designerDao.get(Tdesigner.class, idLong);
		if(designer==null){
			logger.debug(idLong+ " project is null");
			return false;
		}
		
		//2.删除ProjectPhoto
		String deletePhotoHql="delete from TcertificatePhoto p where p.designer.id="+idLong;
		int count=designerDao.executeHql(deletePhotoHql);
		logger.debug(idLong+" delete photos number:"+count);
		
		//3.删除this
		designerDao.delete(designer);
		
	    logger.debug(idLong+"  delete success");
		return true;
	}

	public String downloadeExcelOfAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public String downloadeExcelChecked(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public String downloadExcelFiltered(List<FilterCondition> conditionList) {
		// TODO Auto-generated method stub
		return null;
	}

	public String downloadeDocxOfAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public String downloadDocxChecked(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public String downloadDocxFiltered(List<FilterCondition> conditionList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
