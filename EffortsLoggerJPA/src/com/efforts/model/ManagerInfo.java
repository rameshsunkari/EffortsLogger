package com.efforts.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the MANAGER_INFO database table.
 * 
 */
@Entity
@Table(name = "MANAGER_INFO")
@NamedQueries({ @NamedQuery(name = ManagerInfo.GET_ALL_MANAGERS, query = "SELECT m FROM ManagerInfo m"),
	 @NamedQuery(name = ManagerInfo.GET_MANAGER_BYID, query = "SELECT m FROM ManagerInfo m WHERE m.id = :mgrId")})
public class ManagerInfo implements Serializable {
	
	public static final String GET_ALL_MANAGERS = "ManagerInfo.getAllManagers";
	public static final String GET_MANAGER_BYID = "ManagerInfo.getManagerById";
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, precision = 22)
	private long id;

	@Column(nullable = false, length = 100)
	private String fname;

	@Column(nullable = false, length = 100)
	private String lname;

	@Column(length = 100)
	private String mailid;

	// bi-directional many-to-one association to EffortsInfo
	@OneToMany(mappedBy = "managerInfo", fetch = FetchType.EAGER)
	private List<EffortsInfo> effortsInfos;

	// bi-directional many-to-one association to ProjectInfo
	@OneToMany(mappedBy = "managerInfo", fetch = FetchType.EAGER)
	private List<ProjectInfo> projectInfos;

	// bi-directional many-to-one association to UserInfo
	@OneToMany(mappedBy = "managerInfo", fetch = FetchType.EAGER)
	private List<UserInfo> userInfos;

	public ManagerInfo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
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

	public List<EffortsInfo> getEffortsInfos() {
		return this.effortsInfos;
	}

	public void setEffortsInfos(List<EffortsInfo> effortsInfos) {
		this.effortsInfos = effortsInfos;
	}

	public EffortsInfo addEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().add(effortsInfo);
		effortsInfo.setManagerInfo(this);

		return effortsInfo;
	}

	public EffortsInfo removeEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().remove(effortsInfo);
		effortsInfo.setManagerInfo(null);

		return effortsInfo;
	}

	public List<ProjectInfo> getProjectInfos() {
		return this.projectInfos;
	}

	public void setProjectInfos(List<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

	public ProjectInfo addProjectInfo(ProjectInfo projectInfo) {
		getProjectInfos().add(projectInfo);
		projectInfo.setManagerInfo(this);

		return projectInfo;
	}

	public ProjectInfo removeProjectInfo(ProjectInfo projectInfo) {
		getProjectInfos().remove(projectInfo);
		projectInfo.setManagerInfo(null);

		return projectInfo;
	}

	public List<UserInfo> getUserInfos() {
		return this.userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public UserInfo addUserInfo(UserInfo userInfo) {
		getUserInfos().add(userInfo);
		userInfo.setManagerInfo(this);

		return userInfo;
	}

	public UserInfo removeUserInfo(UserInfo userInfo) {
		getUserInfos().remove(userInfo);
		userInfo.setManagerInfo(null);

		return userInfo;
	}

}