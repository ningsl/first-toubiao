package toubiao.pageModel;

import java.util.Date;

public class Project {
	private Long id;
	
	private Long sn;
	
	private String designerNames;
	
	private String designerIds;
	
	private String designerPhones;
	
	private String achievementCode;
	
	private String designCode;
	
	private String projectName;
	
//	无线网/承载网/核心网/业务网/传输线路/传输设备/接入网/电源
	private String classByProfession;
	
//	设计/可研/咨询规划
	private String classByPhase;
	
//  承担部门names
	private String departmentNames;
	
//  承担部门ids
	private String departmentIds;	
	
//  项目分类:一般项目、大型项目，重要项目，部门协作
	private String classByImportance;
	
//	甲方公司:联通，移动
	private String partA;
	
	//投资金额
	private Float investAmount;
		
	private String description;
	
	//委托日期
	private Date beginDate;
	
	//对应合同的id
	private String contractIds;
	
	//对应合同的编号s
	private String contractSns;
	
	//对应合同的名称s
	private String contractNames;
	
	//对应委托人
	private String contactName;
	
	//对应委托人
	private Long contactId;
	
	//对应委托人
	private String contactPhone;

	//存储目录
	private String webPath;
	
	
	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}
	
	

	public String getContractIds() {
		return contractIds;
	}

	public void setContractIds(String contractIds) {
		this.contractIds = contractIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getDesignerNames() {
		return designerNames;
	}

	public void setDesignerNames(String designerNames) {
		this.designerNames = designerNames;
	}

	public String getDesignerIds() {
		return designerIds;
	}

	public void setDesignerIds(String designerIds) {
		this.designerIds = designerIds;
	}

	public String getDesignerPhones() {
		return designerPhones;
	}

	public void setDesignerPhones(String designerPhones) {
		this.designerPhones = designerPhones;
	}

	public String getAchievementCode() {
		return achievementCode;
	}

	public void setAchievementCode(String achievementCode) {
		this.achievementCode = achievementCode;
	}

	public String getDesignCode() {
		return designCode;
	}

	public void setDesignCode(String designCode) {
		this.designCode = designCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClassByProfession() {
		return classByProfession;
	}

	public void setClassByProfession(String classByProfession) {
		this.classByProfession = classByProfession;
	}

	public String getClassByPhase() {
		return classByPhase;
	}

	public void setClassByPhase(String classByPhase) {
		this.classByPhase = classByPhase;
	}

	public String getDepartmentNames() {
		return departmentNames;
	}

	public void setDepartmentNames(String departmentNames) {
		this.departmentNames = departmentNames;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getClassByImportance() {
		return classByImportance;
	}

	public void setClassByImportance(String classByImportance) {
		this.classByImportance = classByImportance;
	}

	public String getPartA() {
		return partA;
	}

	public void setPartA(String partA) {
		this.partA = partA;
	}

	public Float getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Float investAmount) {
		this.investAmount = investAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getContractSns() {
		return contractSns;
	}

	public void setContractSns(String contractSns) {
		this.contractSns = contractSns;
	}

	public String getContractNames() {
		return contractNames;
	}

	public void setContractNames(String contractNames) {
		this.contractNames = contractNames;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	
	
}
