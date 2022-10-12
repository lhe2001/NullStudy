package board;

import java.util.List;

public class BoardService {
	/*
	 * TODO 귀찮긴 한데 나눠보고 싶어서 쿼리 실행문은 여길로 뺐습니다.
	 */

	// 필드 영역 DAO 초기화
	BoardDAO dao;

	// 생성자 호출시 dao 객체 생성하고
	public BoardService() {
		dao = new BoardDAO();
	}

	// 게시글 전체를 조회할 수 있는 메소드 호출
	public List<BoardDTO> listArticles() {
		// dao클래스에 selectAllArticles() 메소드를 게시글 전체를 조회할 수 있게 짜면 된다.
		List<BoardDTO> list = dao.selectAllArticles();
		// list를 리턴해주면 끝!
		return list;
	}

	// 게시글을 추가 할 수 있는 메소드 호출
	public int addArticle(BoardDTO dto) {
		return dao.addNewArticle(dto);
	}

	// 게시글을 상세보기로 볼 수 있는 메소드 호출
	public BoardDTO viewArticle(int b_articleNo) {
		return dao.selectViewArticle(b_articleNo);
	}

	// 게시글 수정 메소드
	public void updateArticle(BoardDTO dto) {
		dao.modifyArticle(dto);
	}

	// 게시글 삭제 메소드
	public List<Integer> deleteArticle(int b_articleNo) {
		List<Integer> list = dao.selectRemovedArticle(b_articleNo);
		dao.deleteArticle(b_articleNo);
		return list;
	}

	// 게시글 답글 추가 메소드
	public int addReply(BoardDTO dto) {
		return dao.addNewArticle(dto);
	}

//	// 게시글 제목으로 검색하는 메소드
//	public List<BoardDTO> titleSearch(BoardDTO dto) {
//		List<BoardDTO> list = dao.searchTitleArticle(dto);
//		return list;
//	}
//
//	// 게시글 내용으로 검색하는 메소드
//	public List<BoardDTO> contentSearch(BoardDTO dto) {
//		List<BoardDTO> list = dao.searchContentArticle(dto);
//		return list;
//	}
//
//	// 개시글 작성자로 검색하는 메소드
//	public List<BoardDTO> writerSearch(BoardDTO dto) {
//		List<BoardDTO> list = dao.searchWriterArticle(dto);
//		return list;
//	}

	// 게시글 전체로 검색하는 메소드
	public List<BoardDTO> allSearch(BoardDTO dto) {
		List<BoardDTO> list = dao.searchAllArticle(dto);
		return list;
	}

	// 말머리로 리스트를 보여줄 메소드
	public List<BoardDTO> selectViewArticle(BoardDTO dto) {
		List<BoardDTO> list = dao.wiewAllArticle(dto);
		return list;
	}
}
