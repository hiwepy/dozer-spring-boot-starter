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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {{ @link EnableDozerMapper }}.
 *
 * <p>EnableDozerMapper is an annotation meta-annotated with {@code @Import(DozerAutoConfiguration.class)};
 * these tests verify the annotation contract rather than instantiating it.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("EnableDozerMapper Tests")
class EnableDozerMapperTest {

    @Test
    @DisplayName("EnableDozerMapper should target types")
    void shouldTargetTypes() {
        Target target = EnableDozerMapper.class.getAnnotation(Target.class);
        assertThat(target).isNotNull();
        assertThat(target.value()).contains(ElementType.TYPE);
    }

    @Test
    @DisplayName("EnableDozerMapper should be retained at runtime")
    void shouldBeRetainedAtRuntime() {
        Retention retention = EnableDozerMapper.class.getAnnotation(Retention.class);
        assertThat(retention).isNotNull();
        assertThat(retention.value()).isEqualTo(RetentionPolicy.RUNTIME);
    }

    @Test
    @DisplayName("EnableDozerMapper should be documented and inherited")
    void shouldBeDocumentedAndInherited() {
        assertThat(EnableDozerMapper.class.isAnnotationPresent(Documented.class)).isTrue();
        assertThat(EnableDozerMapper.class.isAnnotationPresent(Inherited.class)).isTrue();
    }

    @Test
    @DisplayName("Annotation applied to a class should be reflectively readable")
    void shouldBeReflectivelyReadable() {
        AnnotatedElement element = Sample.class;
        assertThat(element.isAnnotationPresent(EnableDozerMapper.class)).isTrue();
        EnableDozerMapper annotation = element.getAnnotation(EnableDozerMapper.class);
        assertThat(annotation).isNotNull();
    }

    @EnableDozerMapper
    static class Sample {
        // used only to verify annotation retention at runtime
    }

}
