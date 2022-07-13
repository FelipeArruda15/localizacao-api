package io.github.felipearruda15.localizacao.domain.repository;

import io.github.felipearruda15.localizacao.domain.entity.Cidade;
import io.github.felipearruda15.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepo extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

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

    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nm_cidade as nome from tb_cidade as c where c.nm_cidade = :nome")
    List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);
}
