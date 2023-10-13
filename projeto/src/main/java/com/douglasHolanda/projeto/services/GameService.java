package com.douglasHolanda.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglasHolanda.projeto.dto.GameDTO;
import com.douglasHolanda.projeto.dto.GameMinDTO;
import com.douglasHolanda.projeto.entities.Game;
import com.douglasHolanda.projeto.projections.GameMinProjection;
import com.douglasHolanda.projeto.repositories.GameRepository;

@Service
public class GameService 
{
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public GameDTO findById(Long id)
	{
		Game result = gameRepository.findById(id).get();
		return new GameDTO(result);
	}

	@Transactional(readOnly = true) 
	public List<GameMinDTO> findAll()
	{
		List <Game> result = gameRepository.findAll();
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(long listId)
	{
		List <GameMinProjection> result = gameRepository.searchByList(listId); 
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	}
}
  