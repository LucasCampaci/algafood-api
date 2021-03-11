package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long cozinhaId){
        return restauranteRepository.buscar(cozinhaId);
    }

    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha() != null ? restaurante.getCozinha().getId() : 0;
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if(cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public void remover(Long restauranteId) {
        try {
            restauranteRepository.remover(restauranteId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante de código %d não pode ser removido, pois está em uso", restauranteId));
        }
    }
}
