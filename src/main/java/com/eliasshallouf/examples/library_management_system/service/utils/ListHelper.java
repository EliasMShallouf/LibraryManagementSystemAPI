package com.eliasshallouf.examples.library_management_system.service.utils;

import java.util.List;
import java.util.stream.StreamSupport;

public class ListHelper {
    public static <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .toList();
    }
}
