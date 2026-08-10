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
package org.dozer.spring.boot.converters.number;

import java.math.BigDecimal;

import org.dozer.MappingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {{ @link BigDecimalStringConverter }}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("BigDecimalStringConverter Tests")
class BigDecimalStringConverterTest {

    private final BigDecimalStringConverter converter = new BigDecimalStringConverter();

    @Test
    @DisplayName("Instance can be created via constructor")
    void instance() {
        assertThat(new BigDecimalStringConverter()).isNotNull();
    }

    @Test
    @DisplayName("convert should return null when source value is null")
    void convertNullSource() {
        Object result = converter.convert(null, null, String.class, String.class);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("convert should parse a numeric string via the underlying BigDecimalConverter")
    void convertNumericString() {
        Object result = converter.convert(null, "123.456", String.class, String.class);
        assertThat(result).isEqualTo("123.456");
    }

    @Test
    @DisplayName("convert should convert a BigDecimal source to its plain string form")
    void convertBigDecimalSource() {
        Object result = converter.convert(null, new BigDecimal("999.0001"), BigDecimal.class, BigDecimal.class);
        assertThat(result).isEqualTo("999.0001");
    }

    @Test
    @DisplayName("convert should convert a BigDecimal with trailing zeros using toPlainString")
    void convertBigDecimalWithTrailingZeros() {
        Object result = converter.convert(null, new BigDecimal("1.2300"), BigDecimal.class, BigDecimal.class);
        assertThat(result).isEqualTo("1.2300");
    }

    @Test
    @DisplayName("convert should throw MappingException for unsupported types")
    void convertUnsupportedType() {
        assertThatThrownBy(() -> converter.convert(null, "x", Integer.class, Integer.class))
            .isInstanceOf(MappingException.class);
    }

}
