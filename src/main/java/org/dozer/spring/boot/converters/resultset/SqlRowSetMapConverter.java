package org.dozer.spring.boot.converters.resultset;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

public final class SqlRowSetMapConverter implements CustomConverter {
	
	/**
	 * 从SqlRowSet中的到查询结果的列
	 * @param rs {@link SqlRowSet} 结果集
	 * @return  列名称集合
	 * @throws SQLException
	 */
	public String[] getColNames(SqlRowSet rs) throws SQLException {
		SqlRowSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		String[] colNames = new String[count];
		for (int i = 1; i <= count; i++) {
			colNames[i - 1] = rsmd.getColumnLabel(i);
		}
		return colNames;
	}
	
	/**
	 * 转换接口实现 
	 * @param destinationFieldValue：目标字段值
	 * @param sourceFieldValue：源字段值
	 * @param destinationClass:目标字段类型
	 * @param sourceClass：源字段类型
	 * @return 转换后的结果
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}else{
			//SqlRowSet to Map
			if (sourceClass.equals(SqlRowSet.class)&& sourceFieldValue instanceof SqlRowSet) {
				try {
					// 获取类属性
					Map map = ((destinationFieldValue!=null)?(Map) destinationFieldValue:new HashMap());// 创建Map对象
					SqlRowSet rest = (SqlRowSet) sourceFieldValue;
					String[] colNames = getColNames(rest);
					while (rest.next()) {
						for (int i = 0; i < colNames.length; i++) {
							String colName = colNames[i].toLowerCase();
							map.put(colName, rest.getObject(colName));
						}
					}
					return map;
				} catch (Exception e) {
					throw new MappingException(e);
				}
			}
		}
		throw new MappingException("Converter SqlRowSetMapConverter used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
	}
}