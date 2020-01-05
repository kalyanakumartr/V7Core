package org.hbs.core.beans.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hbs.core.util.CommonValidator;
import org.hbs.core.util.ICRUDBean;
import org.jsoup.nodes.Element;

@Entity
@Table(name = "menu")
public class Menu implements ICRUDBean
{
	protected static final long	serialVersionUID	= 4897686429234828726L;

	protected String			actionURL;
	protected String			cssClass;
	protected String			cssClassIcon;
	protected boolean			haveSubMenu;
	protected String			level;
	protected String			menuId;
	protected String			menuName;
	protected String			parentId;

	public Menu()
	{
		super();
	}

	public Menu(String menuId, String menuName, String actionURL, String parentId, boolean haveSubMenu, String level, String cssClass, String cssClassIcon)
	{
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.actionURL = actionURL;
		this.parentId = parentId;
		this.haveSubMenu = haveSubMenu;
		this.level = level;
		this.cssClass = cssClass;
		this.cssClassIcon = cssClassIcon;
	}

	public void _getMenuHTML(Element parent, String orderCss)
	{
		if (menuId.equals(parentId))
		{
			if (CommonValidator.isNotNullNotEmpty(orderCss))
				parent.appendElement("li").attr("id", menuId).addClass(orderCss);
			else
				parent.appendElement("li").attr("id", menuId);
			Element eltA = parent.getElementById(menuId);
			eltA.appendElement("a").attr("id", menuId + "_A").attr("href", actionURL);

			Element elt = parent.getElementById(menuId + "_A");

			if (CommonValidator.isNotNullNotEmpty(cssClassIcon))
				elt.appendElement("i").addClass(cssClassIcon);

			elt.appendElement("span").appendText(" " + menuName).addClass(cssClass);

			if (haveSubMenu)
			{
				elt.appendElement("span").addClass("arrow ");
				eltA.appendElement("ul").attr("id", menuId + "_UL").addClass("sub-menu");
			}

		}
		else if (parent.id().equals(parentId) == false)
		{
			Element eltUL = parent.getElementById(parentId + "_UL");

			eltUL.appendElement("li").attr("id", menuId);
			Element eltA = parent.getElementById(menuId);
			eltA.appendElement("a").attr("id", menuId + "_A").attr("href", actionURL);

			Element elt = parent.getElementById(menuId + "_A");

			if (CommonValidator.isNotNullNotEmpty(cssClassIcon))
				elt.appendElement("i").addClass(cssClassIcon);

			elt.appendElement("span").appendText(" " + menuName).addClass(cssClass);
		}
	}

	@Column(name = "actionURL")
	public String getActionURL()
	{
		return actionURL;
	}

	@Column(name = "cssClass")
	public String getCssClass()
	{
		return cssClass;
	}

	@Column(name = "cssClassIcon")
	public String getCssClassIcon()
	{
		return cssClassIcon;
	}

	@Column(name = "haveSubMenu")
	public boolean getHaveSubMenu()
	{
		return haveSubMenu;
	}

	@Column(name = "level")
	public String getLevel()
	{
		return level;
	}

	@Id
	@Column(name = "menuId")
	public String getMenuId()
	{
		return menuId;
	}

	@Column(name = "menuName")
	public String getMenuName()
	{
		return menuName;
	}

	@Column(name = "parentId")
	public String getParentId()
	{
		return parentId;
	}

	public void setActionURL(String actionURL)
	{
		this.actionURL = actionURL;
	}

	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}

	public void setCssClassIcon(String cssClassIcon)
	{
		this.cssClassIcon = cssClassIcon;
	}

	public void setHaveSubMenu(boolean haveSubMenu)
	{
		this.haveSubMenu = haveSubMenu;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}

	public void setMenuId(String menuId)
	{
		this.menuId = menuId;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

}