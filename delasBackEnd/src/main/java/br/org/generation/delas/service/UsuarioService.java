package br.org.generation.delas.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.generation.delas.model.UserLogin;
import br.org.generation.delas.model.UsuarioModel;
import br.org.generation.delas.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<UsuarioModel> CadastrarUsuario(UsuarioModel usuario) {

		/* Verifica se o usuário existe */
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return null;

		/* Verifica se o email existe */
		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return null;

		/* Criprtografa a senha */
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());

		/*
		 * Atualiza a senha no objeto usuario, ou seja, substitui a senha digitada pela
		 * senha criptografada
		 */
		usuario.setSenha(senhaEncoder);

		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {

		/* Verifica se o email já existe */
		// if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
		// 	return null;

		/* Criprtografa a senha */
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());

		/*
		 * Atualiza a senha no objeto usuario, ou seja, substitui a senha digitada pela
		 * senha criptografada
		 */
		usuario.setSenha(senhaEncoder);

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);			
				user.get().setId(usuario.get().getId());
				user.get().setUsuario(usuario.get().getUsuario());
				//user.get().setFoto(usuario.get().getFoto());
				user.get().setTipoUser(usuario.get().getTipoUser());
				
				return user;

			}
		}

		return null;
	}
}
