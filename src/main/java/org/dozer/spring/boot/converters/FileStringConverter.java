package org.dozer.spring.boot.converters;

import java.io.File;

import org.apache.commons.beanutils.converters.FileConverter;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

public final class FileStringConverter implements CustomConverter {
	
	private FileConverter converter = new FileConverter() ;
	
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
		//String to file
		if (sourceClass.equals(String.class)) {
			return converter.convert(String.class, sourceFieldValue);
		}
		//file to string
		if (sourceClass.equals(File.class)) {
			return ((File)sourceFieldValue).getAbsolutePath();
		}
		throw new MappingException( "Converter DateStringConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}