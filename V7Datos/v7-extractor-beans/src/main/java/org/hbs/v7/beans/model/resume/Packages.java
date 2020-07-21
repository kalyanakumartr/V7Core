package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.CreatedModifiedUsers;
import org.hbs.core.beans.model.ProducersBase;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "resume_packages")
public class Packages extends ProducersBase implements EBusinessKey, ICRUDBean
{
	public enum ECurrency implements EnumInterface
	{
		EUR, INR, SGD, USD;
	}

	public enum EPackageHierarchy implements EnumInterface
	{
		Level1, Level2, Level3, Level4, Level5, Level6, Level7, Level8, Level9, Level10;
	}

	private static final long		serialVersionUID	= -5152921980819763325L;

	protected double				actualCost			= 0.00;
	protected CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();
	protected boolean				carryOver;
	protected int					carryOverPercent;
	protected int					creditReduceRate;
	protected int					credits;
	protected EPackageHierarchy		hierarchy			= EPackageHierarchy.Level1;
	protected ECurrency				currency			= ECurrency.USD;
	protected String				description;
	protected int					discountPercent		= 0;
	protected int					durationInDays;
	protected String				name;
	protected String				packageId;

	public Packages()
	{
		super();
		this.packageId = getBusinessKey();
	}

	public Packages(String packageId)
	{
		super();
		this.packageId = packageId;
	}

	@Column(name = "carryOverPercent")
	public int getCarryOverPercent()
	{
		return carryOverPercent;
	}

	public void setCarryOverPercent(int carryOverPercent)
	{
		this.carryOverPercent = carryOverPercent;
	}

	@Column(name = "hierarchy")
	public EPackageHierarchy getHierarchy()
	{
		return hierarchy;
	}

	public void setHierarchy(EPackageHierarchy hierarchy)
	{
		this.hierarchy = hierarchy;
	}

	@Column(name = "actualCost")
	public double getActualCost()
	{
		return actualCost;
	}

	@Transient
	@Override
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Embedded
	public CreatedModifiedUsers getByUser()
	{
		return byUser;
	}

	@Column(name = "creditReduceRate")
	public int getCreditReduceRate()
	{
		return creditReduceRate;
	}

	@Column(name = "credits")
	public int getCredits()
	{
		return credits;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	public ECurrency getCurrency()
	{
		return currency;
	}

	@Column(name = "description")
	public String getDescription()
	{
		return description;
	}

	@Column(name = "discountPercent")
	public int getDiscountPercent()
	{
		return discountPercent;
	}

	@Column(name = "durationInDays")
	public int getDurationInDays()
	{
		return durationInDays;
	}

	@Column(name = "name")
	public String getName()
	{
		return name;
	}

	@Id
	@Column(name = "packageId")
	public String getPackageId()
	{
		return packageId;
	}

	@Column(name = "carryOver")
	public boolean isCarryOver()
	{
		return carryOver;
	}

	public void setActualCost(double actualCost)
	{
		this.actualCost = actualCost;
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	public void setCarryOver(boolean carryOver)
	{
		this.carryOver = carryOver;
	}

	public void setCreditReduceRate(int creditReduceRate)
	{
		this.creditReduceRate = creditReduceRate;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public void setCurrency(ECurrency currency)
	{
		this.currency = currency;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setDiscountPercent(int discountPercent)
	{
		this.discountPercent = discountPercent;
	}

	public void setDurationInDays(int durationInDays)
	{
		this.durationInDays = durationInDays;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPackageId(String packageId)
	{
		this.packageId = packageId;
	}

}
