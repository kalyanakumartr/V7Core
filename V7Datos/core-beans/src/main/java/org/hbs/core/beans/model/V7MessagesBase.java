package org.hbs.core.beans.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hbs.core.beans.model.channel.ConfigurationEmail;
import org.hbs.core.beans.model.channel.ConfigurationSMS;
import org.hbs.core.beans.model.channel.ConfigurationWeb;
import org.hbs.core.beans.model.channel.ConfigurationWhatsApp;
import org.hbs.core.security.resource.IPathBase.EMedia;
import org.hbs.core.security.resource.IPathBase.EMediaMode;
import org.hbs.core.util.EnumInterface;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class V7MessagesBase extends CommonDateAndStatusFields implements IMessages
{

	private static final long		serialVersionUID	= 7502995821299931151L;
	protected IConfiguration		configuration;
	protected Map<String, Object>	dataMap				= new LinkedHashMap<String, Object>();
	protected String				dataMapTemplateName;
	protected EMedia				media;
	protected String				message				= null;
	protected String				messageId;
	protected String				messageName;
	protected Producers				producer;
	protected String[]				recipients;
	protected String				subject				= "";
	protected boolean				textHTML			= true;

	public V7MessagesBase()
	{
		super();
	}

	@Transient
	public void generateConfigurationFromProducerProperty(EMediaMode mediaMode) throws ClassNotFoundException
	{
		String property = "";
		if (media == EMedia.Email)
			property = ConfigurationEmail.class.getCanonicalName();
		else if (media == EMedia.SMS)
			property = ConfigurationSMS.class.getCanonicalName();
		else if (media == EMedia.Web)
			property = ConfigurationWeb.class.getCanonicalName();
		else if (media == EMedia.WhatsApp)
			property = ConfigurationWhatsApp.class.getCanonicalName();

		this.configuration = producer.getProperty(media, mediaMode, property).getPropertyAsConfiguration();
		this.configuration.setProducerId(producer.getProducerId());

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
	public IConfiguration getConfiguration()
	{
		return configuration;
	}

	@Override
	@Transient
	@JsonIgnore
	public String getCountryTimeZone()
	{
		return null;
	}

	@Transient
	public Map<String, Object> getDataMap()
	{
		return dataMap;
	}

	@Column(name = "dataMapTemplateName")
	public String getDataMapTemplateName()
	{
		return dataMapTemplateName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "media")
	public EMedia getMedia()
	{
		return media;
	}

	@Override
	@Column(name = "message")
	public String getMessage()
	{
		return message;
	}

	@Id
	@Column(name = "messageId")
	public String getMessageId()
	{
		return messageId;
	}

	@Column(name = "messageName")
	public String getMessageName()
	{
		return messageName;
	}

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "producerId")
	@JsonIgnore
	public Producers getProducer()
	{
		return producer;
	}

	@JsonIgnore
	@Transient
	public String[] getRecipients()
	{
		return recipients;
	}

	@Column(name = "subject")
	public String getSubject()
	{
		return subject;
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

	@Column(name = "textHTML")
	public boolean isTextHTML()
	{
		return textHTML;
	}

	public void setConfiguration(IConfiguration configuration)
	{
		this.configuration = configuration;
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
	public void setMedia(EMedia message)
	{
		this.media = message;
	}

	@Override
	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public void setMessageName(String messageName)
	{
		this.messageName = messageName;
	}

	@Override
	public void setProducer(Producers producer)
	{
		this.producer = producer;
	}

	public void setRecipients(String... recipients)
	{
		this.recipients = recipients;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void setTextHTML(boolean textHTML)
	{
		this.textHTML = textHTML;
	}

}