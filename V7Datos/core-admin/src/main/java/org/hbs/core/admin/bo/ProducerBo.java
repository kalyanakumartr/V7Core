package org.hbs.core.admin.bo;

import java.security.InvalidKeyException;
import java.util.List;

import org.apache.kafka.common.errors.InvalidRequestException;
import org.hbs.core.beans.ProducerFormBean;
import org.hbs.core.beans.model.Producers;
import org.hbs.core.util.EnumInterface;
import org.springframework.security.core.Authentication;

public interface ProducerBo
{
	Producers getProducer(ProducerFormBean request);

	EnumInterface saveProducer(Authentication auth, ProducerFormBean request) throws InvalidKeyException;

	EnumInterface updateProducer(Authentication auth, ProducerFormBean request);

	EnumInterface blockProducer(Authentication auth, ProducerFormBean request);

	List<Producers> getProducerByName(ProducerFormBean request);

	EnumInterface checkProducerExist(ProducerFormBean request);

	List<Producers> getProducerList();

	EnumInterface blockProducerById(Authentication auth, ProducerFormBean request);

	EnumInterface deleteProducer(Authentication auth, ProducerFormBean cfBean) throws InvalidRequestException;
}
