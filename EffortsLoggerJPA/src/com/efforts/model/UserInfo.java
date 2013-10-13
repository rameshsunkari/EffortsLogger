package com.efforts.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the USER_INFO database table.
 * 
 */
@Entity
@Table(name = "USER_INFO")
@NamedQueries({
		@NamedQuery(name = UserInfo.GET_ALL_EMP, query = "SELECT u FROM UserInfo u"),
		@NamedQuery(name = UserInfo.CHECK_LOGIN, query = "SELECT u FROM UserInfo u WHERE u.empid = :empId and u.usrpassword =:password "),
		@NamedQuery(name = UserInfo.GET_EMP_BY_ID, query = "SELECT u FROM UserInfo u WHERE u.empid = :empId"),
		@NamedQuery(name = UserInfo.GET_MANAGER_MEMBERS, query = "SELECT u FROM UserInfo u WHERE u.managerInfo.id = :mgrId")})
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String GET_ALL_EMP = "UserInfo.getAllEmp";
	public final static String GET_EMP_BY_ID = "UserInfo.getEmpById";
	public final static String CHECK_LOGIN = "UserInfo.checkLogin";
	public final static String GET_MANAGER_MEMBERS="UserInfo.getManagerMembers";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, precision = 22)
	private long empid;

	@Column(nullable = false, length = 50)
	private String fname;

	@Column(name = "IS_LEAD", nullable = false, length = 1)
	private String isLead;

	@Column(nullable = false, length = 50)
	private String lname;

	@Column(nullable = false, length = 100)
	private String mailid;

	@Column(length = 50)
	private String usrpassword;

	// bi-directional many-to-one association to EffortsInfo
	@OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER)
	private List<EffortsInfo> effortsInfos;

	// bi-directional many-to-one association to ManagerInfo
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID", nullable = false)
	private ManagerInfo managerInfo;

	// bi-directional many-to-one association to ProjectInfo
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID", nullable = false)
	private ProjectInfo projectInfo;

	// bi-directional many-to-one association to RolesInfo
	@ManyToOne
	@JoinColumn(name = "ROLES_INFO_ID", nullable = false)
	private RolesInfo rolesInfo;

	public UserInfo() {
	}

	public long getEmpid() {
		return this.empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getIsLead() {
		return this.isLead;
	}

	public void setIsLead(String isLead) {
		this.isLead = isLead;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMailid() {
		return this.mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getUsrpassword() {
		return this.usrpassword;
	}

	public void setUsrpassword(String usrpassword) {
		this.usrpassword = usrpassword;
	}

	public List<EffortsInfo> getEffortsInfos() {
		return this.effortsInfos;
	}

	public void setEffortsInfos(List<EffortsInfo> effortsInfos) {
		this.effortsInfos = effortsInfos;
	}

	public EffortsInfo addEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().add(effortsInfo);
		effortsInfo.setUserInfo(this);

		return effortsInfo;
	}

	public EffortsInfo removeEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().remove(effortsInfo);
		effortsInfo.setUserInfo(null);

		return effortsInfo;
	}

	public ManagerInfo getManagerInfo() {
		return this.managerInfo;
	}

	public void setManagerInfo(ManagerInfo managerInfo) {
		this.managerInfo = managerInfo;
	}

	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public RolesInfo getRolesInfo() {
		return this.rolesInfo;
	}

	public void setRolesInfo(RolesInfo rolesInfo) {
		this.rolesInfo = rolesInfo;
	}

}