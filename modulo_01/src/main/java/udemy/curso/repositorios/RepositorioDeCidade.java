/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.curso.dominios.Cidade;

/** Repositório de Cidade. */
@Repository
public interface RepositorioDeCidade extends JpaRepository<Cidade, Integer> {

}
