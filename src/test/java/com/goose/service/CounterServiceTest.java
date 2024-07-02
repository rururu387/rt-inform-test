package com.goose.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CounterServiceTest {
    // Функция нужна потому, что Groovy.countBy возвращает LinkedHashMap
    private void assertMapsAreEqual(Map<Integer, Integer> expected, Map<Integer, Integer> actual) {
        if (expected == null) {
            assertNull(actual);
            return;
        }
        assertEquals(expected.size(), actual.size());
        for (var expectedEntry : expected.entrySet()) {
            assertEquals(expectedEntry.getValue(), actual.get(expectedEntry.getKey()));
        }
    }

    private static Stream<Arguments> provideCounterServiceImpls() {
        return Stream.of(
                Arguments.of(new GroovyCounterService()),
                Arguments.of(new JavaCounterService())
        );
    }

    @ParameterizedTest
    @MethodSource("provideCounterServiceImpls")
    // Given ... When ... Then
    public void nullArgument_getAssociativeMapCalled_returnsNull(CounterService service) {
        List<Integer> nullList = null;

        var actualMap = service.getAssociativeMap(nullList);

        assertNull(actualMap);
    }

    @ParameterizedTest
    @MethodSource("provideCounterServiceImpls")
    public void emptyArgument_getAssociativeMapCalled_returnsEmpty(CounterService service) {
        List<Integer> emptyList = List.of();

        var actualMap = service.getAssociativeMap(emptyList);

        assertNotNull(actualMap);
        assertTrue(actualMap.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideCounterServiceImpls")
    public void argListContainsDuplicates_getAssociativeMapCalled_returnsCorrect(CounterService service) {
        List<Integer> sourceList = List.of(1, 3, 4, 5, 1, 5, 4);

        var actualMap = service.getAssociativeMap(sourceList);

        var expectedMap = Map.of(1, 2, 3, 1, 4, 2, 5, 2);
        assertMapsAreEqual(expectedMap, actualMap);
    }

    @ParameterizedTest
    @MethodSource("provideCounterServiceImpls")
    public void argListHasNoDuplicates_getAssociativeMapCalled_returnsCorrect(CounterService service) {
        List<Integer> sourceList = List.of(1, 2, 6, 10, 4, 8, 7);

        var actualMap = service.getAssociativeMap(sourceList);

        var expectedMap = Map.of(1, 1, 2, 1, 4, 1, 6, 1, 7, 1, 8, 1, 10, 1);
        assertMapsAreEqual(expectedMap, actualMap);
    }

    @ParameterizedTest
    @MethodSource("provideCounterServiceImpls")
    public void argListAllDuplicates_getAssociativeMapCalled_returnsCounted(CounterService service) {
        List<Integer> sourceList = List.of(-1, 2, 0, 10, 4, Integer.MAX_VALUE, 7, -1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE, 10, 4, 7, 0);

        var actualMap = service.getAssociativeMap(sourceList);

        var expectedMap = Map.of(-1, 2, 0, 2, 2, 2, 4, 2, 7, 2, 10, 2, Integer.MAX_VALUE, 3);
        assertMapsAreEqual(expectedMap, actualMap);
    }

    @ParameterizedTest
    @MethodSource("provideCounterServiceImpls")
    public void argListContainsSingleDuplicatedValue_getAssociativeMapCalled_returnsSingleValue(CounterService service) {
        List<Integer> sourceList = List.of(42, 42, 42, 42, 42, 42, 42, 42);

        var actualMap = service.getAssociativeMap(sourceList);

        var expectedMap = Map.of(42, 8);
        assertMapsAreEqual(expectedMap, actualMap);
    }
}