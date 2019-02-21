/**
 * 
 */
package stefanini.rh.selecao.mb;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import stefanini.rh.selecao.service.impl.TesteService;

/**
 * 
 */
@ManagedBean
public class TesteController {

	@EJB
	private TesteService servico;

}
