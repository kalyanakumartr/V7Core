package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.util.ICRUDBean;

@Entity
@Table(name = "menurole")
public class MenuRole implements IProducersBase, ICRUDBean
{

	private static final long	serialVersionUID	= 7589959540619401593L;

	protected Menu				menu;

	protected int				autoId;

	protected Producers			producer;

	protected IRoles			roles;

	public MenuRole()
	{
		super();
	}

	@ManyToOne(targetEntity = Menu.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "menuId")
	public Menu getMenu()
	{
		return menu;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "autoId")
	public int getAutoId()
	{
		return autoId;
	}

	@ManyToOne(targetEntity = Producers.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "producerId")
	public Producers getProducer()
	{
		return producer;
	}

	@ManyToOne(targetEntity = Roles.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	public IRoles getRoles()
	{
		return roles;
	}

	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}

	public void setAutoId(int autoId)
	{
		this.autoId = autoId;
	}

	public void setProducer(Producers producer)
	{
		this.producer = producer;
	}

	public void setRoles(IRoles roles)
	{
		this.roles = roles;
	}

}