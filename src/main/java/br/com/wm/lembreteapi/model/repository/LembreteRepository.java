package br.com.wm.lembreteapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wm.lembreteapi.model.entity.Lembrete;

public interface LembreteRepository extends JpaRepository<Lembrete, Long>{

}
