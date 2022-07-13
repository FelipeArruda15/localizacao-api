package io.github.felipearruda15.localizacao.service;

import io.github.felipearruda15.localizacao.domain.entity.Cidade;
import io.github.felipearruda15.localizacao.domain.repository.CidadeRepo;
import io.github.felipearruda15.localizacao.domain.repository.specs.CidadeSpecs;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
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


    public void listarCidadesByNomeSpec(){
        Specification<Cidade> spec = CidadeSpecs.nomeIgual("São Paulo").and(CidadeSpecs.habitantesMenorOuIgual(78787900L));
        cidadeRepo.findAll(spec).forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (filtro.getNome() != null){
            specs = specs.and(CidadeSpecs.nomeLike(filtro.getNome()));
        }

        cidadeRepo.findAll(specs).forEach(System.out::println);
    }

    public void listarCidadesPorNomeSQL(){
        cidadeRepo
                .findByNomeSqlNativo("São Paulo")
                .stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }
}
