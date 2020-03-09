package org.hbs.core.security.resource;

import org.hbs.core.security.resource.IPathBase.ERole;
import org.hbs.core.util.EnumInterface;

public interface EnumResourceInterface extends EnumInterface
{
	public String getPath();

	public ERole[] getRoles();

}
