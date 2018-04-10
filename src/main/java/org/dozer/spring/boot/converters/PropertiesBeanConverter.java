package org.dozer.spring.boot.converters;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Properties;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
@Component
public class PropertiesBeanConverter implements CustomConverter  {

	/**
	 * 转换接口实现 
	 * @param destinationFieldValue：目标字段值
	 * @param sourceFieldValue：源字段值
	 * @param destinationClass:目标字段类型
	 * @param sourceClass：源字段类型
	 * @return 转换后的结果
	 */
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		//Properties to JavaBean
		if (sourceClass.equals(Properties.class)&& sourceFieldValue instanceof Properties ) {
			try {
				Properties properties = (Properties) sourceFieldValue;
				BeanInfo beanInfo = Introspector.getBeanInfo(destinationClass);
				Object object = (destinationFieldValue!=null)?destinationFieldValue: destinationClass.newInstance();// 创建JavaBean对象
				Iterator<Object> ite = properties.keySet().iterator();
				while (ite.hasNext()) {
					String key = (String) ite.next();
					//BeanUtils.setProperty(object, entry.getKey(), entry.getValue());
					// 给JavaBean对象的属性赋值
					for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
						String propertyName = descriptor.getName();
						if (propertyName.equalsIgnoreCase(key)) {
							descriptor.getWriteMethod().invoke(object,properties.getProperty(key));
						}
					}
				}
				return object;
			}catch (Exception e) {
				throw new MappingException(e);
			}
		}
		//JavaBean to Properties
		if (!sourceClass.equals(Properties.class)&& sourceFieldValue instanceof Object ) {
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(sourceClass);
				Properties properties = ((destinationFieldValue!=null)?(Properties) destinationFieldValue:new Properties());
				for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
					String propertyName = descriptor.getName();
					properties.put(propertyName, descriptor.getReadMethod().invoke(sourceFieldValue));
				}
				return properties;
			}catch (Exception e) {
				throw new MappingException(e);
			}
		}
		throw new MappingException( "Converter PropertiesBeanConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
	
}



