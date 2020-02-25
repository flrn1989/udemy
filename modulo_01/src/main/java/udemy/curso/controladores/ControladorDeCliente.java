package udemy.curso.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Cliente;
import udemy.curso.dto.ClienteDTO;

@RestController
@RequestMapping(value = "/clientes")
public class ControladorDeCliente extends ControladorDeDominio<Cliente, ClienteDTO> {

}