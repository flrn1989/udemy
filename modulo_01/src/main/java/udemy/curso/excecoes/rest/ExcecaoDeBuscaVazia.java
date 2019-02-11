/**
 * 
 */
package udemy.curso.excecoes.rest;

import org.springframework.http.HttpStatus;

/** "A busca realizada não obteve resultado." */
public class ExcecaoDeBuscaVazia extends ExcecaoRest {

	private static final long serialVersionUID = 1L;

	/** {@inheritDoc} */
	@Override
	public String getMessage() {
		return "A busca realizada não obteve resultado.";
	}

	/** {@inheritDoc} */
	@Override
	public HttpStatus httpStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
