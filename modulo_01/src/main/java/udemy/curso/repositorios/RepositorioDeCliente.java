/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.Cliente;

/** Repositório de Cliente */
@Repository
public interface RepositorioDeCliente extends JpaRepository<Cliente, Integer> {

}
