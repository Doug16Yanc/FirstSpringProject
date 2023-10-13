package com.douglasHolanda.projeto.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglasHolanda.projeto.dto.GameDTO;
import com.douglasHolanda.projeto.dto.GameMinDTO;
import com.douglasHolanda.projeto.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameControllers 
{
	@Autowired
	private GameService gameService;	

	@GetMapping(value = "/{id}")
	public GameDTO findById(@PathVariable Long id)
	{
		GameDTO result = gameService.findById(id);
		return result;
	}

	@GetMapping
	public List<GameMinDTO> findAll() 
	{
		List<GameMinDTO> result = gameService.findAll();
		return result;
	}
	
}
