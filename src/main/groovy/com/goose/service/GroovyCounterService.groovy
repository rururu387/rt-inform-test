package com.goose.service

import jakarta.annotation.Nullable

class GroovyCounterService implements CounterService {
    @Override
    @Nullable
    Map<Integer, Integer> getAssociativeMap(@Nullable Collection<Integer> ints) {
        if (ints == null) {
            return null
        }

        return ints.countBy { it }
    }
}