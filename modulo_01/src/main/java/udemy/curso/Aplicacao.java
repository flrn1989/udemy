package udemy.curso;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import udemy.curso.categoria.Categoria;
import udemy.curso.categoria.CategoriaRepository;
import udemy.curso.produto.Produto;
import udemy.curso.produto.ProdutoRepository;

/** Aplicacao */
@SpringBootApplication
public class Aplicacao implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

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

		Produto produto1 = new Produto("Faca", 30.00);
		Produto produto2 = new Produto("Pistola", 20.00);
		Produto produto3 = new Produto("Algemas", 10.00);

		categoria1.getProdutos().add(produto3);
		categoria2.getProdutos().addAll(Arrays.asList(produto2, produto1, produto3));
		categoria3.getProdutos().add(produto1);

		produto1.getCategorias().add(categoria3);
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2, categoria3));
		produto3.getCategorias().add(categoria1);

		List<Categoria> categorias = Arrays.asList(
				categoria1,
				categoria2,
				categoria3);

		List<Produto> produtos = Arrays.asList(
				produto1,
				produto2,
				produto3);

		categoriaRepository.saveAll(categorias);
		produtoRepository.saveAll(produtos);

		return this;
	}

}