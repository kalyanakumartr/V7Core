package org.hbs.core.beans.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hbs.core.beans.model.IAddress.AddressType;
import org.hbs.core.util.EnumInterface;
import org.hbs.core.util.LabelValueBean;
import org.hbs.core.util.Masker;

public interface IUsers extends IUsersBase, IUsersByUser
{
	public enum EUsers implements EnumInterface
	{
		Dummy, SuperAdmin, System;

		private static final String BREAD_CRUMP = "BreadCrump";

		@SuppressWarnings("unchecked")
		public static List<LabelValueBean> getBreadCrump(HttpServletRequest request, int index, String label) throws Exception
		{
			Object object = request.getSession().getAttribute(BREAD_CRUMP);

			if (object == null)
			{
				request.getSession().setAttribute(BREAD_CRUMP, new ArrayList<LabelValueBean>());
				object = request.getSession().getAttribute(BREAD_CRUMP);
			}

			List<LabelValueBean> bcList = (List<LabelValueBean>) object;

			for (int i = (bcList.size() - 1); i >= index; i--)
			{
				bcList.remove(i);
			}
			String url = request.getParameter("b");
			String decUrl = Masker.decryptBase64(url);
			String encUrl = (decUrl.indexOf("?") > 0) ? "&b=" + url : "?b=" + url;

			bcList.add(new LabelValueBean(decUrl + encUrl, label));

			return bcList;
		}

	}

	public Set<IUsersAddress> getAddressList();

	public IUsersAddress getAddressToDisplay(AddressType addressType);

	public IUsersAttachments getAttachment(EnumInterface documentType);

	public Set<IUsersAttachments> getAttachmentList();

	public String getDomainUrl(HttpServletRequest request);

	public Set<UsersMedia> getMediaList();

	public String getOtp();

	public Set<IUserPortlets> getUserPorlets();

	public Set<IUserRoles> getUserRoleses();

	public boolean hasMenuRole(String pathVariable);

	public boolean isAdmin();

	public boolean isEmployee();

	public boolean isSuperAdmin();

	public boolean isValid();

	public void setAddressList(Set<IUsersAddress> addressList);

	public void setAttachmentList(Set<IUsersAttachments> attachmentList);

	public void setMediaList(Set<UsersMedia> mediaList);

	public void setOtp(String otp);

	public void setUserPorlets(Set<IUserPortlets> userPorlets);

	public void setUserRoleses(Set<IUserRoles> userRoleses);
}