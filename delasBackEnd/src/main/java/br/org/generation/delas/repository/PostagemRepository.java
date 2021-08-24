package br.org.generation.delas.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import br.org.generation.delas.model.PostagemModel;

@Repository
@Transactional(readOnly = true)
public interface PostagemRepository extends JpaRepository<PostagemModel, Integer>{
	
	//public List <PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);
	
	
	/**
	 * 
	 * Consulta Nativa - Conta o numero de postagens por tema
	 * 
	 */
	
	@Query(value = "select count(tema_id) from tb_postagens where tema_id = :id" , nativeQuery = true)
	public int countPosts2(@Param("id") int id);
	
}
