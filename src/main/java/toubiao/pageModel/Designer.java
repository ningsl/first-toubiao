package toubiao.pageModel;

import java.util.Date;
import java.util.Set;

import toubiao.model.Tproject;

public class Designer {

	private Long id;
	
	private int sn;
	
	private String name;
	
	private String gender;
	
	private String status;
	
	private int age;
	
	private Date birthday;
	
	//技术职称
	private String professionalTitle;
	
	//在校所学专业
	private String schoolMajor;
	
	//拟分配专业
	private String distributedMajor;
	
	//从事专业
	private String workingMajor;
	
	private int workingYears;
	
	private String departmentName;
	
	private String school;
	
	//学历
	private String education;
	
	private String phone;
	
	//职务
	private String headship;
	
	//参加工作时间
	private Date workDate;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

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

	public  String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	@Override
	public String toString() {
		return "Designer [id=" + id + ", sn=" + sn + ", name=" + name
				+ ", gender=" + gender + ", status=" + status + ", age=" + age
				+ ", birthday=" + birthday + ", professionalTitle="
				+ professionalTitle + ", schoolMajor=" + schoolMajor
				+ ", distributedMajor=" + distributedMajor + ", workingMajor="
				+ workingMajor + ", workingYears=" + workingYears
				+ ", departmentName=" + departmentName + ", school=" + school
				+ ", education=" + education + ", phone=" + phone
				+ ", headship=" + headship + ", workDate=" + workDate + "]";
	}
	
	/*private Set<Tproject> projectSet;*/
	
	
	
	
}
