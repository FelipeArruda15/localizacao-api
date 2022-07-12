package io.github.felipearruda15.localizacao.domain.repository;

import io.github.felipearruda15.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepo extends JpaRepository<Cidade, Long> {

}
