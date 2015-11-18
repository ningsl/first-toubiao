package toubiao.model;

import java.util.Date;
import java.util.HashSet;
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


@Entity
@Table(name = "t_designer")
public class Tdesigner  {

	
	private Long id;
		
	private int sn;
	
	private String name;
	
	private String gender;
	
	private String status;
	
	private int age;
	
	private Date birthday;
	
	private Set<TcertificatePhoto> certificatePhotoSet=new HashSet<TcertificatePhoto>(0); 
	
	//技术职称
	private String professionalTitle;
	
	//在校所学专业
	private String schoolMajor;
	
	//拟分配专业
	private String distributedMajor;
	
	//从事专业
	private String workingMajor;
	
	private int workingYears;
	
	private Tdepartment department;
	
	private String school;
	
	//学历
	private String education;
	
	private String phone;
	
	//职务
	private String headship;
	
	//参加工作时间
	private Date workDate;
	
	private Set<Tproject> projectSet;
	
	public Tdesigner() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_project_designer", joinColumns = { @JoinColumn(name = "designer_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "project_id", nullable = false) })
	public Set<Tproject> getProjectSet() {
		return projectSet;
	}

	public void setProjectSet(Set<Tproject> projectSet) {
		this.projectSet = projectSet;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	public String getSchoolMajor() {
		return schoolMajor;
	}

	public void setSchoolMajor(String schoolMajor) {
		this.schoolMajor = schoolMajor;
	}

	public String getDistributedMajor() {
		return distributedMajor;
	}

	public void setDistributedMajor(String distributedMajor) {
		this.distributedMajor = distributedMajor;
	}

	public String getWorkingMajor() {
		return workingMajor;
	}

	public void setWorkingMajor(String workingMajor) {
		this.workingMajor = workingMajor;
	}

	public int getWorkingYears() {
		return workingYears;
	}

	public void setWorkingYears(int workingYears) {
		this.workingYears = workingYears;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	public Tdepartment getDepartment() {
		return department;
	}

	
	public void setDepartment(Tdepartment department) {
		this.department = department;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadship() {
		return headship;
	}

	public void setHeadship(String headship) {
		this.headship = headship;
	}

	@Temporal(TemporalType.DATE)
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "designer")
	@OrderBy("seqInType ASC")
	public Set<TcertificatePhoto> getCertificatePhotoSet() {
		return certificatePhotoSet;
	}

	public void setCertificatePhotoSet(Set<TcertificatePhoto> certificatePhotoSet) {
		this.certificatePhotoSet = certificatePhotoSet;
	}

	
}
