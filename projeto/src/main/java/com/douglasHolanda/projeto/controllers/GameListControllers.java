package com.douglasHolanda.projeto.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasHolanda.projeto.dto.GameListDTO;
import com.douglasHolanda.projeto.dto.GameMinDTO;
import com.douglasHolanda.projeto.dto.ReplacementDTO;
import com.douglasHolanda.projeto.services.GameListService;
import com.douglasHolanda.projeto.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListControllers 
{
	@Autowired
	private GameListService gameListService;	
	
	@Autowired
	private GameService gameService;

	@GetMapping
	public List<GameListDTO> findAll() 
	{
		List<GameListDTO> result = gameListService.findAll();
		return result;
	}
	@GetMapping(value = "/{listId}/games")
	public List<GameMinDTO> findByList(@PathVariable Long listId)
	{
		List<GameMinDTO> result = gameService.findByList(listId);
		return result;
	}
	@PostMapping(value = "/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body)
	{
		gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	}
}
