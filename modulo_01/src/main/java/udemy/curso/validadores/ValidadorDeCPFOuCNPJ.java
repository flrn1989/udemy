package udemy.curso.validadores;

import java.util.InputMismatchException;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import udemy.curso.interfaces.anotacoes.CPFOuCNPJ;

public class ValidadorDeCPFOuCNPJ implements ConstraintValidator<CPFOuCNPJ, String> {

	private static final int TAM_CPF = 11;
	private static final int TAM_CNPJ = 14;
	private String documento;
	private ConstraintValidatorContext contexto;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		this.documento = StringUtils.normalizeSpace(value);
		this.contexto = context;
		if (Objects.isNull(documento)) {
			invalidarDocumentoNulo();
			return false;
		}
		boolean documentoValido = documentoRepresentaCPF() ? eCPFValido() : eCNPJValido();
		if (!documentoValido) {
			invalidarCPFOuCNPJ();
		}
		return documentoValido;
	}

	private void invalidarDocumentoNulo() {
		contexto.buildConstraintViolationWithTemplate("O documento não deve ser vazio.")
				.addConstraintViolation();
	}

	private boolean documentoRepresentaCPF() {
		return Objects.nonNull(documento) && TAM_CPF >= documento.length();
	}

	private void invalidarCPFOuCNPJ() {
		contexto.disableDefaultConstraintViolation();
		StringBuilder msgb = new StringBuilder();
		msgb.append(documentoRepresentaCPF() ? "CPF" : "CNPJ");
		msgb.append(" de número inválido: ");
		msgb.append(documento);
		contexto.buildConstraintViolationWithTemplate(msgb.toString())
				.addConstraintViolation();
	}

	public boolean eCPFValido() {
		removerCaracteresEspeciais();
		String cpf = documento;
		if (todosCaracteresSaoIguais() || documentoDeTamanhoDiferenteDeCPF()) {
			return false;
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}

//		char dig10;
//		char dig11;
//		int sm;
//		int i;
//		int r;
//		int num;
//		int peso;
//
//		try {
//			// Calculo do 1o. Digito Verificador
//			sm = 0;
//			peso = 10;
//			for (i = 0; i < 9; i++) {
//				// converte o i-esimo caractere do CPF em um numero:
//				// por exemplo, transforma o caractere '0' no inteiro 0
//				// (48 eh a posicao de '0' na tabela ASCII)
//				num = (cpf.charAt(i) - 48);
//				sm = sm + (num * peso);
//				peso = peso - 1;
//			}
//
//			r = 11 - (sm % 11);
//			if ((r == 10) || (r == 11))
//				dig10 = '0';
//			else
//				dig10 = (char) (r + 48); // converte no respectivo caractere numerico
//
//			// Calculo do 2o. Digito Verificador
//			sm = 0;
//			peso = 11;
//			for (i = 0; i < 10; i++) {
//				num = (cpf.charAt(i) - 48);
//				sm = sm + (num * peso);
//				peso = peso - 1;
//			}
//
//			r = 11 - (sm % 11);
//			if ((r == 10) || (r == 11))
//				dig11 = '0';
//			else
//				dig11 = (char) (r + 48);
//
//			// Verifica se os digitos calculados conferem com os digitos informados.
//			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
//				return (true);
//			else
//				return (false);
//		} catch (InputMismatchException erro) {
//			return (false);
//		}
	}

	public boolean eCNPJValido() {
		removerCaracteresEspeciais();
		String cnpj = documento;
		if (todosCaracteresSaoIguais() || documentoDeTamanhoDiferenteDeCNPJ()) {
			return false;
		}

		char dig13;
		char dig14;
		int sm;
		int i;
		int r;
		int num;
		int peso;

		try {
			// Calculo do 1o. Digito Verificador
			dig13 = calcularDig13(cnpj);

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);

			// Verifica se os dígitos calculados conferem com os dígitos informados.
			if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private char calcularDig13(String cnpj) {
		int sm = 0;
		int peso = 2;
		for (int i = 11; i >= 0; i--) {
			// converte o i-ésimo caractere do CNPJ em um número:
			// por exemplo, transforma o caractere '0' no inteiro 0
			// (48 eh a posição de '0' na tabela ASCII)
			int num = (cnpj.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso + 1;
			if (peso == 10)
				peso = 2;
		}
		int r = sm % 11;
		return ((r == 0) || (r == 1)) ? '0' : (char) ((11 - r) + 48);
	}

	private void removerCaracteresEspeciais() {
		this.documento = StringUtils.remove(documento, ".");
		this.documento = StringUtils.remove(documento, "-");
		this.documento = StringUtils.remove(documento, "/");
	}

	private boolean todosCaracteresSaoIguais() {
		String primeiroDigito = StringUtils.firstNonBlank(documento);
		String digitosDiferentes = StringUtils.remove(documento, primeiroDigito);
		return StringUtils.isBlank(digitosDiferentes);
	}

	private boolean documentoDeTamanhoDiferenteDeCPF() {
		return !documentoDeTamanhoDeCPF();
	}

	private boolean documentoDeTamanhoDeCPF() {
		return Objects.nonNull(documento) && TAM_CPF == documento.length();
	}

	private boolean documentoDeTamanhoDiferenteDeCNPJ() {
		return !documentoDeTamanhoDeCNPJ();
	}

	private boolean documentoDeTamanhoDeCNPJ() {
		return Objects.nonNull(documento) && TAM_CNPJ == documento.length();
	}

}
