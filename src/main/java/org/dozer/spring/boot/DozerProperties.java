package org.dozer.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Dozer bean mapper, bound to the {@code spring.dozer}
 * prefix.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@ConfigurationProperties(DozerProperties.PREFIX)
public class DozerProperties {

	public static final String PREFIX = "spring.dozer";

	/** The Spring resource definition. i.e. classpath*:/*.dozer.xml */
	private String[] mappingFiles;

	/**
	 * Returns the Dozer mapping file resource locations.
	 * @return the mapping file resource patterns, or {@code null} if none configured
	 */
	public String[] getMappingFiles() {
		return mappingFiles;
	}

	/**
	 * Sets the Dozer mapping file resource locations.
	 * @param mappingFiles the mapping file resource patterns
	 */
	public void setMappingFiles(String[] mappingFiles) {
		this.mappingFiles = mappingFiles;
	}


}