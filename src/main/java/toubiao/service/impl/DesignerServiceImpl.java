package toubiao.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toubiao.dao.DesignerDaoI;
import toubiao.model.TcertificatePhoto;
import toubiao.pageModel.CertificatePhoto;
import toubiao.pageModel.DataGrid;
import toubiao.pageModel.Designer;
import toubiao.pageModel.FilterCondition;
import toubiao.pageModel.PageHelper;
import toubiao.pageModel.Project;

@Service
public class DesignerServiceImpl {
	static Logger logger = LogManager.getLogger(DesignerServiceImpl.class.getName());
	
	@Autowired
	private DesignerDaoI designerDao;

	public DataGrid dataGridByConditions(List<FilterCondition> conditionList,
			PageHelper ph) {
		// TODO Auto-generated method stub
		return null;
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
		return false;
	}
	
	
}
