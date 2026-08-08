package org.dozer.spring.boot.converters;

import org.apache.commons.beanutils.converters.BooleanConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;

/**
 * Dozer {@link CustomConverter} that converts between {@link String} and
 * {@link Boolean} values.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@Component
public final class BooleanStringConverter implements CustomConverter {

	private final BooleanConverter converter = new BooleanConverter();

	/**
	 * Converts the source value between {@link String} and {@link Boolean}.
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
		//String to Boolean
		if (sourceClass.equals(String.class)&&sourceFieldValue instanceof String) {
			return converter.convert(String.class, sourceFieldValue);
		}
		//Boolean to String
		if (sourceClass.equals(Boolean.class)&&sourceFieldValue instanceof Boolean) {
			return Boolean.parseBoolean((String) sourceFieldValue);
		}
		throw new MappingException( "Converter BooleanStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}