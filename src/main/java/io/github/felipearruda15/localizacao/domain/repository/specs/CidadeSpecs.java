package io.github.felipearruda15.localizacao.domain.repository.specs;

import io.github.felipearruda15.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    public static Specification<Cidade> nomeIgual(String nome){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> habitantesMenorOuIgual(Long qtdHabitantes){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("habitantes"), qtdHabitantes);
    }

    public static Specification<Cidade> habitantesBetwenn(Long min, Long max){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("habitantes"), min, max);
    }

    public static Specification<Cidade> nomeLike(String nome){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + nome + "%".toUpperCase());
    }
}
