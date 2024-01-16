package kr.or.ddit.memo.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.memo.MemoVO;
import kr.or.ddit.memo.dao.MemoDAO;

@Service
public class MemoService {
	
	@Inject
	private MemoDAO memoDAO;
	
	public MemoVO retrieveMemo(int code) {
		MemoVO memo = memoDAO.selectMemo(code);
		return memo;
	}
	
	public Map<Integer, MemoVO> retrieveMemoList() {
		Map<Integer, MemoVO> memoList = MemoDAO.selectMemoList();
		return memoList;
	}
	

	public Map<Integer, MemoVO> createMemo(MemoVO memo) {
		Map<Integer, MemoVO> memoList = memoDAO.insertMemo(memo);
		return memoList;
		
	}
	
	public Map<Integer, MemoVO> modifyMemo(MemoVO memo) {
		Map<Integer, MemoVO> memoList = memoDAO.updateMemo(memo);
		return memoList;
	}
	
	public void removeMemo() {
		
	}
	
}
