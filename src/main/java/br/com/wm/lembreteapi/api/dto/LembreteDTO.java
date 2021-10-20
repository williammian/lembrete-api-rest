package br.com.wm.lembreteapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LembreteDTO {
	
	private Long id;
	private String conteudo;
	private String prioridade;

}