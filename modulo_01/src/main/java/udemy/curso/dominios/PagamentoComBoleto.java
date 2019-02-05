/**
 * 
 */
package udemy.curso.dominios;

import java.time.LocalDate;

import udemy.curso.dominios.enums.EstadoDePagamento;

/** Especialização do domínio Pagamento. */
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	private LocalDate vencimento;

	private LocalDate realizacao;

	/** Construtor padrão. */
	public PagamentoComBoleto() {
	}

	/** @param estadoPagamento
	 * @param pedido
	 * @param vencimento
	 * @param realizacao */
	public PagamentoComBoleto(EstadoDePagamento estadoPagamento, Pedido pedido,
			LocalDate vencimento,
			LocalDate realizacao) {
		super(estadoPagamento, pedido);
		this.vencimento = vencimento;
		this.realizacao = realizacao;
	}

	/** @return the vencimento */
	public LocalDate getVencimento() {
		return vencimento;
	}

	/** @param vencimento the vencimento to set */
	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	/** @return the realizacao */
	public LocalDate getRealizacao() {
		return realizacao;
	}

	/** @param realizacao the realizacao to set */
	public void setRealizacao(LocalDate realizacao) {
		this.realizacao = realizacao;
	}

}