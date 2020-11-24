package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Question2 {
    // Q3
    public static int countBandMembersInternal(List<Artist> artists) {
        return artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, Long::sum)
                .intValue();
    }

    /**
     * 使用 reduce实现 map
     * @param mapper
     * @param <R>
     * @param <T>
     * @return
     *//*
    public static <R, T> Stream<Stream<R>> flat(Stream<T> stream, Function<? super T, ? extends R> mapper) {
        stream.forEach(t -> {
            R apply = mapper.apply(t);
            Stream.of(apply);
        });

        stream.forEach(t -> Stream.of(mapper.apply(t)));
    }
    public static <R, T> Stream<R> map(Stream<R> stream, Function<? super T, ? extends R> mapper) {
        return stream.reduce();
    }*/
}
