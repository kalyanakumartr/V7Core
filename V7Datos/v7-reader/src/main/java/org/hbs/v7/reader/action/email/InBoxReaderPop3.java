package org.hbs.v7.reader.action.email;

import org.hbs.v7.reader.action.core.InBoxReader;
import org.springframework.stereotype.Service;

@Service
public class InBoxReaderPop3 extends InBoxReaderBaseImpl implements InBoxReader
{

	private static final long serialVersionUID = 4813150068221933347L;

	@Override
	public void readDataFromChannel()
	{

	}

}
