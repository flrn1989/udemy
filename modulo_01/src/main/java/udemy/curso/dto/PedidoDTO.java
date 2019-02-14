/**
 * 
 */
package udemy.curso.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import udemy.curso.dominios.Pedido;
import udemy.curso.interfaces.DTO;

/** DTO de Pedido. */
public class PedidoDTO implements DTO<Pedido> {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime instante;

	/** Construtor. */
	public PedidoDTO() {
	}

	/** Construtor. */
	public PedidoDTO(Pedido pedido) {
		this.instante = pedido.getInstante();
	}

	/** {@inheritDoc} */
	@Override
	public Pedido paraDominio() {
		return new Pedido(this);
	}

	/** {@inheritDoc} */
	@Override
	public Pedido paraDominio(Pedido dominio) {

		if (Objects.isNull(dominio)) {
			return paraDominio();
		}

		dominio.setInstante(Optional.ofNullable(this.getInstante())
				.orElse(dominio.getInstante()));

		return dominio;
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
