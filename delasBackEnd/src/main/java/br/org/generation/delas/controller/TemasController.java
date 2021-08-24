package br.org.generation.delas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.delas.model.Temas;
import br.org.generation.delas.repository.TemasRepository;
import br.org.generation.delas.service.TemaService;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemasController {

	@Autowired
	private TemasRepository repository;
	
	private TemaService temaService;
	
	
	

	// find All pra trazer todas categorias
	@GetMapping
	public ResponseEntity<List<Temas>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	// findById pra trazer a categoria pelo id correspondente
	@GetMapping("/{id}")
	public ResponseEntity<Temas> GetById(@PathVariable int id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	// findByCategorias para trazer uma categoria especifica
	@GetMapping("/categorias/{categorias}")
	public ResponseEntity<List<Temas>> GetByCategorias(@PathVariable String categorias) {
		return ResponseEntity.ok(repository.findAllByCategoriasContainingIgnoreCase(categorias));
	}

	// findByDescricao para trazer uma descricao especifica
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Temas>> GetByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	/**
	 * 
	 * Calcula o numero de postagens por tema
	 * 
	 */
	
	@GetMapping("/trendtopics")
	public ResponseEntity<List<Temas>> getTrendTopics() {
		
		return ResponseEntity.ok(temaService.trendTopics());
	
	}

	// gravar uma nova categoria
	@PostMapping
	public ResponseEntity<Temas> post(@RequestBody Temas newCategorias) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newCategorias));
	}

	// ataulizar categoria
	@PutMapping
	public ResponseEntity<Temas> put(@RequestBody Temas updateCategorias) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(updateCategorias));
	}

	// deletar categoria
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		repository.deleteById(id);
	}

}
