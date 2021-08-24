package br.org.generation.delas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagens")
public class PostagemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fotoPost;

	//@Positive
	private int curtidas;

	//@Column(columnDefinition = "text")
	private String textoPost;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPost = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private Temas temas;

	@ManyToOne
	@JsonIgnoreProperties("postagens")
	private UsuarioModel usuarios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFotoPost() {
		return fotoPost;
	}

	public void setFotoPost(String fotoPost) {
		this.fotoPost = fotoPost;
	}

	public int getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(int curtidas) {
		this.curtidas = curtidas;
	}

	public String getTextoPost() {
		return textoPost;
	}

	public void setTextoPost(String textoPost) {
		this.textoPost = textoPost;
	}

	public Date getDataPost() {
		return dataPost;
	}

	public void setDataPost(Date dataPost) {
		this.dataPost = dataPost;
	}

	public Temas getTemas() {
		return temas;
	}

	public void setTemas(Temas temas) {
		this.temas = temas;
	}

	public UsuarioModel getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(UsuarioModel usuarios) {
		this.usuarios = usuarios;
	}

	}


