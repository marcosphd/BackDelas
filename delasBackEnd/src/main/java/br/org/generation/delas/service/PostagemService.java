package br.org.generation.delas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.org.generation.delas.model.PostagemModel;
import br.org.generation.delas.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	public PostagemModel curtir(Integer id) {

		/**
		 * Busca a Postagem no Banco de Dados pelo Id utilizabdo o método
		 * buscarPostagemPeloId(id) implmentado logo abaixo
		 */

		PostagemModel postagem = buscarPostagemPeloId(id);

		/**
		 * Se o Método retornou a Postagem, soma 1 curtida no atributo curtidas através
		 * do método Set que altera o atributo e do método Get que recupera o atributo
		 * do Banco de Dados
		 */

		postagem.setCurtidas(postagem.getCurtidas() + 1);

		/**
		 * Retorna para a Classe Controller a postagem atualizada com uma nova curtida
		 */

		return postagemRepository.save(postagem);

	}

	private PostagemModel buscarPostagemPeloId(Integer id) {

		/**
		 * Busca uma Postagem pelo Id e caso não encontre retorna null
		 */

		PostagemModel postagemSalva = postagemRepository.findById(id).orElse(null);

		/**
		 * Verifica o retorni do método
		 * 
		 * Se for null, retorna uma Exception
		 * 
		 * Caso contrário, retorna a postagem
		 */

		if (postagemSalva == null) {

			throw new EmptyResultDataAccessException(1);
		}

		return postagemSalva;
	}

}
