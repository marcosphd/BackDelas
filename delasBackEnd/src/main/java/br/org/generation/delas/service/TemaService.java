package br.org.generation.delas.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.generation.delas.model.Temas;
import br.org.generation.delas.repository.PostagemRepository;
import br.org.generation.delas.repository.TemasRepository;

@Service
public class TemaService {
	
	@Autowired
	private TemasRepository temaRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;

	
	
	/**
	 *
	 * Gera Trendtopics 
	 *
	 */

	public List<Temas> trendTopics(){
		
		List<Temas> temas = temaRepository.findAll();
		
		for (Temas tema : temas) {
	
			tema.setQtd_post(postagemRepository.countPosts2(tema.getId()));
		}
		
		Collections.sort(temas, Collections.reverseOrder(Comparator.comparing(Temas::getQtd_post)));
		return temas;
	
	
	/** Injeção de dependência das Classes PostagemRepository 
	 *  e TemaRepository.
	 *  
	 *  Neste código iremos trabalhar com as 2 classes Repositório
	*/
	//@Autowired
	//private TemasRepository temaRepository;
	
	//@Autowired
	//private PostagemRepository postagemRepository;
	
	
	}
}
