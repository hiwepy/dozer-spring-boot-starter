package org.dozer.spring.boot.converters;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;
@Component
public final class RmbStringDozerConverter implements CustomConverter {
	
	
	private String toRMB(double money) {
    	char[] s1 = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
    	char[] s4 = {'分', '角', '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟', '万'};
    	String str = String.valueOf(Math.round(money * 100 + 0.00001));
    	String result = "";

    	for (int i = 0; i < str.length(); i++) {
    		int n = str.charAt(str.length() - 1 - i) - '0';
    		result = s1[n] + "" + s4[i] + result;
    	}

    	result = result.replaceAll("零仟", "零");
    	result = result.replaceAll("零佰", "零");
    	result = result.replaceAll("零拾", "零");
    	result = result.replaceAll("零亿", "亿");
    	result = result.replaceAll("零万", "万");
    	result = result.replaceAll("零元", "元");
    	result = result.replaceAll("零角", "零");
    	result = result.replaceAll("零分", "零");

    	result = result.replaceAll("零零", "零");
    	result = result.replaceAll("零亿", "亿");
    	result = result.replaceAll("零零", "零");
    	result = result.replaceAll("零万", "万");
    	result = result.replaceAll("零零", "零");
    	result = result.replaceAll("零元", "元");
    	result = result.replaceAll("亿万", "亿");

    	result = result.replaceAll("零$", "");
    	result = result.replaceAll("元$", "元整");

    	return result;
    }

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
		//Number to RMB String
		if (sourceClass.equals(Number.class)&& sourceFieldValue instanceof Number) {
			try {
				return this.toRMB((Double) sourceFieldValue);
			} catch (SecurityException e) {
				throw new MappingException(e);
			} catch (IllegalArgumentException e) {
				throw new MappingException(e);
			}
		}
		throw new MappingException( "Converter RmbStringDozerConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}