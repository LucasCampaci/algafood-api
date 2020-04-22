package com.algaworks.algafood.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotaFiscalService {
	@EventListener
	public void emitirNotaFiscaloListener(ClienteAtivadoEvent event) {
		System.out.println("Emitindo nota fiscal para cliente " + event.getCliente().getNome());
	}
}
