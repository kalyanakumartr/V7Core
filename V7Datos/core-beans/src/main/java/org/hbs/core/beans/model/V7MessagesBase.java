package org.hbs.core.beans.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.util.EnumInterface;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class V7MessagesBase extends ProducersBase implements IMessages
{

	private static final long		serialVersionUID	= 7502995821299931151L;
	protected IConfiguration		configuration;
	protected Map<String, Object>	dataMap				= new LinkedHashMap<String, Object>();
	protected String				dataMapTemplateName;
	protected String				messageId;
	protected String				message				= null;
	protected String				messageName;
	protected String				subject				= "";
	protected EMedia				media;
	protected String[]				recipients;
	protected boolean				textHTML			= true;

	public V7MessagesBase()
	{
		super();
	}

	@JsonIgnore
	@Transient
	public String[] getRecipients()
	{
		return recipients;
	}

	public void setRecipients(String... recipients)
	{
		this.recipients = recipients;
	}

	@Transient
	public String generateVTLMessage() throws IOException
	{
		Velocity.init();
		VelocityContext context = new VelocityContext();

		for (String key : dataMap.keySet())
			context.put(key, dataMap.get(key));

		StringWriter writer = new StringWriter();

		Velocity.evaluate(context, writer, "Message" + messageId, message);

		String generateMessage = writer.toString();

		writer.close();

		return generateMessage;

	}

	@Transient
	public String generateVTLSubject() throws IOException
	{
		Velocity.init();
		VelocityContext context = new VelocityContext();

		for (String key : dataMap.keySet())
			context.put(key, dataMap.get(key));

		StringWriter writer = new StringWriter();

		Velocity.evaluate(context, writer, "Subject" + messageId, subject);

		String generateMessage = writer.toString();

		writer.close();

		return generateMessage;

	}

	@Transient
	public String getBusinessKey(String... combination)
	{
		return EKey.Auto();
	}

	@Transient
	public Map<String, Object> getDataMap()
	{
		return dataMap;
	}

	@Column(name = "textHTML")
	public boolean isTextHTML()
	{
		return textHTML;
	}

	public void setTextHTML(boolean textHTML)
	{
		this.textHTML = textHTML;
	}

	@Column(name = "dataMapTemplateName")
	public String getDataMapTemplateName()
	{
		return dataMapTemplateName;
	}

	@Id
	@Column(name = "messageId")
	public String getMessageId()
	{
		return messageId;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	@Override
	@Column(name = "message")
	public String getMessage()
	{
		return message;
	}

	@Column(name = "messageName")
	public String getMessageName()
	{
		return messageName;
	}

	@Column(name = "subject")
	public String getSubject()
	{
		return subject;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "media")
	public EMedia getMedia()
	{
		return media;
	}

	@Transient
	@Override
	public EnumInterface getTemplateName()
	{
		return new EnumInterface() {

			@Override
			public String name()
			{
				return messageName;
			}

			@Override
			public int ordinal()
			{
				return 0;
			}

		};
	}

	public void setDataMap(Map<String, Object> dataMap)
	{
		this.dataMap = dataMap;
	}

	public void setDataMapTemplateName(String dataMapTemplateName)
	{
		this.dataMapTemplateName = dataMapTemplateName;
	}

	@Override
	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMessageName(String messageName)
	{
		this.messageName = messageName;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	@Override
	public void setMedia(EMedia message)
	{
		this.media = message;
	}

	@Transient
	public IConfiguration getConfiguration()
	{
		return configuration;
	}

	public void setConfiguration(IConfiguration configuration)
	{
		this.configuration = configuration;
	}

}