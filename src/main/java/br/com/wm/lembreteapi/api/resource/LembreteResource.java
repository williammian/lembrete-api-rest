package br.com.wm.lembreteapi.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wm.lembreteapi.api.dto.LembreteDTO;
import br.com.wm.lembreteapi.exception.RegraNegocioException;
import br.com.wm.lembreteapi.model.entity.Lembrete;
import br.com.wm.lembreteapi.model.enums.Prioridade;
import br.com.wm.lembreteapi.service.LembreteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lembretes")
@RequiredArgsConstructor
public class LembreteResource {
	
	private final LembreteService service;
	
	@GetMapping("{id}")
	public ResponseEntity obterLembrete(@PathVariable("id") Long id) {
		return service.obterPorId(id)
					  .map(lembrete -> new ResponseEntity(converter(lembrete), HttpStatus.OK))
					  .orElseGet( () -> new ResponseEntity(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity salvar(@RequestBody LembreteDTO dto) {
		try {
			Lembrete lembrete = converter(dto);
			lembrete = service.salvar(lembrete);
			return new ResponseEntity(lembrete, HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LembreteDTO dto) {
		return service.obterPorId(id).map( entity -> {
			try {
				Lembrete lembrete = converter(dto);
				lembrete.setId(entity.getId());
				service.atualizar(lembrete);
				return ResponseEntity.ok(lembrete);
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () -> 
			new ResponseEntity("Lembrete não encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map( entity -> {
			service.deletar(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> 
			new ResponseEntity("Lembrete não encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping
	public ResponseEntity buscar() {
		List<Lembrete> lembretes = service.buscar();
		
		return ResponseEntity.ok(lembretes);
	}
	
	private LembreteDTO converter(Lembrete lembrete) {
		return LembreteDTO.builder()
				.id(lembrete.getId())
				.conteudo(lembrete.getConteudo())
				.prioridade(lembrete.getPrioridade().name())
				.build();
	}
	
	private Lembrete converter(LembreteDTO dto) {
		Lembrete lembrete = new Lembrete();
		
		lembrete.setId(dto.getId());
		lembrete.setConteudo(dto.getConteudo());
		
		if(dto.getPrioridade() != null) {
			lembrete.setPrioridade(Prioridade.valueOf(dto.getPrioridade()));
		}
		
		return lembrete;
	}

}
