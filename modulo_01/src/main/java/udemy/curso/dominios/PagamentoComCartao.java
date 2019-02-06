/**
 * 
 */
package udemy.curso.dominios;

import javax.persistence.Entity;

import udemy.curso.dominios.enums.EstadoDePagamento;

/** Especialização do domínio Pagamento. */
@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	/** Construtor padrão. */
	public PagamentoComCartao() {
	}

	/** @param estadoPagamento
	 * @param pedido
	 * @param numeroDeParcelas */
	public PagamentoComCartao(EstadoDePagamento estadoPagamento, Pedido pedido,
			Integer numeroDeParcelas) {
		super(estadoPagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	/** @return the numeroDeParcelas */
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	/** @param numeroDeParcelas the numeroDeParcelas to set */
	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
