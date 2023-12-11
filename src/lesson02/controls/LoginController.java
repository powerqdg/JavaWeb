package lesson02.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import lesson02.dao.MemberDao;
import lesson02.vo.Member;

public class LoginController implements Controller {
	MemberDao memberDao;
	
	public LoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member loginInfo = (Member)model.get("loginInfo");
		
		if (loginInfo == null) {
			return "/auth/LogInForm.jsp";
		} else {
			Member member = memberDao.exist(
					loginInfo.getEmail(), 
					loginInfo.getPassword());
			
			if (member == null) {
				return "/auth/LogInFail.jsp";
			} else {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			}
		}
	}
}
