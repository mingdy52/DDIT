package kr.or.ddit.memo.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.memo.MemoVO;

@Repository
public class MemoDAO {
	
	public static Map<Integer, MemoVO> memoTable = new LinkedHashMap<>();
	
	public static Map<Integer, MemoVO> selectMemoList() {
		return memoTable;
	}
	
	public MemoVO selectMemo(int code) {
		MemoVO memo = memoTable.get(code);
		return memo;
	}
	
	public Map<Integer, MemoVO> insertMemo(MemoVO memo) {
		int lastCode = memoTable.keySet().stream()
				.mapToInt(key->key.intValue()) // key integer 를 int로 바꿈.
				.max()
				.orElse(0); // max가 없을 때 쓸 수 있는 기본값
		memo.setCode(lastCode+1);
		memoTable.put(memo.getCode(), memo);
		return memoTable; 
	}
	

	
	public Map<Integer, MemoVO> updateMemo(MemoVO memo) {
		int code = memo.getCode();
		 memoTable.put(code, memo);
		return memoTable;
	}
	
//	public deleteMemo() {
//		
//	}
}
