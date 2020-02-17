/**
 * 
 */
package udemy.curso.interfaces.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import udemy.curso.validadores.ValidadorDeCPFOuCNPJ;

/** Anotação para definir campo como validável para CPF ou CNPJ. */
@Constraint(validatedBy = ValidadorDeCPFOuCNPJ.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CPFOuCNPJ {

	/** Campo {@code message} da anotação {@code @Constraint}. */
	String message() default "Valor inválido para o campo.";

	/** Campo {@code groups} da anotação {@code @Constraint}. */
	Class<?>[] groups() default {};

	/** Campo {@code payload} da anotação {@code @Constraint}. */
	Class<?>[] payload() default {};

}
