package com.efforts.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the EFFORTS_INFO database table.
 * 
 */
@Entity
@Table(name = "EFFORTS_INFO")
@NamedQueries({
		@NamedQuery(name = EffortsInfo.GET_ALL_EFFORTS, query = "SELECT e FROM EffortsInfo e"),
		@NamedQuery(name = EffortsInfo.GET_ALL_EMP_EFFORTS, query = "SELECT e FROM EffortsInfo e WHERE e.userInfo.empid =:empId"),
		@NamedQuery(name = EffortsInfo.GET_MGR_ALL_EMP_EFFORTS, query = "SELECT e FROM EffortsInfo e WHERE e.managerInfo.id = :mgrId"),
		@NamedQuery(name = EffortsInfo.GET_EFFORTINFO_BY_ID, query = "SELECT e FROM EffortsInfo e WHERE e.id = :effortsInfoId") })
public class EffortsInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_EFFORTS = "EffortsInfo.getAllEfforts";
	public static final String GET_EFFORTINFO_BY_ID = "EffortsInfo.getEffortInfoById";
	public static final String GET_ALL_EMP_EFFORTS = "EffortsInfo.getAllEmpEfforts";
	public static final String GET_MGR_ALL_EMP_EFFORTS = "EffortsInfo.getEmpOfManagerEfforts";

	@Id
	@GeneratedValue(generator="effortsSeq")
    @SequenceGenerator(name="effortsSeq",sequenceName="EFFORTS_SEQ",schema="EFFORTS_LOGGER",allocationSize=1, initialValue=1)
	@Column(unique = true, nullable = false, precision = 22)
	private Long id;

	@Column(length = 256)
	private String description;

	@Temporal(TemporalType.DATE)
	private Date effortdate;

	@Column(precision = 22)
	private BigDecimal points;

	@Column(length = 256)
	private String remarks;

	@Temporal(TemporalType.DATE)
	@Column(name = "submitted_Date")
	private Date submitted_Date;

	// bi-directional many-to-one association to Effortssubtype
	@ManyToOne
	@JoinColumn(name = "EFFORTSSUBTYPE_ID", nullable = false)
	private Effortssubtype effortssubtype;

	// bi-directional many-to-one association to Effortstype
	@ManyToOne
	@JoinColumn(name = "EFFORTSTYPE_ID", nullable = false)
	private Effortstype effortstype;

	// bi-directional many-to-one association to ManagerInfo
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID", nullable = false)
	private ManagerInfo managerInfo;

	// bi-directional many-to-one association to ProjectInfo
	@ManyToOne
	@JoinColumn(name = "PROJECT_INFO_ID", nullable = false)
	private ProjectInfo projectInfo;

	// bi-directional many-to-one association to UserInfo
	@ManyToOne
	@JoinColumn(name = "USER_INFO_EMPID", nullable = false)
	private UserInfo userInfo;

	public EffortsInfo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffortdate() {
		return this.effortdate;
	}

	public void setEffortdate(Date effortdate) {
		this.effortdate = effortdate;
	}

	public BigDecimal getPoints() {
		return this.points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSubmitted_Date() {
		return this.submitted_Date;
	}

	public void setSubmitted_Date(Date submitted_Date) {
		this.submitted_Date = submitted_Date;
	}

	public Effortssubtype getEffortssubtype() {
		return this.effortssubtype;
	}

	public void setEffortssubtype(Effortssubtype effortssubtype) {
		this.effortssubtype = effortssubtype;
	}

	public Effortstype getEffortstype() {
		return this.effortstype;
	}

	public void setEffortstype(Effortstype effortstype) {
		this.effortstype = effortstype;
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

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}