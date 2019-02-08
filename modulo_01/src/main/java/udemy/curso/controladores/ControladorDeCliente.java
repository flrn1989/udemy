/**
 * 
 */
package udemy.curso.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Cliente;

/** Controlador do domínio Cliente. */
@RestController
@RequestMapping(value = "/clientes")
public class ControladorDeCliente extends ControladorDeDominio<Cliente> {

}