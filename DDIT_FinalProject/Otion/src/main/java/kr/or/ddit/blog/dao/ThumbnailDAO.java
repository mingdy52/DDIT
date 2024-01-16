package kr.or.ddit.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.blog.vo.ThumbNailVO;
@Mapper
public interface ThumbnailDAO {
	
	public List<ThumbNailVO> ThumbnailList();
}
