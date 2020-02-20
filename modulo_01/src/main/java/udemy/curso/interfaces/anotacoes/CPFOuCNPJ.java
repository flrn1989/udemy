package udemy.curso.interfaces.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import udemy.curso.validadores.ValidadorDeCPFOuCNPJ;

@Constraint(validatedBy = ValidadorDeCPFOuCNPJ.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CPFOuCNPJ {

	String message() default "Valor inválido para o campo.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};

}
