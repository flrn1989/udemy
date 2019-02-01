/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.Categoria;

/** Repositório do domínio Categoria. */
@Repository
public interface RepositorioDeCategoria extends JpaRepository<Categoria, Integer> {

}
