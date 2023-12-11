package lesson02.controls;

import java.util.Map;

import lesson02.annotation.Component;
import lesson02.bind.DataBinding;
import lesson02.dao.MemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"mno", Integer.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		memberDao.delete((int)model.get("mno"));
		return "redirect:list.do";
	}
}
