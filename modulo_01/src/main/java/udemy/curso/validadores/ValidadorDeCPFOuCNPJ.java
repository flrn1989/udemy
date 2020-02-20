package udemy.curso.validadores;

import java.util.InputMismatchException;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import udemy.curso.interfaces.anotacoes.CPFOuCNPJ;

public class ValidadorDeCPFOuCNPJ implements ConstraintValidator<CPFOuCNPJ, String> {

	private static final int TAM_CPF = 11;
	private String documento;
	private ConstraintValidatorContext contexto;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		this.documento = value;
		this.contexto = context;
		if (Objects.isNull(documento)) {
			invalidarDocumentoNulo();
			return false;
		}
		boolean documentoValido = documentoRepresentaCPF() ? isCPF(documento) : isCNPJ(documento);
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
		contexto
				.buildConstraintViolationWithTemplate(msgb.toString())
				.addConstraintViolation();
	}

	public boolean isCPF(String cpf) {

		cpf = removerCaracteresEspeciais(cpf);

		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != TAM_CPF)) {
			return (false);
		}

		char dig10;
		char dig11;
		int sm;
		int i;
		int r;
		int num;
		int peso;

		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (cpf.charAt(i) - 48);
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
				num = (cpf.charAt(i) - 48);
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
	}

	public boolean isCNPJ(String cnpj) {

		cnpj = removerCaracteresEspeciais(cnpj);

		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
				|| cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
				|| cnpj.equals("99999999999999") || (cnpj.length() != 14)) {
			return (false);
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

	private String removerCaracteresEspeciais(String doc) {
		doc = doc.replace(".", StringUtils.EMPTY);
		doc = doc.replace("-", StringUtils.EMPTY);
		doc = doc.replace("/", StringUtils.EMPTY);
		return doc;
	}

}
