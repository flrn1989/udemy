/**
 * 
 */
package udemy.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import udemy.curso.dominios.Endereco;

/** Repositório de Endereço */
public interface RepositorioDeEndereco extends JpaRepository<Endereco, Integer> {

}
