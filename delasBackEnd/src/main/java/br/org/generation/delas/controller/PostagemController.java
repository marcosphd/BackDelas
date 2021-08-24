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

import br.org.generation.delas.model.PostagemModel;
import br.org.generation.delas.repository.PostagemRepository;
import br.org.generation.delas.service.PostagemService;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@Autowired
	private PostagemService postagemService;
	
	// find All pra trazer todas as postagens
		@GetMapping
		public ResponseEntity<List<PostagemModel>> GetAll() {
			return ResponseEntity.ok(repository.findAll());
		}
		
		// findById pra trazer a postagem pelo id correspondente
		@GetMapping("/{id}")
		public ResponseEntity<PostagemModel> GetById(@PathVariable int id) {
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).
					orElse(ResponseEntity.notFound().build());
		}
		
		// gravar uma nova postagem
		@PostMapping
		public ResponseEntity<PostagemModel> post(@RequestBody PostagemModel newPostagem) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newPostagem));
		}
		
		// ataulizar uma postagem
		@PutMapping
		public ResponseEntity<PostagemModel> put(@RequestBody PostagemModel uptadePostagem) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(uptadePostagem));
		}
		
		@PutMapping("/curtir/{id}")
		public ResponseEntity<PostagemModel> putCurtirPostagemId (@PathVariable int id){
			
			return ResponseEntity.status(HttpStatus.OK).body(postagemService.curtir(id));
		
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable int id) {
			repository.deleteById(id);
		}
		
}
