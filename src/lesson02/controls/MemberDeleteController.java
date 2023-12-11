package lesson02.controls;

import java.util.Map;

import lesson02.dao.MemberDao;

public class MemberDeleteController implements Controller {
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		memberDao.delete((int)model.get("mno"));
		return "redirect:list.do";
	}
}
