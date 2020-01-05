package org.hbs.core.admin;

import java.util.List;

import org.hbs.core.admin.bo.ProducerBo;
import org.hbs.core.beans.ProducerFormBean;
import org.hbs.core.beans.model.Producers;
import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.EnumInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController implements IProducerController
{
	private static final long	serialVersionUID	= 7322526333735035930L;

	@Autowired
	ProducerBo					producerBo;

	@Override
	public ResponseEntity<?> addProducer(Authentication auth, ProducerFormBean producerForm)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(producerForm.producer))
			{
				producerBo.saveProducer(auth, producerForm);
				return new ResponseEntity<>(producerForm, HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			producerForm.producer = null;
			producerForm.messageCode = excep.getMessage();
			return new ResponseEntity<>(producerForm, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> blockProducer(Authentication auth, ProducerFormBean producerForm)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(producerForm.producer))
			{
				return new ResponseEntity<>(producerBo.blockProducer(auth, producerForm), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			producerForm.producer = null;
			producerForm.messageCode = excep.getMessage();
			return new ResponseEntity<>(producerForm, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteProducer(Authentication auth, ProducerFormBean producerForm)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(producerForm.producer))
			{
				return new ResponseEntity<>(producerBo.deleteProducer(auth, producerForm), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			producerForm.producer = null;
			producerForm.messageCode = excep.getMessage();
			return new ResponseEntity<>(producerForm, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> checkProducerExist(Authentication auth, ProducerFormBean producerForm)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(producerForm, producerForm.producer))
			{
				return new ResponseEntity<>(producerBo.checkProducerExist(producerForm), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			producerForm.producer = null;
			producerForm.messageCode = excep.getMessage();
			return new ResponseEntity<>(producerForm, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getProducer(Authentication auth, ProducerFormBean producerForm)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(producerForm.producer))
			{
				return new ResponseEntity<Producers>(producerBo.getProducer(producerForm), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			producerForm.producer = null;
			producerForm.messageCode = excep.getMessage();
			return new ResponseEntity<>(producerForm, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getProducerByName(Authentication auth, ProducerFormBean producerForm)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(producerForm.producer))
			{
				return new ResponseEntity<List<Producers>>(producerBo.getProducerByName(producerForm), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			producerForm.producer = null;
			producerForm.messageCode = excep.getMessage();
			return new ResponseEntity<>(producerForm, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateProducer(Authentication auth, ProducerFormBean producerForm)
	{
		try
		{
			if (CommonValidator.isNotNullNotEmpty(producerForm.producer))
			{
				return new ResponseEntity<EnumInterface>(producerBo.updateProducer(auth, producerForm), HttpStatus.OK);
			}
			throw new InvalidRequestException(INVALID_REQUEST_PARAMETERS);
		}
		catch (Exception excep)
		{
			producerForm.producer = null;
			producerForm.messageCode = excep.getMessage();
			return new ResponseEntity<>(producerForm, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getProducerList(Authentication auth)
	{
		try
		{

			return new ResponseEntity<List<Producers>>(producerBo.getProducerList(), HttpStatus.OK);

		}
		catch (Exception excep)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
