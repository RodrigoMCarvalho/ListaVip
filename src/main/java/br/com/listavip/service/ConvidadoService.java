package br.com.listavip.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.listavip.model.Convidado;
import br.com.listavip.repository.ConvidadoRepository;

@Service
public class ConvidadoService {
	
	@Autowired
	ConvidadoRepository repository;
	
	public Iterable<Convidado> ObterConvidados() {
		Iterable<Convidado> convidados = repository.findAll(); // obt�m os dados do BD

		return convidados;
	}
	
	public void salvar(Convidado convidado) {
		repository.save(convidado);
	}
	
	public void excluirPorId(Long id) {
		repository.deleteById(id);
	}
	
	public Optional<Convidado> procurarPorId(Long id) {
		Optional<Convidado> convidado = repository.findById(id);
		return convidado;
	}

//
//	public void editar(Long idUsuario, Convidado convidado) {
//		List<Convidado> convidados = repository.buscarConvidados(); // obt�m os dados do BD
//		
//		for (int i = 0; i < convidados.size(); i++) {
//			Convidado c = convidados.get(i);
//			if (c.getId().equals(idUsuario)) {
//				convidados.set(i, convidado);
//				return;
//			}
//		}
		
//	}
	

}
