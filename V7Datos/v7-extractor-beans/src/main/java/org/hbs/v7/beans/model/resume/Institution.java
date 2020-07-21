package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hbs.core.beans.model.ProducersBase;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "institution")
public class Institution extends ProducersBase implements ICRUDBean, EBusinessKey
{
	public enum EStarRating implements EnumInterface
	{
		_1_Star, _2_Star, _3_Star, _4_Star, _5_Star, Not_Rated;
	}

	private static final long	serialVersionUID	= 2739600479449750394L;

	private String				autoId;
	private String				course;
	private int					creditCost			= 1;
	private String				institutionName;
	private EStarRating			rating				= EStarRating.Not_Rated;

	public Institution()
	{
		super();
		this.autoId = getBusinessKey();
	}

	@Id
	@Column(name = "autoId")
	public String getAutoId()
	{
		return autoId;
	}

	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Column(name = "course")
	public String getCourse()
	{
		return course;
	}

	@Column(name = "creditCost")
	public int getCreditCost()
	{
		return creditCost;
	}

	@Column(name = "institutionName")
	public String getInstitutionName()
	{
		return institutionName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "rating")
	public EStarRating getRating()
	{
		return rating;
	}

	public void setAutoId(String autoId)
	{
		this.autoId = autoId;
	}

	public void setCourse(String course)
	{
		this.course = course;
	}

	public void setCreditCost(int creditCost)
	{
		this.creditCost = creditCost;
	}

	public void setInstitutionName(String institutionName)
	{
		this.institutionName = institutionName;
	}

	public void setRating(EStarRating rating)
	{
		this.rating = rating;
	}
}
