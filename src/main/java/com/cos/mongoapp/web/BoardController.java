package com.cos.mongoapp.web;



import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mongoapp.domain.Board;
import com.cos.mongoapp.domain.BoardRepository;
import com.cos.mongoapp.web.dto.BoardSaveDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //DI
@RestController					  //데이터 리턴 서버
public class BoardController {
	//DI
	private final BoardRepository boardRepository;
	
	@PutMapping("/board/{id}")
	public Board update(@RequestBody BoardSaveDto dto, @PathVariable String id) {
		
		Board board = dto.toEntity();
		board.set_id(id); // save함수는 같은 아이디면 수정한다.
		
		return boardRepository.save(board);
		
		//postman put delete쓸려고 사용하는거임 ㅎㅎ
	}
	
	
	@DeleteMapping("/board/{id}")
	public int deleteById(@PathVariable String id){
		boardRepository.deleteById(id);
		
		return 1;  //1 성공, -1 실패
	}
	
	
	@GetMapping("/board/{id}")
	public Board findById(@PathVariable String id) {
		return  boardRepository.findById(id).get();
	}
	
	
	@GetMapping("/board")
	public List<Board>findAll() { //리턴을 JAVAObject로 하면 스프링 내부적으로 JSON으로 자동 변환해준다.
		return boardRepository.findAll();
		
	}
	@PostMapping("/board")
	public Board save(@RequestBody BoardSaveDto dto) {  //{"title":"제목3", " content":"내용3"} 을 받으려함 
		
		return boardRepository.save(dto.toEntity());
		
	}
}
