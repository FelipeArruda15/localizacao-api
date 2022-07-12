package io.github.felipearruda15.localizacao;

import io.github.felipearruda15.localizacao.domain.entity.Cidade;
import io.github.felipearruda15.localizacao.domain.repository.CidadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

	@Autowired
	private CidadeRepo cidadeRepo;

	@Transactional
	void salvarCidade(){
		var cidade = new Cidade(1L, "São Paulo", 12396372L);
		cidadeRepo.save(cidade);
	}

	void listarCidades(){
		cidadeRepo.findAll().forEach(System.out::println);
	}

	@Override
	public void run(String... args) throws Exception {
		salvarCidade();
		listarCidades();
	}
}
