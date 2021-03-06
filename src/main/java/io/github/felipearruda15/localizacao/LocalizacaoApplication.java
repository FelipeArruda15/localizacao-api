package io.github.felipearruda15.localizacao;

import io.github.felipearruda15.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		cidadeService.listarCidadesPorNomeSQL();
	}
}
