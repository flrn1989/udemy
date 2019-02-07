/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.ProdutoPedido;
import udemy.curso.dominios.embeddedids.ProdutoPedidoId;

/** Repositório de ProdutoPedido. */
@Repository
public interface RepositorioDeProdutoPedido extends JpaRepository<ProdutoPedido, ProdutoPedidoId> {

}
