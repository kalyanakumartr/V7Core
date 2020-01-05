package org.hbs.sender;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.hbs.core.beans.MessageFormBean;
import org.hbs.core.beans.SMSResponseBean;
import org.hbs.core.beans.model.IMessages.EMessageStatus;
import org.hbs.core.beans.model.channel.ConfigurationSMS;
import org.hbs.core.beans.model.clickatell.MessageContent;
import org.hbs.core.beans.model.clickatell.MessageResponse;
import org.hbs.core.beans.model.clickatell.Messages;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

@Component
public class SMSGatewayClickatell implements ISMSGateway
{
	private static final long	serialVersionUID	= 161520382322745890L;
	private final Logger		logger				= LoggerFactory.getLogger(SMSGatewayClickatell.class);

	public SMSResponseBean sendSMSMessages(MessageFormBean messageFormBean)
	{
		try
		{
			ConfigurationSMS configuration = (ConfigurationSMS) messageFormBean.message.getConfiguration();
			HttpHeaders headers = new HttpHeaders();
			headers.add(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			headers.add(AUTHORIZATION, configuration.getAuthToken());
			headers.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

			MessageContent messageContent = new MessageContent();
			messageContent.setTo(messageFormBean.message.getRecipients());
			messageContent.setContent(messageFormBean.message.getMessage());

			// DoNOT change structure.
			ResponseEntity<MessageResponse> response = new RestTemplate().exchange(configuration.getRequestURL(), HttpMethod.POST, new HttpEntity<MessageContent>(messageContent, headers),
					new ParameterizedTypeReference<MessageResponse>() {
					});

			if (CommonValidator.isNotNullNotEmpty(response))
			{
				switch ( response.getStatusCode() )
				{
					case OK :
					case ACCEPTED :
					{
						SMSResponseBean smsResponse = new SMSResponseBean();
						for (Messages ctlMessage : response.getBody().getMessages())
						{
							smsResponse.setRepsonseId(ctlMessage.getApiMessageId());
							smsResponse.setStatus(EMessageStatus.Completed);
							return smsResponse;
						}
					}
					default :
					{
						logger.error("Unacceptable Response Status Code : " + response.getStatusCode().name());
						logger.error("SMSResponseBean Response Status Object : " + new Gson().toJson(response));
						throw new CustomException("Unacceptable Response Status Code : " + response.getStatusCode().name());
					}

				}
			}
		}
		catch (Exception excep)
		{
			StringWriter logMessageWriter = new StringWriter();
			excep.printStackTrace(new PrintWriter(logMessageWriter));
			logger.error(logMessageWriter.toString());
		}
		return new SMSResponseBean(EMessageStatus.Error);
	}

	public String callBackResponse(String responseUrl, String token, String messageId)
	{
		try
		{
			HttpHeaders headers = new HttpHeaders();

			headers.add(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			headers.add(AUTHORIZATION, token);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(responseUrl);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("templateId", messageId);
			builder.queryParams(map);

			HttpEntity<?> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<Map<String, String>> response = new RestTemplate().exchange(builder.build().toUri(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Map<String, String>>() {
			});

			if (CommonValidator.isNotNullNotEmpty(response))
			{
				switch ( response.getStatusCode() )
				{
					case OK :
					case ACCEPTED :
						return response.getBody().get("status");
					default :
						throw new CustomException("Unacceptable Response Status Code : " + response.getStatusCode().name());

				}
			}
		}
		catch (Exception excep)
		{
			StringWriter logMessageWriter = new StringWriter();
			excep.printStackTrace(new PrintWriter(logMessageWriter));
			logger.error(logMessageWriter.toString());
		}
		return EReturn.Failure.name();
	}
}
