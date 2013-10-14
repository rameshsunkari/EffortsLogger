package com.efforts.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the ROLES_INFO database table.
 * 
 */
@Entity
@Table(name = "ROLES_INFO")
@NamedQueries({ @NamedQuery(name = RolesInfo.GET_ALL_ROLES, query = "SELECT r FROM RolesInfo r"),
	@NamedQuery(name = RolesInfo.GET_ROLE_BY_ID, query = "SELECT r FROM RolesInfo r WHERE r.id = :roleId")})
public class RolesInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String GET_ALL_ROLES = "RolesInfo.getAllRoles";
	public final static String GET_ROLE_BY_ID = "RolesInfo.getRoleById";

	@Id
	@GeneratedValue(generator="roleSeq")
    @SequenceGenerator(name="roleSeq",sequenceName="ROLE_SEQ",schema="EFFORTS_LOGGER",allocationSize=1, initialValue=1)
	@Column(name="ROLEID",unique = true, nullable = false, precision = 22)
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	// bi-directional many-to-one association to UserInfo
	@OneToMany(mappedBy = "rolesInfo", fetch = FetchType.EAGER)
	private List<UserInfo> userInfos;
	
	// bi-directional many-to-one association to Roles
	@OneToMany(mappedBy = "roleInfo", fetch = FetchType.EAGER)
	private List<Effortstype> effortstypes;

	public RolesInfo() {
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

	public List<UserInfo> getUserInfos() {
		return this.userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public UserInfo addUserInfo(UserInfo userInfo) {
		getUserInfos().add(userInfo);
		userInfo.setRolesInfo(this);

		return userInfo;
	}

	public UserInfo removeUserInfo(UserInfo userInfo) {
		getUserInfos().remove(userInfo);
		userInfo.setRolesInfo(null);

		return userInfo;
	}

	public List<Effortstype> getEffortstypes() {
		return effortstypes;
	}

	public void setEffortstypes(List<Effortstype> effortstypes) {
		this.effortstypes = effortstypes;
	}

}