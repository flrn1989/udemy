package udemy.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import udemy.curso.categoria.CategoriaService;

/** Aplicacao */
@SpringBootApplication
public class Aplicacao implements CommandLineRunner {

	@Autowired
	private CategoriaService categoriaService;

	/** @param args */
	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);
	}

	/** {@inheritDoc} */
	@Override
	public void run(String... args) throws Exception {
		popularBaseDeDadosDeTeste();
	}

	private Aplicacao popularBaseDeDadosDeTeste() {

		categoriaService.popularBaseDeDadosDeTeste();

		return this;
	}

}