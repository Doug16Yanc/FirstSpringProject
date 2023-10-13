package com.douglasHolanda.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglasHolanda.projeto.dto.GameListDTO;
import com.douglasHolanda.projeto.entities.GameList;
import com.douglasHolanda.projeto.projections.GameMinProjection;
import com.douglasHolanda.projeto.repositories.GameListRepository;
import com.douglasHolanda.projeto.repositories.GameRepository;

@Service
public class GameListService
{
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll()
	{
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex)
	{
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++)
		{
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}

	}
	
}
