/**
 * 
 */
package udemy.curso.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repositório de Produto. */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
