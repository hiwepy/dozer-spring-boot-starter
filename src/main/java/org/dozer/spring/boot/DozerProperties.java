package org.dozer.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DozerProperties.PREFIX)
public class DozerProperties {

	public static final String PREFIX = "spring.dozer";
	
	/** The Spring resource definition. i.e. classpath*:/*.dozer.xml */
	private String[] mappingFiles;
	
	public String[] getMappingFiles() {
		return mappingFiles;
	}
	public void setMappingFiles(String[] mappingFiles) {
		this.mappingFiles = mappingFiles;
	}
	
	
}