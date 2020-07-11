package org.hbs.extractor.beans.model.resume;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hbs.core.beans.model.CreatedModifiedUsers;
import org.hbs.core.beans.model.ProducersBase;
import org.hbs.core.util.EBusinessKey;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "resume_credits")
public class Credits extends ProducersBase implements EBusinessKey, ICRUDBean
{

	public enum ECreditStatus implements EnumInterface
	{
		Approved, Expired, Pending;
	}

	private static final long		serialVersionUID	= -4339173762594719178L;

	protected int					availableCredits;
	protected CreatedModifiedUsers	byUser				= new CreatedModifiedUsers();
	protected String				creditId;
	protected ECreditStatus			creditStatus		= ECreditStatus.Pending;
	protected Packages				packages;
	protected Timestamp				purchaseEndDate;
	protected Timestamp				purchaseStartDate;
	protected double				purchaseValue;

	public Credits()
	{
		super();
	}

	public Credits(String packageId)
	{
		super();
		this.creditId = getBusinessKey();
		this.packages = new Packages(packageId);
	}

	public Credits(String creditId, Packages packages, int availableCredits, Timestamp purchaseStartDate, Timestamp purchaseEndDate, double purchaseValue, ECreditStatus creditStatus,
			CreatedModifiedUsers byUser)
	{
		super();
		this.creditId = creditId;
		this.packages = packages;
		this.availableCredits = availableCredits;
		this.purchaseStartDate = purchaseStartDate;
		this.purchaseEndDate = purchaseEndDate;
		this.purchaseValue = purchaseValue;
		this.creditStatus = creditStatus;
		this.byUser = byUser;
	}

	@Column(name = "availableCredits")
	public int getAvailableCredits()
	{
		return availableCredits;
	}

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

	@Id
	@Column(name = "creditId")
	public String getCreditId()
	{
		return creditId;
	}

	@Column(name = "creditStatus")
	public ECreditStatus getCreditStatus()
	{
		return creditStatus;
	}

	@ManyToOne(targetEntity = Packages.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "packageId", nullable = false)
	public Packages getPackages()
	{
		return packages;
	}

	@Column(name = "purchaseEndDate")
	public Timestamp getPurchaseEndDate()
	{
		return purchaseEndDate;
	}

	@Column(name = "purchaseStartDate")
	public Timestamp getPurchaseStartDate()
	{
		return purchaseStartDate;
	}

	@Column(name = "purchaseValue")
	public double getPurchaseValue()
	{
		return purchaseValue;
	}

	public void reduceCreditByScheme()
	{
		if (creditStatus == ECreditStatus.Approved && this.availableCredits >= this.packages.getCreditReduceRate())
		{
			this.availableCredits = (this.availableCredits - this.packages.getCreditReduceRate());
		}
	}

	public void setAvailableCredits(int availableCredits)
	{
		this.availableCredits = availableCredits;
	}

	public void setByUser(CreatedModifiedUsers byUser)
	{
		this.byUser = byUser;
	}

	public void setCreditId(String creditId)
	{
		this.creditId = creditId;
	}

	public void setCreditStatus(ECreditStatus creditStatus)
	{
		this.creditStatus = creditStatus;
	}

	public void setPackages(Packages packages)
	{
		this.packages = packages;
	}

	public void setPurchaseEndDate(Timestamp purchaseEndDate)
	{
		this.purchaseEndDate = purchaseEndDate;
	}

	public void setPurchaseStartDate(Timestamp purchaseStartDate)
	{
		this.purchaseStartDate = purchaseStartDate;
	}

	public void setPurchaseValue(double purchaseValue)
	{
		this.purchaseValue = purchaseValue;
	}

	@Transient
	public void update_EndDate_Credits_Status(int earlierCredits)
	{
		this.purchaseEndDate = new Timestamp(this.purchaseStartDate.getTime() * this.packages.durationInDays);
		this.availableCredits = this.packages.getCredits();

		if (this.packages.isCarryOver())
			this.availableCredits = this.availableCredits + earlierCredits;

		if (System.currentTimeMillis() - this.purchaseStartDate.getTime() > 0)
			creditStatus = ECreditStatus.Approved;

	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		if (this.byUser.getCreatedUser() != null && this.byUser.getCreatedUser().getCountry().getCountry() != null && this.byUser.getModifiedUser() == null)
		{
			return this.byUser.getCreatedUser().getCountry().getCountry();
		}
		else if (this.byUser.getModifiedUser().getCountry().getCountry() != null)
		{
			return this.byUser.getModifiedUser().getCountry().getCountry();
		}
		return null;
	}

}
