//package com.algaworks.algafood.infrastructure.repository;
//
//import com.algaworks.algafood.domain.model.Estado;
//import com.algaworks.algafood.domain.repository.EstadoRepository;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Component
//public class EstadoRepositoryImpl implements EstadoRepository {
//
//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	public List<Estado> listar() {
//		return manager.createQuery("from Estado", Estado.class)
//				.getResultList();
//	}
//
//	@Override
//	public Estado buscar(Long id) {
//		return manager.find(Estado.class, id);
//	}
//
//	@Transactional
//	@Override
//	public Estado salvar(Estado estado) {
//		return manager.merge(estado);
//	}
//
//	@Transactional
//	@Override
//	public void remover(Long estadoId) {
//		Estado estado = buscar(estadoId);
//		if (estado == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		manager.remove(estado);
//	}
//}