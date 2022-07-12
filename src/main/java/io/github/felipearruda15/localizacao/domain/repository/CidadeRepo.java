package io.github.felipearruda15.localizacao.domain.repository;

import io.github.felipearruda15.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepo extends JpaRepository<Cidade, Long> {

    List<Cidade> findByNome(String nome);

    List<Cidade> findByNomeStartingWith(String nome);

    List<Cidade> findByNomeEndingWith(String nome);

    List<Cidade> findByNomeContaining(String nome);

    @Query("select c from Cidade c where lower(c.nome) like lower(?1)")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    @Query("select c from Cidade c where lower(c.nome) like lower(?1)")
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findByHabitantesLessThanEqualAndNomeLike(Long habitantes, String nome);
}
