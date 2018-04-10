package org.dozer.spring.boot.converters;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
@Component
public class MapBeanConverter implements CustomConverter  {

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
		//Map to JavaBean
		if (sourceClass.equals(Map.class) && sourceFieldValue instanceof Map) {
			try {
				Map<String,Object> map = (Map<String, Object>) sourceFieldValue;
				BeanInfo beanInfo = Introspector.getBeanInfo(destinationClass);
				Object object = (destinationFieldValue!=null)?destinationFieldValue: destinationClass.newInstance();// 创建JavaBean对象
				Iterator<Map.Entry<String,Object>> ite = map.entrySet().iterator();
				while (ite.hasNext()) {
					Map.Entry<String,Object> entry = ite.next();
					//BeanUtils.setProperty(object, entry.getKey(), entry.getValue());
					// 给JavaBean对象的属性赋值
					for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
						String propertyName = descriptor.getName();
						if (propertyName.equalsIgnoreCase(entry.getKey())) {
							descriptor.getWriteMethod().invoke(object,entry.getValue());
						}
					}
				}
				return object;
			}catch (Exception e) {
				throw new MappingException(e);
			}
		}
		
		//JavaBean to Map
		if (!sourceClass.equals(Map.class)&& sourceFieldValue instanceof Map ) {
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(sourceClass);
				Map<String,Object> map = ((destinationFieldValue!=null)?(Map<String,Object>) destinationFieldValue:new HashMap<String,Object>());
				for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
					String propertyName = descriptor.getName();
					map.put(propertyName, descriptor.getReadMethod().invoke(sourceFieldValue));
				}
				return map;
			}catch (Exception e) {
				throw new MappingException(e);
			}
		}
		throw new MappingException( "Converter MapBeanConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
	
}



