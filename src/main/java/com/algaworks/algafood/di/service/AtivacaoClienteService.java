package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
//	@Autowired
//	private ApplicationEventPublisher eventPublisher;
	
//	@PostConstruct
//	void init( ) {
//		System.out.println("CHAMOU INIT");
//	}
	
//	@PreDestroy
//	void destroy() {
//		System.out.println("CHAMOU DESTROY");
//	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();

//		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
}