package org.dozer.spring.boot.converters.number;

import java.math.BigDecimal;

import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;

/**
 * Dozer {@link CustomConverter} that converts between {@link String} and
 * {@link BigDecimal} values.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Component
/**
 * <p>Auto-configuration for BigDecimalStringConverter.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public final class BigDecimalStringConverter implements CustomConverter {

	private final BigDecimalConverter converter = new BigDecimalConverter();

	/**
	 * Converts the source value between {@link String} and {@link BigDecimal}.
	 * @param destinationFieldValue the current destination field value
	 * @param sourceFieldValue the source field value to convert
	 * @param destinationClass the target field type
	 * @param sourceClass the source field type
	 * @return the converted result, or {@code null} if the source value is {@code null}
	 */
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		//String to BigDecimal
		if (sourceClass.equals(String.class)&&sourceFieldValue instanceof String) {
			return converter.convert(String.class, sourceFieldValue);
		}
		//BigDecimal to String
		if (sourceClass.equals(BigDecimal.class)&&sourceFieldValue instanceof BigDecimal ) {
			return ((BigDecimal) sourceFieldValue).toPlainString();
		}
		throw new MappingException( "Converter DateStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}