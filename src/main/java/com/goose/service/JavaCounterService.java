package com.goose.service;

import jakarta.annotation.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaCounterService implements CounterService {
    @Override
    @Nullable
    public Map<Integer, Integer> getAssociativeMap(@Nullable Collection<Integer> ints) {
        if (ints == null) {
            return null;
        }

        return ints.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }
}
