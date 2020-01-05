package org.hbs.sender.bo;

import org.hbs.core.dao.MessageDao;
import org.hbs.core.dao.SequenceDao;
import org.hbs.core.dao.UserDao;
import org.hbs.core.security.resource.IPath;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseSenderBoImpl implements IPath
{

	private static final long	serialVersionUID	= 833657530598194674L;

	@Autowired
	MessageDao					messageDao;

	@Autowired
	ConfigurationBo				configurationBo;

	@Autowired
	SequenceDao					sequenceDao;

	@Autowired
	UserDao						userDao;

	public BaseSenderBoImpl()
	{
		super();
	}

}