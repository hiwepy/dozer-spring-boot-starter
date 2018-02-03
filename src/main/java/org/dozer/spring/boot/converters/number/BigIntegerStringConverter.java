package org.dozer.spring.boot.converters.number;

import java.math.BigInteger;

import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

public final class BigIntegerStringConverter implements CustomConverter {
	
	private final BigIntegerConverter converter = new BigIntegerConverter();
     
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
		//String to BigInteger
		if (sourceClass.equals(String.class)&&sourceFieldValue instanceof String) {
			return converter.convert(String.class, sourceFieldValue);
		}
		//BigInteger to String
		if (sourceClass.equals(BigInteger.class)&&sourceFieldValue instanceof BigInteger) {
			return ((BigInteger) sourceFieldValue).toString();
		}
		throw new MappingException( "Converter DateStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}