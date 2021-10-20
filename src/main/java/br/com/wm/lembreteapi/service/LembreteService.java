package br.com.wm.lembreteapi.service;

import java.util.List;
import java.util.Optional;

import br.com.wm.lembreteapi.model.entity.Lembrete;

public interface LembreteService {
	
	Lembrete salvar(Lembrete lembrete);
	
	Lembrete atualizar(Lembrete lembrete);
	
	void deletar(Lembrete lembrete);
	
	List<Lembrete> buscar();
	
	void validar(Lembrete lembrete);
	
	Optional<Lembrete> obterPorId(Long id);

}
