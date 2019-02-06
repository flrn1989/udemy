/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.Pagamento;

/** Repositório do domínio Pagamento. */
@Repository
public interface RepositorioDePagamento extends JpaRepository<Pagamento, Integer> {

}
