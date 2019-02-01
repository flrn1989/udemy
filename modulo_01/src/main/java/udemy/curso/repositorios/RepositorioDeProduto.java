/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.Produto;

/** Repositório de Produto. */
@Repository
public interface RepositorioDeProduto extends JpaRepository<Produto, Integer> {

}
