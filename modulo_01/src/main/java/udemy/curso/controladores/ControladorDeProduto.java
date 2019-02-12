/**
 * 
 */
package udemy.curso.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Produto;
import udemy.curso.dto.ProdutoDTO;

/** Controlador do domínio Produto. */
@RestController
@RequestMapping(value = "/produtos")
public class ControladorDeProduto extends ControladorDeDominio<Produto, ProdutoDTO> {

}