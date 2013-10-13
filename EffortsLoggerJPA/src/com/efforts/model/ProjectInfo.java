package com.efforts.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the PROJECT_INFO database table.
 * 
 */
@Entity
@Table(name = "PROJECT_INFO")
@NamedQueries({
		@NamedQuery(name = ProjectInfo.GET_ALL_PROJECTS, query = "SELECT p FROM ProjectInfo p"),
		@NamedQuery(name = ProjectInfo.GET_PROJECT_BY_ID, query = "SELECT p FROM ProjectInfo p WHERE p.id =:projectId") })
public class ProjectInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_PROJECTS = "ProjectInfo.getAllProjects";
	public static final String GET_PROJECT_BY_ID = "ProjectInfo.getProjectById";

	@Id
	@GeneratedValue(generator="projectSeq")
    @SequenceGenerator(name="projectSeq",sequenceName="PROJECT_SEQ",schema="EFFORTS_LOGGER",allocationSize=1, initialValue=1)
	@Column(unique = true, nullable = false, precision = 22)
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	// bi-directional many-to-one association to Effortstype
	@OneToMany(mappedBy = "projectInfo", fetch = FetchType.EAGER)
	private List<Effortstype> effortstypes;

	// bi-directional many-to-one association to EffortsInfo
	@OneToMany(mappedBy = "projectInfo", fetch = FetchType.EAGER)
	private List<EffortsInfo> effortsInfos;

	// bi-directional many-to-one association to ManagerInfo
	@ManyToOne
	@JoinColumn(name = "MANAGER_INFO_ID", nullable = false)
	private ManagerInfo managerInfo;

	// bi-directional many-to-one association to UserInfo
	@OneToMany(mappedBy = "projectInfo", fetch = FetchType.EAGER)
	private List<UserInfo> userInfos;

	public ProjectInfo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Effortstype> getEffortstypes() {
		return this.effortstypes;
	}

	public void setEffortstypes(List<Effortstype> effortstypes) {
		this.effortstypes = effortstypes;
	}

	public Effortstype addEffortstype(Effortstype effortstype) {
		getEffortstypes().add(effortstype);
		effortstype.setProjectInfo(this);

		return effortstype;
	}

	public Effortstype removeEffortstype(Effortstype effortstype) {
		getEffortstypes().remove(effortstype);
		effortstype.setProjectInfo(null);

		return effortstype;
	}

	public List<EffortsInfo> getEffortsInfos() {
		return this.effortsInfos;
	}

	public void setEffortsInfos(List<EffortsInfo> effortsInfos) {
		this.effortsInfos = effortsInfos;
	}

	public EffortsInfo addEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().add(effortsInfo);
		effortsInfo.setProjectInfo(this);

		return effortsInfo;
	}

	public EffortsInfo removeEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().remove(effortsInfo);
		effortsInfo.setProjectInfo(null);

		return effortsInfo;
	}

	public ManagerInfo getManagerInfo() {
		return this.managerInfo;
	}

	public void setManagerInfo(ManagerInfo managerInfo) {
		this.managerInfo = managerInfo;
	}

	public List<UserInfo> getUserInfos() {
		return this.userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public UserInfo addUserInfo(UserInfo userInfo) {
		getUserInfos().add(userInfo);
		userInfo.setProjectInfo(this);

		return userInfo;
	}

	public UserInfo removeUserInfo(UserInfo userInfo) {
		getUserInfos().remove(userInfo);
		userInfo.setProjectInfo(null);

		return userInfo;
	}

}