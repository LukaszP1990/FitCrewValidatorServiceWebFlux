package com.fitcrew.validatorservice.core.util;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValueUtil {

  public static boolean validateStrings(List<? extends Comparable<? extends Comparable<?>>> listOfFields) {
    return Optional.ofNullable(listOfFields)
        .map(field -> isString(listOfFields))
        .orElse(false);
  }

  public static boolean validateObjects(List<Object> listOfFields) {
    return Optional.ofNullable(listOfFields)
            .map(field -> isObject(listOfFields))
            .orElse(false);
  }

  private static boolean isString(List<? extends Comparable<? extends Comparable<?>>> listOfFields) {
    return IntStream.rangeClosed(0, listOfFields.size() - 1)
        .allMatch(value -> Objects.nonNull(listOfFields.get(value)));
  }

  private static boolean isObject(List<Object> listOfFields) {
    return IntStream.rangeClosed(0, listOfFields.size() - 1)
            .allMatch(value -> Objects.nonNull(listOfFields.get(value)));
  }
}
