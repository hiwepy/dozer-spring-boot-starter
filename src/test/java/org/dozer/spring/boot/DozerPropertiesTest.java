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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link DozerProperties }}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("DozerProperties Tests")
class DozerPropertiesTest {

    @Test
    @DisplayName("PREFIX constant should be 'spring.dozer'")
    void prefixConstant() {
        assertThat(DozerProperties.PREFIX).isEqualTo("spring.dozer");
    }

    @Test
    @DisplayName("Default mappingFiles should be null")
    void defaultMappingFilesShouldBeNull() {
        DozerProperties props = new DozerProperties();
        assertThat(props.getMappingFiles()).isNull();
    }

    @Test
    @DisplayName("Getters and setters should round-trip mappingFiles")
    void mappingFilesRoundTrip() {
        DozerProperties props = new DozerProperties();
        String[] files = { "classpath*:/*.dozer.xml", "classpath*:/custom.xml" };
        props.setMappingFiles(files);
        assertThat(props.getMappingFiles()).containsExactly(files);
    }

    @Test
    @DisplayName("setMappingFiles(null) should clear the field")
    void setMappingFilesNull() {
        DozerProperties props = new DozerProperties();
        props.setMappingFiles(new String[] { "a.xml" });
        props.setMappingFiles(null);
        assertThat(props.getMappingFiles()).isNull();
    }

}
