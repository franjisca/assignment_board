package com.assignment.board;

import com.assignment.board.domain.Board;
import com.assignment.board.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardApplicationTests {

	BoardRepository boardStore = new BoardRepository();

	@AfterEach
	void afterTest(){
		boardStore.boardList().clear();
	}

	@Test
	void saveBoard() {
		Board board = new Board();
		board.setReadCount(0);
		board.setContents("test");
		board.setTitle("title");
		board.setChecked("n");
		board.setWriter("test");
		board.setWriteDate(LocalDateTime.now());

		Board saveBoard = boardStore.saveBoard(board);

		Board findBoard = boardStore.findById(saveBoard.getBoardNo());

		assertThat(findBoard).isEqualTo(saveBoard);
	}

	@Test
	void boardList() {

		for(int i=0; i<3; i++){
		Board board = new Board();
		board.setReadCount(0);
		board.setContents("test"+i);
		board.setTitle("title"+i);
		board.setChecked("n");
		board.setWriter("test"+i);
		board.setWriteDate(LocalDateTime.now());
		Board saveBoard = boardStore.saveBoard(board);
		}

		List<Board> boards = boardStore.boardList();

		assertThat(boards.size()).isEqualTo(3);

	}

	@Test
	void updateBoard(){
		Board board = new Board("test", "test","test","y",LocalDateTime.now(), 0);
		Board saveBoard = boardStore.saveBoard(board);

		Board updateBoard = new Board("test", "hihihihi", "testtttt", "n", LocalDateTime.now(), 1);

		boardStore.updateBoard(saveBoard.getBoardNo(), updateBoard);
		Board findBoard = boardStore.findById(saveBoard.getBoardNo());

		assertThat(findBoard.getWriter()).isEqualTo(updateBoard.getWriter());
		assertThat(findBoard.getTitle()).isEqualTo(updateBoard.getTitle());
		assertThat(findBoard.getContents()).isEqualTo(updateBoard.getContents());
		assertThat(findBoard.getChecked()).isEqualTo(updateBoard.getChecked());
	}

}
