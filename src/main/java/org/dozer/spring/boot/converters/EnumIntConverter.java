package org.dozer.spring.boot.converters;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

public class EnumIntConverter implements CustomConverter {

	/**
	 * destinationFieldValue：目标字段值
	 * sourceFieldValue：源字段值
	 * destinationClass:目标字段类型
	 * sourceClass：源字段类型
	 */
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null){
			return null;
		}
		//Enum to int
		if (sourceClass.isEnum()&sourceFieldValue instanceof Enum) {
			Object dest = null;
			if (destinationFieldValue == null){
				dest = Integer.valueOf(((Enum) sourceFieldValue).ordinal());
			}else{
				dest = destinationFieldValue;
			}
			return dest;
		}
		//int to Enum
		if (sourceClass.equals(Integer.class)&&sourceFieldValue instanceof Integer){
			Object consts[] = destinationClass.getEnumConstants();
			return consts[((Integer) sourceFieldValue).intValue()];
		}
		throw new MappingException( "Converter DateStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}
