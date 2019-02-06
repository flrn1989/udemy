/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.Pedido;

/** Repositório do domínio Pedido. */
@Repository
public interface RepositorioDePedido extends JpaRepository<Pedido, Integer> {

}
