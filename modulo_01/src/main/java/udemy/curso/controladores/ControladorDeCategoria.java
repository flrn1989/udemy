/**
 * 
 */
package udemy.curso.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Categoria;

/** Controlador do domínio Categoria. */
@RestController
@RequestMapping(value = "/categorias")
public class ControladorDeCategoria extends ControladorDeDominio<Categoria> {

}