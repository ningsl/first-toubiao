package toubiao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toubiao.dao.CertificatePhotoDaoI;

@Service
public class CertificatePhotoServiceImpl {
	@Autowired
	private CertificatePhotoDaoI certificatePhotoDao;
	
	
	public void getAllCertificatePhoto(){
		String hql="from TcertificatePhoto";
		List list=certificatePhotoDao.find(hql);
	}
	
}
