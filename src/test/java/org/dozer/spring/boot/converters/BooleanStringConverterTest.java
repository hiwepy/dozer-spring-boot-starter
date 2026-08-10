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
package org.dozer.spring.boot.converters;

import org.dozer.MappingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {{ @link BooleanStringConverter }}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("BooleanStringConverter Tests")
class BooleanStringConverterTest {

    private final BooleanStringConverter converter = new BooleanStringConverter();

    @Test
    @DisplayName("Instance can be created via constructor")
    void instance() {
        assertThat(new BooleanStringConverter()).isNotNull();
    }

    @Test
    @DisplayName("convert should return null when source value is null")
    void convertNullSource() {
        Object result = converter.convert(null, null, String.class, String.class);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("convert should parse 'true' string source via the underlying BooleanConverter")
    void convertTrueString() {
        Object result = converter.convert(null, "true", String.class, String.class);
        assertThat(result).isEqualTo("true");
    }

    @Test
    @DisplayName("convert should parse 'false' string source via the underlying BooleanConverter")
    void convertFalseString() {
        Object result = converter.convert(null, "false", String.class, String.class);
        assertThat(result).isEqualTo("false");
    }

    @Test
    @DisplayName("convert should return a String form for a non-boolean string source")
    void convertNonBooleanString() {
        // The underlying BooleanConverter cannot parse 'yes' so it returns the default String form.
        Object result = converter.convert(null, "yes", String.class, String.class);
        assertThat(result).isEqualTo("yes");
    }

    @Test
    @DisplayName("convert should throw MappingException for unsupported types")
    void convertUnsupportedType() {
        assertThatThrownBy(() -> converter.convert(null, Integer.valueOf(1), Integer.class, Integer.class))
            .isInstanceOf(MappingException.class);
    }

}
