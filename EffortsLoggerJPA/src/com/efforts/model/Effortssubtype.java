package com.efforts.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the EFFORTSSUBTYPE database table.
 * 
 */
@Entity
@Table(name = "EFFORTSSUBTYPE")
@NamedQueries({
		@NamedQuery(name = Effortssubtype.GET_ALL_EFFORTSSUBTYPE, query = "SELECT e FROM Effortssubtype e"),
		@NamedQuery(name = Effortssubtype.GET_EFFORTSSUBTYPE_BY_ID, query = "SELECT e FROM Effortssubtype e WHERE e.id =:effortsSubTypeId") })
public class Effortssubtype implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_EFFORTSSUBTYPE = "Effortssubtype.getAllEffortsSubType";
	public static final String GET_EFFORTSSUBTYPE_BY_ID = "Effortssubtype.getEffortsSubTypeById";

	@Id
	@GeneratedValue(generator="subTypeSeq")
    @SequenceGenerator(name="subTypeSeq",sequenceName="EFFORTS_SUBTYPE_SEQ",schema="EFFORTS_LOGGER",allocationSize=1, initialValue=1)
	@Column(unique = true, nullable = false, precision = 22)
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	// bi-directional many-to-one association to Effortstype
	@ManyToOne
	@JoinColumn(name = "EFFORTSTYPE_ID", nullable = false)
	private Effortstype effortstype;

	// bi-directional many-to-one association to EffortsInfo
	@OneToMany(mappedBy = "effortssubtype", fetch = FetchType.EAGER)
	private List<EffortsInfo> effortsInfos;

	public Effortssubtype() {
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

	public Effortstype getEffortstype() {
		return this.effortstype;
	}

	public void setEffortstype(Effortstype effortstype) {
		this.effortstype = effortstype;
	}

	public List<EffortsInfo> getEffortsInfos() {
		return this.effortsInfos;
	}

	public void setEffortsInfos(List<EffortsInfo> effortsInfos) {
		this.effortsInfos = effortsInfos;
	}

	public EffortsInfo addEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().add(effortsInfo);
		effortsInfo.setEffortssubtype(this);

		return effortsInfo;
	}

	public EffortsInfo removeEffortsInfo(EffortsInfo effortsInfo) {
		getEffortsInfos().remove(effortsInfo);
		effortsInfo.setEffortssubtype(null);

		return effortsInfo;
	}

}