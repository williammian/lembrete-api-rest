package br.com.wm.lembreteapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wm.lembreteapi.model.enums.Prioridade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lembrete")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lembrete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "conteudo")
	private String conteudo;

	@Column(name = "prioridade")
	@Enumerated(value = EnumType.STRING)
	private Prioridade prioridade;
	
}
