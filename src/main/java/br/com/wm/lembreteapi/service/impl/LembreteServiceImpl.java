package br.com.wm.lembreteapi.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wm.lembreteapi.exception.RegraNegocioException;
import br.com.wm.lembreteapi.model.entity.Lembrete;
import br.com.wm.lembreteapi.model.repository.LembreteRepository;
import br.com.wm.lembreteapi.service.LembreteService;

@Service
public class LembreteServiceImpl implements LembreteService {
	
	private LembreteRepository repository;
	
	public LembreteServiceImpl(LembreteRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Lembrete salvar(Lembrete lembrete) {
		validar(lembrete);
		return repository.save(lembrete);
	}

	@Override
	@Transactional
	public Lembrete atualizar(Lembrete lembrete) {
		Objects.requireNonNull(lembrete.getId());
		validar(lembrete);
		return repository.save(lembrete);
	}

	@Override
	@Transactional
	public void deletar(Lembrete lembrete) {
		Objects.requireNonNull(lembrete.getId());
		repository.delete(lembrete);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lembrete> buscar() {
		return repository.findAll();
	}

	@Override
	public void validar(Lembrete lembrete) {
		
		if(lembrete.getConteudo() == null || lembrete.getConteudo().trim().equals("")) {
			throw new RegraNegocioException("Informe o conteúdo.");
		}
		
		if(lembrete.getPrioridade() == null) {
			throw new RegraNegocioException("Informe a prioridade.");
		}
		
	}

	@Override
	public Optional<Lembrete> obterPorId(Long id) {
		return repository.findById(id);
	}

}
