package org.hbs.v7.beans.model.resume;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.ICRUDBean;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "resume_data")
public class ResumeData implements ICRUDBean, EBusinessKey
{
	private static final long				serialVersionUID	= -4292683338585791631L;
	protected String						dataId;
	protected Resume						resume;
	protected _PersonalInfo					personal;
	protected _SkillsAndCost				skillsCost;
	protected Set<_Address>					addressList			= new LinkedHashSet<_Address>(0);
	protected Set<_Media>					mediaList			= new LinkedHashSet<_Media>(0);
	protected Set<_Qualification>			qualificationList	= new LinkedHashSet<_Qualification>(0);
	protected Set<_ProfessionalExperience>	professionalList	= new LinkedHashSet<_ProfessionalExperience>(0);

	public ResumeData()
	{
		super();
		this.dataId = getBusinessKey();
	}

	@OneToMany(targetEntity = _Address.class, fetch = FetchType.EAGER, mappedBy = "resumeData", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	public Set<_Address> getAddressList()
	{
		return addressList;
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Id
	@Column(name = "dataId")
	public String getDataId()
	{
		return dataId;
	}

	@OneToMany(targetEntity = _Media.class, fetch = FetchType.EAGER, mappedBy = "resumeData", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	public Set<_Media> getMediaList()
	{
		return mediaList;
	}

	@Embedded
	public _PersonalInfo getPersonal()
	{
		return personal;
	}

	@OneToMany(targetEntity = _ProfessionalExperience.class, fetch = FetchType.EAGER, mappedBy = "resumeData", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	public Set<_ProfessionalExperience> getProfessionalList()
	{
		return professionalList;
	}

	@OneToMany(targetEntity = _Qualification.class, fetch = FetchType.EAGER, mappedBy = "resumeData", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	public Set<_Qualification> getQualificationList()
	{
		return qualificationList;
	}

	@ManyToOne(targetEntity = Resume.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "resumeURN")
	public Resume getResume()
	{
		return resume;
	}

	@Embedded
	public _SkillsAndCost getSkillsCost()
	{
		return skillsCost;
	}

	public void setAddressList(_Address... addresses)
	{
		if (CommonValidator.isArrayFirstNotNull(addresses))
			this.addressList = new LinkedHashSet<>(Arrays.asList(addresses));
	}

	public void setAddressList(Set<_Address> addressList)
	{
		this.addressList = addressList;
	}

	public void setDataId(String dataId)
	{
		this.dataId = dataId;
	}

	public void setMediaList(Set<_Media> mediaList)
	{
		this.mediaList = mediaList;
	}

	public void setPersonal(_PersonalInfo personal)
	{
		this.personal = personal;
	}

	public void setProfessionalList(Set<_ProfessionalExperience> professionalList)
	{
		this.professionalList = professionalList;
	}

	@SuppressWarnings("unchecked")
	public void setQualificationList(_Qualification... qualifications)
	{
		if (CommonValidator.isArrayFirstNotNull(qualifications))
			this.qualificationList = new LinkedHashSet<>(Arrays.asList(qualifications));
	}

	public void setQualificationList(Set<_Qualification> qualificationList)
	{
		this.qualificationList = qualificationList;
	}

	public void setResume(Resume resume)
	{
		this.resume = resume;
	}

	public void setSkillsCost(_SkillsAndCost skillsCost)
	{
		this.skillsCost = skillsCost;
	}

	public void setMediaList(_Media... medias)
	{
		if (CommonValidator.isArrayFirstNotNull(medias))
			this.mediaList = new LinkedHashSet<>(Arrays.asList(medias));
	}

	@SuppressWarnings("unchecked")
	public void setProfessionalList(_ProfessionalExperience... professional)
	{
		if (CommonValidator.isArrayFirstNotNull(professional))
			this.professionalList = new LinkedHashSet<>(Arrays.asList(professional));
		
	}

	
}
