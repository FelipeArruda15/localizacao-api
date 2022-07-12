package io.github.felipearruda15.localizacao.service;

import io.github.felipearruda15.localizacao.domain.entity.Cidade;
import io.github.felipearruda15.localizacao.domain.repository.CidadeRepo;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepo cidadeRepo;

    public CidadeService(CidadeRepo cidadeRepo){
        this.cidadeRepo = cidadeRepo;
    }

    @Transactional
    public void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 12396372L);
        cidadeRepo.save(cidade);
    }

    public void listarCidadesPorQuantidadeHabitantes(){
        cidadeRepo
                .findByHabitantesLessThanEqualAndNomeLike(1000001L, "Br%")
                .forEach(System.out::println);
    }

    public void listarCidadesPorNome(){
        cidadeRepo
                .findByNomeLike("Porto%", Sort.by("habitantes", "nome"))
                .forEach(System.out::println);
    }

    public void listarCidadesPorNomePaginado(){
        Pageable pageable = PageRequest.of(1, 2);
        cidadeRepo
                .findByNomeLike("%%%%%", pageable)
                .forEach(System.out::println);
    }

    public void listarCidades(){
        cidadeRepo.findAll().forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();
        Example<Cidade> example = Example.of(cidade, matcher);
        return cidadeRepo.findAll(example);
    }
}
