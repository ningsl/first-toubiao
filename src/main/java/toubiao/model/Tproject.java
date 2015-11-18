package toubiao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "t_project")
@DynamicUpdate(true)
public class Tproject {
	
	private Long id;
	
	private Long sn;
	
	private String achievementCode;
	
	private String designCode;
	
	private String projectName;
	
//	无线网/承载网/核心网/业务网/传输线路/传输设备/接入网/电源
	private String classByProfession;
	
//	设计/可研/咨询规划
	private String classByPhase;
	
//  承担部门 多对多
	private Set<Tdepartment> departmentSet;
	
//  项目分类:一般项目、大型项目，重要项目，部门协作
	private String classByImportance;
	
//	甲方公司:联通，移动
	private String partA;
	
	//投资金额
	private Float investAmount;
	
	//委托日期
	@Temporal(TemporalType.DATE)  
	private Date beginDate;
	
	private String description;
	
	//设计师，多对多
	private Set<Tdesigner> designerSet;
	
	//对应合同，多对多	
	private Set<Tcontract> contractSet;
	
	//甲方委托人
	private Tcontact contact;
	
	//获奖情况:1对多
	private Set<Reward> rewardSet;
	
	/*private List<TprojectPhoto> projectPhotoList=new ArrayList<TprojectPhoto>();*/
	
	private Set<TprojectPhoto> projectPhotoSet;
	
	private String webPath;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPartA() {
		return partA;
	}
	
	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public void setPartA(String partA) {
		this.partA = partA;
	}
	
	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
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

	

	public Float getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Float investAmount) {
		this.investAmount = investAmount;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_project_designer", joinColumns = { @JoinColumn(name = "project_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "designer_id", nullable = false) })
	@OrderBy("name ASC")
	public Set<Tdesigner> getDesignerSet() {
		return designerSet;
	}

	public void setDesignerSet(Set<Tdesigner> designerSet) {
		this.designerSet = designerSet;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_project_contract", joinColumns = { @JoinColumn(name = "project_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "contract_id", nullable = false) })
	@OrderBy("name ASC")
	public Set<Tcontract> getContractSet() {
		return contractSet;
	}

	public void setContractSet(Set<Tcontract> contractSet) {
		this.contractSet = contractSet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	public Tcontact getContact() {
		return contact;
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_department_project", joinColumns = { @JoinColumn(name = "project_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "department_id", nullable = false) })
	@OrderBy("name ASC")
	public Set<Tdepartment> getDepartmentSet() {
		return departmentSet;
	}

	public void setDepartmentSet(Set<Tdepartment> departmentSet) {
		this.departmentSet = departmentSet;
	}

	public String getClassByImportance() {
		return classByImportance;
	}

	public void setClassByImportance(String classByImportance) {
		this.classByImportance = classByImportance;
	}

	@Type(type="text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setContact(Tcontact partA) {
		this.contact = partA;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Reward> getRewardSet() {
		return rewardSet;
	}

	public void setRewardSet(Set<Reward> rewardSet) {
		this.rewardSet = rewardSet;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	@OrderBy("seqInType ASC")
	public Set<TprojectPhoto> getProjectPhotoSet() {
		return projectPhotoSet;
	}

	public void setProjectPhotoSet(Set<TprojectPhoto> projectPhotoSet) {
		this.projectPhotoSet = projectPhotoSet;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	@OrderBy("seq ASC")
	public List<TprojectPhoto> getProjectPhotoList() {
		return projectPhotoList;
	}

	public void setProjectPhotoList(List<TprojectPhoto> projectPhotoList) {
		this.projectPhotoList = projectPhotoList;
	}*/

	
}
