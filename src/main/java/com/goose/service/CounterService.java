package com.goose.service;

import jakarta.annotation.Nullable;

import java.util.Collection;
import java.util.Map;

public interface CounterService {
    @Nullable
    Map<Integer, Integer> getAssociativeMap(@Nullable Collection<Integer> ints);
}
