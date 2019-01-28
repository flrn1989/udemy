/**
 * 
 */
package udemy.curso;

/** "A busca realizada não obteve resultado." */
public class ExcecaoDeBuscaVazia extends Throwable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "A busca realizada não obteve resultado.";
	}

}
