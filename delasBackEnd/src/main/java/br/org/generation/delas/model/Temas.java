package br.org.generation.delas.model;






import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





@Entity
@Table(name = "tb_temas")
public class Temas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 5, max = 255)
	private String categorias;

	@Size(min = 5, max = 255)
	private String descricao;

	@PositiveOrZero
	private int qtd_post;
	
	@OneToMany(mappedBy = "temas", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("temas")
	private List<PostagemModel> postagens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtd_post() {
		return qtd_post;
	}

	public void setQtd_post(int qtd_post) {
		this.qtd_post = qtd_post;
	}

	public List<PostagemModel> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<PostagemModel> postagens) {
		this.postagens = postagens;
	}

}
