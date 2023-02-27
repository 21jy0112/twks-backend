package model;

import java.io.Serializable;

public class Employee implements Serializable {
	private String empId;
	private String empName;
	private String empKana;
	private String empSex;
	private String empTel;
	private String empEmail;
	private String empPostCode;
	private String empAddress;
	private String empBirthDate;
	private String empInitDate;
	private String empPassword;
	private String jobTitleCode;
	private String deptCode;
	private String rankCode;

	public Employee() {
		super();
	}

	public Employee(String empId, String empName, String empKana, String empSex, String empTel, String empEmail,
			String empPostCode, String empAddress, String empBirthDate, String empInitDate, String empPassword,
			String jobTitleCode, String deptCode, String rankCode) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empKana = empKana;
		this.empSex = empSex;
		this.empTel = empTel;
		this.empEmail = empEmail;
		this.empPostCode = empPostCode;
		this.empAddress = empAddress;
		this.empBirthDate = empBirthDate;
		this.empInitDate = empInitDate;
		this.empPassword = empPassword;
		this.jobTitleCode = jobTitleCode;
		this.deptCode = deptCode;
		this.rankCode = rankCode;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpKana() {
		return empKana;
	}

	public void setEmpKana(String empKana) {
		this.empKana = empKana;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpPostCode() {
		return empPostCode;
	}

	public void setEmpPostCode(String empPostCode) {
		this.empPostCode = empPostCode;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpBirthDate() {
		return empBirthDate;
	}

	public void setEmpBirthDate(String empBirthDate) {
		this.empBirthDate = empBirthDate;
	}

	public String getEmpInitDate() {
		return empInitDate;
	}

	public void setEmpInitDate(String empInitDate) {
		this.empInitDate = empInitDate;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getJobTitleCode() {
		return jobTitleCode;
	}

	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getRankCode() {
		return rankCode;
	}

	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}

}
