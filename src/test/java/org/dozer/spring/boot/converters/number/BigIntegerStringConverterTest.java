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

import java.math.BigInteger;

import org.dozer.MappingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {{ @link BigIntegerStringConverter }}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("BigIntegerStringConverter Tests")
class BigIntegerStringConverterTest {

    private final BigIntegerStringConverter converter = new BigIntegerStringConverter();

    @Test
    @DisplayName("Instance can be created via constructor")
    void instance() {
        assertThat(new BigIntegerStringConverter()).isNotNull();
    }

    @Test
    @DisplayName("convert should return null when source value is null")
    void convertNullSource() {
        Object result = converter.convert(null, null, String.class, String.class);
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("convert should parse a numeric string via the underlying BigIntegerConverter")
    void convertNumericString() {
        Object result = converter.convert(null, "98765", String.class, String.class);
        assertThat(result).isEqualTo("98765");
    }

    @Test
    @DisplayName("convert should convert a BigInteger source to its string form")
    void convertBigIntegerSource() {
        Object result = converter.convert(null, new BigInteger("123456789"), BigInteger.class, BigInteger.class);
        assertThat(result).isEqualTo("123456789");
    }

    @Test
    @DisplayName("convert should convert a zero BigInteger to '0'")
    void convertZeroBigInteger() {
        Object result = converter.convert(null, BigInteger.ZERO, BigInteger.class, BigInteger.class);
        assertThat(result).isEqualTo("0");
    }

    @Test
    @DisplayName("convert should throw MappingException for unsupported types")
    void convertUnsupportedType() {
        assertThatThrownBy(() -> converter.convert(null, "x", Integer.class, Integer.class))
            .isInstanceOf(MappingException.class);
    }

}
