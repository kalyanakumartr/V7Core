package org.hbs.v7.beans.model.resume;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hbs.core.beans.model.IUsersBase.EGender;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.ICRUDBean;

@Embeddable
public class _PersonalInfo implements ICRUDBean
{
	public enum EMartial implements EnumInterface
	{
		Divorced, Married, NoToSpecify, Seperated, UnMarried, Widower
	}

	protected static final long	serialVersionUID	= -6322736437714159489L;

	protected String			name;
	protected int				age;
	protected String			dateOfBirth;
	protected String			dateOfYear;
	protected String			fatherName;
	protected String			gaurdianName;
	protected String			motherName;
	protected EGender			gender				= EGender.NoToSpecify;
	protected EMartial			martial				= EMartial.NoToSpecify;
	protected String			nationality;
	protected String			passportNo;
	protected String			_UID;										// Social Security No /
																			// Aadhar etc

	public _PersonalInfo()
	{
		super();
	}

	public _PersonalInfo(String name, int age, String dateOfBirth, String dateOfYear, String fatherName, String gaurdianName, String motherName, EGender gender, EMartial martial, String nationality,
			String passportNo)
	{
		super();
		this.name = name;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.dateOfYear = dateOfYear;
		this.fatherName = fatherName;
		this.gaurdianName = gaurdianName;
		this.motherName = motherName;
		this.gender = gender;
		this.martial = martial;
		this.nationality = nationality;
		this.passportNo = passportNo;
	}

	@Column(name = "age")
	public int getAge()
	{
		return age;
	}

	@Column(name = "dateOfBirth")
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	@Column(name = "dateOfYear")
	public String getDateOfYear()
	{
		return dateOfYear;
	}

	@Column(name = "fatherName")
	public String getFatherName()
	{
		return fatherName;
	}

	@Column(name = "gaurdianName")
	public String getGaurdianName()
	{
		return gaurdianName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	public EGender getGender()
	{
		return gender;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "martial")
	public EMartial getMartial()
	{
		return martial;
	}

	@Column(name = "motherName")
	public String getMotherName()
	{
		return motherName;
	}

	@Column(name = "name")
	public String getName()
	{
		return name;
	}

	@Column(name = "nationality")
	public String getNationality()
	{
		return nationality;
	}

	@Column(name = "passportNo")
	public String getPassportNo()
	{
		return passportNo;
	}

	@Column(name = "uid")
	public String get_UID()
	{
		return _UID;
	}

	public void set_UID(String _UID)
	{
		this._UID = _UID;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfYear(String dateOfYear)
	{
		this.dateOfYear = dateOfYear;
	}

	public void setFatherName(String fatherName)
	{
		this.fatherName = fatherName;
	}

	public void setGaurdianName(String gaurdianName)
	{
		this.gaurdianName = gaurdianName;
	}

	public void setGender(EGender gender)
	{
		this.gender = gender;
	}

	public void setMartial(EMartial martial)
	{
		this.martial = martial;
	}

	public void setMotherName(String motherName)
	{
		this.motherName = motherName;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}

	public void setPassportNo(String passportNo)
	{
		this.passportNo = passportNo;
	}

}
