package udemy.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import udemy.curso.dominios.Categoria;
import udemy.curso.dominios.Cidade;
import udemy.curso.dominios.Cliente;
import udemy.curso.dominios.Endereco;
import udemy.curso.dominios.Estado;
import udemy.curso.dominios.Produto;
import udemy.curso.dominios.enums.TipoCliente;
import udemy.curso.repositorios.RepositorioDeCategoria;
import udemy.curso.repositorios.RepositorioDeCidade;
import udemy.curso.repositorios.RepositorioDeCliente;
import udemy.curso.repositorios.RepositorioDeEndereco;
import udemy.curso.repositorios.RepositorioDeEstado;
import udemy.curso.repositorios.RepositorioDeProduto;

/** Aplicacao */
@SpringBootApplication
public class Aplicacao implements CommandLineRunner {

	@Autowired
	private RepositorioDeCategoria repositorioDeCategoria;

	@Autowired
	private RepositorioDeProduto repositorioDeProduto;

	@Autowired
	private RepositorioDeCidade repositorioDeCidade;

	@Autowired
	private RepositorioDeEstado repositorioDeEstado;

	@Autowired
	private RepositorioDeCliente repositorioDeCliente;

	@Autowired
	private RepositorioDeEndereco repositorioDeEndereco;

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

		Categoria categoria1 = new Categoria("Militar");
		Categoria categoria2 = new Categoria("Policial");
		Categoria categoria3 = new Categoria("Direito");

		Produto produto1 = new Produto("Vade Mecum", 30.00);
		Produto produto2 = new Produto("Algemas", 20.00);
		Produto produto3 = new Produto("Uniforme", 10.00);

		produto1.getCategorias().add(
				categoria3);
		produto2.getCategorias().addAll(Arrays.asList(
				categoria1,
				categoria2,
				categoria3));
		produto3.getCategorias().add(
				categoria1);

		repositorioDeCategoria.saveAll(Arrays.asList(
				categoria1,
				categoria2,
				categoria3));

		repositorioDeProduto.saveAll(Arrays.asList(
				produto1,
				produto2,
				produto3));

		Estado estado1 = new Estado("Rio Grande do Sul");
		Estado estado2 = new Estado("Goiás");

		Cidade cidade1 = new Cidade("Gramado", estado1);
		Cidade cidade2 = new Cidade("Canela", estado1);
		Cidade cidade3 = new Cidade("Posse", estado2);

		repositorioDeEstado.saveAll(Arrays.asList(
				estado1,
				estado2));

		repositorioDeCidade.saveAll(Arrays.asList(
				cidade1,
				cidade2,
				cidade3));

		Endereco endereco1 = new Endereco("Rua Alfa Quadra 1", "101",
				"Conjunto A", "Primeiro", "55888123", cidade1);
		Endereco endereco2 = new Endereco("Rua Beta Quadra 2", "201",
				"Conjunto B", "Segundo", "55888234", cidade2);

		Cliente cliente1 = new Cliente("Pedro Farias", "pedrof@gmail.com",
				"77788899900", TipoCliente.PF);

		cliente1.getTelefones().addAll(Arrays.asList(
				"44445555",
				"988886666"));
		cliente1.getEnderecos().addAll(Arrays.asList(
				endereco1,
				endereco2));
		endereco1.setCliente(cliente1);
		endereco2.setCliente(cliente1);

		repositorioDeCliente.saveAll(Arrays.asList(
				cliente1));

		repositorioDeEndereco.saveAll(Arrays.asList(
				endereco1,
				endereco2));

		return this;
	}

}