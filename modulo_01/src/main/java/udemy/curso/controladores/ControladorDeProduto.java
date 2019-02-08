/**
 * 
 */
package udemy.curso.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Produto;

/** Controlador do domínio Produto. */
@RestController
@RequestMapping(value = "/produtos")
public class ControladorDeProduto extends ControladorDeDominio<Produto> {

}