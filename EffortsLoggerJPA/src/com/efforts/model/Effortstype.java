package com.efforts.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the EFFORTSTYPE database table.
 * 
 */
@Entity
@Table(name = "EFFORTSTYPE")
@NamedQueries({
		@NamedQuery(name = Effortstype.GET_ALL_EFFORTSTYPE, query = "SELECT e FROM Effortstype e"),
		@NamedQuery(name = Effortstype.GET_EFFORTSTYPE_BY_ID, query = "SELECT e FROM Effortstype e WHERE e.id=:effortsTypeId") })
public class Effortstype implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_EFFORTSTYPE = "Effortstype.getAllEffortsType";
	public static final String GET_EFFORTSTYPE_BY_ID = "Effortstype.getEffortsTypeById";

	@Id
	@GeneratedValue(generator="typeSeq")
    @SequenceGenerator(name="typeSeq",sequenceName="EFFORTS_TYPE_SEQ",schema="EFFORTS_LOGGER",allocationSize=1, initialValue=1)
	@Column(unique = true, nullable = false, precision = 22)
	private long id;

	@Column(nullable = false, length = 50)
	private String name;	
	
	// bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private RolesInfo roleInfo;

	// bi-directional many-to-one association to Effortssubtype
	@OneToMany(mappedBy = "effortstype", fetch = FetchType.EAGER)
	private List<Effortssubtype> effortssubtypes;

	// bi-directional many-to-one association to ProjectInfo
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID", nullable = false)
	private ProjectInfo projectInfo;

	// bi-directional many-to-one association to EffortsInfo
	@OneToMany(mappedBy = "effortstype", fetch = FetchType.EAGER)
	private List<EffortsInfo> effortsInfos;

	public Effortstype() {
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

	public List<Effortssubtype> getEffortssubtypes() {
		return this.effortssubtypes;
	}

	public void setEffortssubtypes(List<Effortssubtype> effortssubtypes) {
		this.effortssubtypes = effortssubtypes;
	}

	public Effortssubtype addEffortssubtype(Effortssubtype effortssubtype) {
		getEffortssubtypes().add(effortssubtype);
		effortssubtype.setEffortstype(this);

		return effortssubtype;
	}

	public Effortssubtype removeEffortssubtype(Effortssubtype effortssubtype) {
		getEffortssubtypes().remove(effortssubtype);
		effortssubtype.setEffortstype(null);

		return effortssubtype;
	}

	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public List<EffortsInfo> getEffortsInfos() {
		return this.effortsInfos;
	}

	public void setEffortsInfos(List<EffortsInfo> effortsInfos) {
		this.effortsInfos = effortsInfos;
	}

	public EffortsInfo addEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().add(effortsInfo);
		effortsInfo.setEffortstype(this);

		return effortsInfo;
	}

	public EffortsInfo removeEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().remove(effortsInfo);
		effortsInfo.setEffortstype(null);

		return effortsInfo;
	}

	public RolesInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RolesInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

}