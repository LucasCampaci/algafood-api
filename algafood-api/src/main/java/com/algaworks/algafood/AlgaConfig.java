//package com.algaworks.algafood;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.algaworks.algafood.di.notificacao.NotificadorEmail;
//import com.algaworks.algafood.di.service.AtivacaoClienteService;
//
//public class AlgaConfig {
//	
//	@Bean
//	public NotificadorEmail notificadorEmail() {
//		NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
//		notificador.setCaixaAlta(true);
//		return notificador;
//	}
//
//	@Bean
//	public AtivacaoClienteService ativacaoClienteService() {
//		return new AtivacaoClienteService(notificadorEmail());
//	}
//}