package org.hbs.core.beans.model;

import java.util.Set;

import org.hbs.core.util.EnumInterface;

public interface IUsersBaseImpl extends IUsersBase
{
	public IUsersAttachments getAttachment(EnumInterface documentType);

	public Set<IUsersAddress> getAddressList();

	public Set<IUsersAttachments> getAttachmentList();

	public IUsersAddress getCommunicationAddress();

	public void setAddressList(Set<IUsersAddress> addressList);

	public void setAttachmentList(Set<IUsersAttachments> attachmentList);

	public void setCommunicationAddress(IUsersAddress communicationAddress);

}