package lesson02.controls;

import java.util.Map;

import lesson02.dao.MemberDao;
import lesson02.vo.Member;

public class MemberUpdateController implements Controller {
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		Member member = (Member)model.get("member");
		if (member == null) {
			int mno = (int)model.get("mno");
			model.put("member", memberDao.selectOne(mno));
			return "/member/MemberUpdate.jsp";
		} else {
			memberDao.update(member);
			return "redirect:list.do";
		}
	}
}
