package org.hbs.v7.reader.action.core;

import java.io.Serializable;

import org.hbs.core.util.CustomException;

public interface InBoxReader extends Serializable
{
	final String	MULTIPART_ALTERNATIVE	= "multipart/alternative";
	final String	MULTIPART_ENCRYPTED		= "multipart/encrypted";
	final String	MULTIPART_MIXED			= "multipart/mixed";
	final String	MULTIPART_RELATED		= "multipart/related";
	final String	MULTIPART_SIGNED		= "multipart/signed";

	public void readDataFromChannel() throws CustomException;

}
