package com.efforts.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PROJECT_ROLE_INFO database table.
 * 
 */
@Entity
@Table(name="PROJECT_ROLE_INFO")
@NamedQuery(name="ProjectRoleInfo.findAll", query="SELECT p FROM ProjectRoleInfo p")
public class ProjectRoleInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, precision=22)
	private long id;

	@Column(name="PROJECT_INFO_ID", nullable=false, precision=22)
	private BigDecimal projectInfoId;

	@Column(name="ROLES_INFO_ROLEID", nullable=false, precision=22)
	private BigDecimal rolesInfoRoleid;

	public ProjectRoleInfo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getProjectInfoId() {
		return this.projectInfoId;
	}

	public void setProjectInfoId(BigDecimal projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

	public BigDecimal getRolesInfoRoleid() {
		return this.rolesInfoRoleid;
	}

	public void setRolesInfoRoleid(BigDecimal rolesInfoRoleid) {
		this.rolesInfoRoleid = rolesInfoRoleid;
	}

}