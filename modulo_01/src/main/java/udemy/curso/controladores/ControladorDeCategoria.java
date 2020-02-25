package udemy.curso.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udemy.curso.dominios.Categoria;
import udemy.curso.dto.CategoriaDTO;

@RestController
@RequestMapping(value = "/categorias")
public class ControladorDeCategoria extends ControladorDeDominio<Categoria, CategoriaDTO> {

}