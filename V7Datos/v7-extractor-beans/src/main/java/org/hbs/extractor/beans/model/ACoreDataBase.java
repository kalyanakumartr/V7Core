package org.hbs.extractor.beans.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hbs.core.beans.model.CommonBeanFields;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@MappedSuperclass
public abstract class ACoreDataBase extends CommonBeanFields implements ICoreData
{
	private static final long		serialVersionUID	= 5357766946700135577L;
	protected String				_URN;
	protected Set<DataAttachments>	attachmentList		= new LinkedHashSet<DataAttachments>(0);

	public ACoreDataBase()
	{
		super();
	}

	@Override
	@Id
	@Column(name = "_URN")
	public String get_URN()
	{
		return _URN;
	}

	@Override
	@OneToMany(targetEntity = DataAttachments.class, fetch = FetchType.LAZY, mappedBy = "_URN", cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	public Set<DataAttachments> getAttachmentList()
	{
		return attachmentList;
	}

	@Override
	public void set_URN(String _URN)
	{
		this._URN = _URN;
	}

	@Override
	public void setAttachmentList(Set<DataAttachments> attachmentList)
	{
		this.attachmentList = attachmentList;
	}

}