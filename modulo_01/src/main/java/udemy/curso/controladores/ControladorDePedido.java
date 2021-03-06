package udemy.curso.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Pedido;
import udemy.curso.dto.PedidoDTO;

@RestController
@RequestMapping(value = "/pedidos")
public class ControladorDePedido extends ControladorDeDominio<Pedido, PedidoDTO> {

}
