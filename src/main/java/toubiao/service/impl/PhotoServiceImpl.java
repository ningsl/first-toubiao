package toubiao.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toubiao.constant.Web;
import toubiao.controller.PhotoController;
import toubiao.dao.PhotoDaoI;
import toubiao.dao.ProjectDaoI;
import toubiao.dao.ProjectPhotoDaoI;
import toubiao.model.Tphoto;
import toubiao.model.Tproject;
import toubiao.model.TprojectPhoto;
import toubiao.pageModel.Photo;
import toubiao.pageModel.ProjectPhoto;

@Service
public class PhotoServiceImpl {
	static Logger logger = LogManager.getLogger(PhotoServiceImpl.class.getName());
	
	@Autowired
	private PhotoDaoI photoDao;
	
	@Autowired
	private ProjectDaoI projectDao;
	
	@Autowired
	private ProjectPhotoDaoI projectPhotoDao;
	
	@Autowired
	private ProjectServiceImpl projectService;
	
	public Photo getPhotoById(Long idLong ){
		Tphoto photo=photoDao.get(Tphoto.class, idLong);
		if(photo==null){
			return null;
		}
		Photo p=new Photo();
		BeanUtils.copyProperties(photo, p);
		
		return  p;
	}
	
	/** 更新database 照片信息
	 * @param photo
	 */
	public void updatePhoto(Photo photo ){
		Long id=photo.getId();
		Tphoto p=photoDao.get(Tphoto.class, id);
		p.setTitle(photo.getTitle());
		p.setDescription(photo.getDescription());
		photoDao.update(p);
	}
	
	/** 1.根据id ,删除database中的信息，2.删除disk上的照片文件
	 * @param idlLong
	 */
	public void deletePhoto(Long idlLong){
		//delete file in disk
		Tphoto p=photoDao.get(Tphoto.class, idlLong);
		if(p==null){
			throw new RuntimeException("get Tphoto by id is null;");
		}
		File file=new File(Web.WEB_ABS_PATH+p.getWebPath());
	
		// active when deploy to tomcate
		// System.out.println(file.getAbsolutePath());
		if(!file.delete()){
			/*throw new RuntimeException("delete file in disk failed;");*/
			logger.debug("文件删除失败");
		}
		
		//delete database
		photoDao.delete(p);
	}
	
	
	/**根据project_id，查找相应目录，并存入文件
	 * @param projectId
	 * @param originalFileName
	 * @return
	 */
	public ProjectPhoto saveProjectPhotoFile(Long projectId,String originalFileName){
		Tproject p=projectDao.get(Tproject.class, projectId);
		if(p==null){
			throw new RuntimeException("未找到所属项目!");
		}
		ProjectPhoto photo=new ProjectPhoto();
		
		//获取文件名和后缀
		String fileName=originalFileName.substring(0, originalFileName.lastIndexOf("."));
		String fileSuffix=originalFileName.substring(originalFileName.lastIndexOf("."),originalFileName.length());
		
		logger.debug(fileName);
		
		//初始话projectPhoto的属性,根据project的属性，定义web path
		photo.setTitle(fileName);
		photo.setType("合同照片");
		photo.setProjectId(projectId);
		photo.setSeqInType(p.getProjectPhotoSet().size()+1);
		
		//默认必有partA 和  achievementCode
		String webPath="/resource"+"/"+p.getPartA().trim()+"/"+p.getAchievementCode().trim()+"/"+
					photo.getType().trim();
		logger.debug("localDiskPath="+Web.WEB_ABS_PATH+webPath);
		File localDiskPath=new File(Web.WEB_ABS_PATH+webPath);
		
		
		logger.debug("Web.LOCAL_ABS_PATH="+Web.WEB_ABS_PATH);
		logger.debug("localDiskFile="+localDiskPath);
		if(!localDiskPath.exists()){
			logger.debug(localDiskPath+"  目录不存在");
			if(!localDiskPath.mkdirs()){
				throw new RuntimeException("创建目录("+webPath+")失败");//创建失败的话，应该抛出异常
			}
		}
		
		//生成文件名有2种：1.uuid 唯一，但不直观，不出现冲突  2.typeName+序号,可能导致存储失败
		//调试的时候采用2,实际采用1
		UUID uuid = UUID.randomUUID();
		String savedWebPath=webPath+"/"+uuid.toString()+fileSuffix;
		logger.debug(savedWebPath);
		photo.setWebPath(savedWebPath);
		
		return photo;
	}
	
	public int getAvailableTypeSeq(String objectType,Long objectId,String photoType) throws ClassNotFoundException{
		
		if(objectType.equals(Tproject.class.getSimpleName())){
			
		}
		
		return 0;
	}

	/**将照片数据 存入database
	 * @param photo
	 */
	public void saveProjectPhoto(ProjectPhoto photo) {
		// TODO Auto-generated method stub
		
		Long projectId=photo.getProjectId();
		if(projectId==null){
			throw new RuntimeException("保存项目照片时，project id 为空");
		}
		Tproject project=projectDao.get(Tproject.class, projectId);
		
		TprojectPhoto photoDes=new TprojectPhoto();
		BeanUtils.copyProperties(photo, photoDes);
		photoDes.setProject(project);
		
		photoDes.setSeqInType(project.getProjectPhotoSet().size()+1);
		photoDes.setType("合同照片");
		
		String webPathString=photoDes.getWebPath();
		String fileName=webPathString.substring(webPathString.lastIndexOf("/")+1,webPathString.length());
		photoDes.setFileName(fileName);
		
		photoDao.save(photoDes);
		
		photoDao.getCurrentSession().evict(project);
		
		}

	//根据路径，删除照片文件
	public void deleteProjectPhotoFile(String absPath) {
		// TODO Auto-generated method stub
		logger.debug("is beginning");
		logger.debug(absPath);
		File file=new File(absPath);
		if(file.exists()){
			if(file.delete()){
				logger.debug(absPath+"is deleted");
			}
		}else {
			logger.debug("文件未找到");
		}
	}
	
	public List<ProjectPhoto> getProjectPhotoList(Long id){
		if(id==null)
			return null;
		String hql="from TprojectPhoto p   where p.project.id="+id;
		List<TprojectPhoto> listSrc=projectPhotoDao.find(hql);
		List<ProjectPhoto>  ListDes=new ArrayList<ProjectPhoto>();
		for(TprojectPhoto p:listSrc){
			ProjectPhoto photoDes=new ProjectPhoto();
			BeanUtils.copyProperties(p, photoDes);
			photoDes.setProjectId(id);
			ListDes.add(photoDes);
		}
		return ListDes;
	}
	
	
	
}
