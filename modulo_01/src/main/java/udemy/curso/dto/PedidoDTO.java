/**
 * 
 */
package udemy.curso.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import udemy.curso.dominios.Pedido;
import udemy.curso.interfaces.DTO;

/** DTO de Pedido. */
public class PedidoDTO implements DTO {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime instante;

	/** Construtor. */
	public PedidoDTO() {
	}

	/** Construtor. */
	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.instante = pedido.getInstante();
	}

	/** @return the id */
	public Integer getId() {
		return id;
	}

	/** @param id the id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return the instante */
	public LocalDateTime getInstante() {
		return instante;
	}

	/** @param instante the instante to set */
	public void setInstante(LocalDateTime instante) {
		this.instante = instante;
	}

}
