/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.Estado;

/** Repositório de Estado. */
@Repository
public interface RepositorioDeEstado extends JpaRepository<Estado, Integer> {

}
