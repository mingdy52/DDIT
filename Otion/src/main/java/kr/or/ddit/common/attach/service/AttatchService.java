package kr.or.ddit.common.attach.service;

import kr.or.ddit.common.vo.AttatchVO;

public interface AttatchService {
	
	public void createAttatch(AttatchVO attatch);
	
	public void createFolderAttatch(AttatchVO attatch);
	
	public AttatchVO download(String attatchNum);
	
	public void deleteAttatchFile(AttatchVO attatch);

	public AttatchVO multiDownload(AttatchVO attatch);
	
	public void createGitAttatch(AttatchVO attatch);

	public void createReplyAttatch(AttatchVO attatch);
	
	public void createIssueReplyAttatch(AttatchVO attatch);
	
	public void modifyAttatch(AttatchVO attatch);
	
	public void createAttachNumReplyAttatch(AttatchVO attatch);
	
	public String findAttachNum(String parentWoReplyNum);

	public AttatchVO retrieveAttatch(AttatchVO attatch);
	
}
