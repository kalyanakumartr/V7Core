package org.hbs.core.beans;

import java.util.Map;

import org.hbs.core.beans.model.IConfiguration;
import org.hbs.core.beans.model.channel.ChannelMessages;
import org.hbs.core.beans.model.channel.IChannelMessages;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MessageFormBean extends APIStatus
{

	private static final long			serialVersionUID	= 2490152380120347818L;

	public String						messageType;

	public String						bodyText;

	public String						subjectText;

	public MultipartFile[]				multipartFiles;

	public Map<String, SendMessageBean>	dataMap;

	public String						producerId;

	public IChannelMessages				message;

	public MessageFormBean()
	{
		super();
	}

	public MessageFormBean(IChannelMessages message)
	{
		super();
		this.message = message;
	}

	@Transient
	public void constructBaseMessage(Authentication auth, IConfiguration configuration)
	{
		this.message = new ChannelMessages();
		this.message.setMessage(bodyText);
		this.message.setSubject(subjectText);
		this.message.setConfiguration(configuration);
	}

	public SendMessageBean get(String key)
	{
		return this.dataMap.get(key);
	}

	@Override
	public void clearForm()
	{
		// TODO Auto-generated method stub

	}
}
