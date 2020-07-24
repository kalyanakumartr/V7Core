package org.hbs.v7.beans.model;

import java.io.Serializable;
import java.util.Set;

public interface ICoreData extends Serializable
{

	public String getDataURN();

	public Set<DataAttachments> getAttachmentList();

	public void setDataURN(String dataURN);

	public void setAttachmentList(Set<DataAttachments> attachmentList);

}