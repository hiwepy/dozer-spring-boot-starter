package org.dozer.spring.boot;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.CustomConverter;
import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerEventListener;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@ConditionalOnProperty(prefix = DozerProperties.PREFIX, value = "enabled", havingValue = "true")
@ConditionalOnClass({ DozerBeanMapper.class, DozerBeanMapperFactoryBean.class })
@EnableConfigurationProperties({ DozerProperties.class })
public class DozerAutoConfiguration {

	//spring 资源路径匹配解析器
	//“classpath”： 用于加载类路径（包括jar包）中的一个且仅一个资源；对于多个匹配的也只返回一个，所以如果需要多个匹配的请考虑“classpath*:”前缀
	//“classpath*”： 用于加载类路径（包括jar包）中的所有匹配的资源。带通配符的classpath使用“ClassLoader”的“Enumeration<URL> getResources(String name)”
	//方法来查找通配符之前的资源，然后通过模式匹配来获取匹配的资源。
	protected static ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	
	@Bean
	@ConditionalOnMissingBean
	public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(DozerProperties properties,
			@Autowired(required = false) List<CustomConverter> customConverters,
			@Autowired(required = false) CustomFieldMapper customFieldMapper,
			@Autowired(required = false) List<DozerEventListener> eventListeners) throws IOException {

		DozerBeanMapperFactoryBean factory = new DozerBeanMapperFactoryBean();

		factory.setCustomConverters(customConverters);
		factory.setCustomFieldMapper(customFieldMapper);
		factory.setEventListeners(eventListeners);

		if (ArrayUtils.isNotEmpty(properties.getMappingFiles())) {
			factory.setMappingFiles(resolver.getResources(StringUtils.join(properties.getMappingFiles(), ",")));
		}

		return factory;
	}

	@Bean
	@ConditionalOnMissingBean
	public DozerBeanMapper beanMapper(DozerProperties properties,
			@Autowired(required = false) List<CustomConverter> customConverters,
			@Autowired(required = false) CustomFieldMapper customFieldMapper,
			@Autowired(required = false) List<DozerEventListener> eventListeners) throws Exception {
		return (DozerBeanMapper) (dozerBeanMapperFactoryBean(properties, customConverters, customFieldMapper,
				eventListeners).getObject());
	}

}
