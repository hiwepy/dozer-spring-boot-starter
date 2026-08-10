/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.dozer.spring.boot;

import org.dozer.DozerBeanMapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link DozerAutoConfiguration }}.
 *
 * <p>Verifies the auto-configuration registers its declared mapper beans in the
 * application context.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("DozerAutoConfiguration Tests")
class DozerAutoConfigurationTest {

    private final ApplicationContextRunner runner = new ApplicationContextRunner();

    @Test
    @DisplayName("Auto-configuration class can be instantiated")
    void testInstantiation() {
        DozerAutoConfiguration configuration = new DozerAutoConfiguration();
        assertThat(configuration).isNotNull();
    }

    @Test
    @DisplayName("Auto-configuration should register a DozerBeanMapperFactoryBean")
    void shouldRegisterFactoryBean() {
        runner.withUserConfiguration(DozerAutoConfiguration.class)
                .run(context -> assertThat(context).hasSingleBean(DozerBeanMapperFactoryBean.class));
    }

    @Test
    @DisplayName("Auto-configuration should register a DozerBeanMapper bean")
    void shouldRegisterBeanMapper() {
        runner.withUserConfiguration(DozerAutoConfiguration.class)
                .run(context -> assertThat(context).hasSingleBean(DozerBeanMapper.class));
    }

    @Test
    @DisplayName("Auto-configuration should register DozerProperties")
    void shouldRegisterProperties() {
        runner.withUserConfiguration(DozerAutoConfiguration.class)
                .run(context -> assertThat(context).hasSingleBean(DozerProperties.class));
    }

    @Test
    @DisplayName("Factory bean should be created with empty mapping files")
    void factoryBeanWithEmptyMappingFiles() {
        runner.withUserConfiguration(DozerAutoConfiguration.class)
                .run(context -> assertThat(context.getBean(DozerBeanMapperFactoryBean.class)).isNotNull());
    }

    @Test
    @DisplayName("Factory bean should resolve configured mapping files resources")
    void factoryBeanWithMappingFiles() {
        runner.withUserConfiguration(DozerAutoConfiguration.class)
                .withPropertyValues("spring.dozer.mapping-files=classpath*:/dozer-*.xml")
                .run(context -> assertThat(context).hasSingleBean(DozerBeanMapperFactoryBean.class));
    }

}
