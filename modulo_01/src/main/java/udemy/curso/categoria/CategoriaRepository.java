/**
 * 
 */
package udemy.curso.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repositório do domínio Categoria. */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
