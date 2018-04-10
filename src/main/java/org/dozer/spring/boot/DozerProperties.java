package org.dozer.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DozerProperties.PREFIX)
public class DozerProperties {

	public static final String PREFIX = "spring.dozer";
	
	/** Whether Enable Dozer. */
	private boolean enabled = false;
	/** The Spring resource definition. i.e. classpath*:/*.dozer.xml */
	private String[] mappingFiles;
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String[] getMappingFiles() {
		return mappingFiles;
	}
	public void setMappingFiles(String[] mappingFiles) {
		this.mappingFiles = mappingFiles;
	}
	
	
}