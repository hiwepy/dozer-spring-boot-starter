package org.dozer.spring.boot.converters.number;

import java.math.BigInteger;

import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;

/**
 * Dozer {@link CustomConverter} that converts between {@link String} and
 * {@link BigInteger} values.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Component
/**
 * <p>Auto-configuration for BigIntegerStringConverter.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public final class BigIntegerStringConverter implements CustomConverter {

	private final BigIntegerConverter converter = new BigIntegerConverter();

	/**
	 * Converts the source value between {@link String} and {@link BigInteger}.
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
		//String to BigInteger
		if (sourceClass.equals(String.class)&&sourceFieldValue instanceof String) {
			return converter.convert(String.class, sourceFieldValue);
		}
		//BigInteger to String
		if (sourceClass.equals(BigInteger.class)&&sourceFieldValue instanceof BigInteger) {
			return ((BigInteger) sourceFieldValue).toString();
		}
		throw new MappingException( "Converter BigIntegerStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}