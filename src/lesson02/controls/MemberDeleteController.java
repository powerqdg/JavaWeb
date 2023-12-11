package lesson02.controls;

import java.util.Map;

import lesson02.dao.MemberDao;

public class MemberDeleteController implements Controller {
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		memberDao.delete((int)model.get("mno"));
		return "redirect:list.do";
	}
}
