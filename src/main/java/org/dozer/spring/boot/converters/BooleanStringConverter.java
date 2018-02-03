package org.dozer.spring.boot.converters;

import org.apache.commons.beanutils.converters.BooleanConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

public final class BooleanStringConverter implements CustomConverter {
	
	private final BooleanConverter converter = new BooleanConverter();
     
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
		//String to Boolean
		if (sourceClass.equals(String.class)&&sourceFieldValue instanceof String) {
			return converter.convert(String.class, sourceFieldValue);
		}
		//Boolean to String
		if (sourceClass.equals(Boolean.class)&&sourceFieldValue instanceof Boolean) {
			return Boolean.parseBoolean((String) sourceFieldValue);
		}
		throw new MappingException( "Converter DateStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}