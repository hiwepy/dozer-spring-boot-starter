package org.dozer.spring.boot.converters.number;

import java.math.BigDecimal;

import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

public final class BigDecimalStringConverter implements CustomConverter {
	
	private final BigDecimalConverter converter = new BigDecimalConverter();
	
	/**
	 * destinationFieldValue：目标字段值
	 * sourceFieldValue：源字段值
	 * destinationClass:目标字段类型
	 * sourceClass：源字段类型
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