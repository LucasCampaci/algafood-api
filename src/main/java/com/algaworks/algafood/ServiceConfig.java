//package com.algaworks.algafood;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.algaworks.algafood.di.notificacao.Notificador;
//import com.algaworks.algafood.di.service.AtivacaoClienteService;
//
//@Configuration
//public class ServiceConfig {
//
//	@Bean(initMethod="init", destroyMethod="destroy")
//	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
//		return new AtivacaoClienteService();
//	}
//}