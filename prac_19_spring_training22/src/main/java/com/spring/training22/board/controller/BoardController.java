package com.spring.training22.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.training22.board.dto.BoardDto;
import com.spring.training22.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "board/bMain";
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.GET)
	public String boardWrite() {
		
		return "board/bWrite";
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.GET)
	public String boardWrite(BoardDto boardDto) {
		
		boardService.insertBoard(boardDto);
		
		return "redirect:boardList";	
	}
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	public String boardList(Model model) {
		
		model.addAttribute("boardList", boardList);
		
		return "board/bList";
	}
	
	@RequestMapping(value="/boardInfo", method=RequestMethod.GET)
	public String boardInfo(@RequestParam("num") int num, Model model) {
		
		BoardDto boardDto = boardService.getOneBoard(num);
		model.addAttribute("boardDto", boardDto);
		
		return "board/bInfo";
	}
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(@RequestParam("num") int num, Model model) {
		
		model.addAttribute("boardDto", boardService.getOneBoard(num));
		
		return "board/bDelete";
	}
	
	@RequestMapping(value="/boardDelete", method=RequestMethod.GET)
	public String boardDelete(BoardDto boardDto, Model model) {
		
		if(boardService.deleteBoard(boardDto)) {
			model.addAttribute("success", true);
		}
		else {
			model.addAttribute("success", false);
		}
		
		
		return "board/bDeletePro";
	}
}
