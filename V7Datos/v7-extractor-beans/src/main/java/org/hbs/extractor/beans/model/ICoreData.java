package org.hbs.extractor.beans.model;

import java.io.Serializable;
import java.util.Set;

public interface ICoreData extends Serializable
{

	public String get_URN();

	public Set<DataAttachments> getAttachmentList();

	public void set_URN(String _URN);

	public void setAttachmentList(Set<DataAttachments> attachmentList);

}