package util;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import type.Element;

/**
 * Static helper methods for validating model objects during construction
 * 
 * @apiNote In methods where an exception is thrown, IllegalStateException is chosen over IllegalArgumentException
 *   so that Builder.build() methods can throw the most appropriate exception without catching and wrapping
 */
public final class Validator {
    
    private static final int MIN_STRING_LENGTH = 1;
    private static final int MAX_STRING_LEGNTH = 1048576;  // 1024 * 1024 = 1MB
    private static final Set<Character> WHITESPACE = new HashSet<>(Arrays.asList(' ', '\t', '\r', '\n'));

    /**
     * A sequence of Unicode characters
     * <pre>
     * pattern: [\r\n\t\S]+
     * </pre>
     * 
     * @throws IllegalStateException if the passed String is not a valid String value
     */
    public static void checkString(String s) {
        if (s == null) {
            return;
        }
        if (s.length() > MAX_STRING_LEGNTH) {
            throw new IllegalStateException(String.format("String value length: %d is greater than maximum allowed length: %d", s.length(), MAX_STRING_LEGNTH));
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!Character.isWhitespace(ch)) {
                count++;
            } else if (!WHITESPACE.contains(ch)) {
                throw new IllegalStateException(String.format("String value: '%s' is not valid with respect to pattern: [ \\r\\n\\t\\S]+", s));
            }
        }
        if (count < MIN_STRING_LENGTH) {
            throw new IllegalStateException(String.format("Trimmed String value length: %d is less than minimum required length: %d", count, MIN_STRING_LENGTH));
        }
    }

    /**
     * @throws IllegalStateException if the type of the passed value is not one of the passed types
     */
    public static <T> T checkValueType(T value, Class<?>... types) {
        if (value != null) {
            List<Class<?>> typeList = Arrays.asList(types);
            Class<?> valueType = value.getClass();
            if (!typeList.contains(valueType)) {
                List<String> typeNameList = typeList.stream().map(Class::getSimpleName).collect(Collectors.toList());
                throw new IllegalStateException(String.format("Invalid value type: %s must be one of: %s", valueType.getSimpleName(), typeNameList.toString()));
            }
        }
        return value;
    }

    /**
     * @throws IllegalStateException if the passed element has no value and no children
     */
    public static void requireValueOrChildren(Element element) {
        if (!element.hasValue() && !element.hasChildren()) {
            throw new IllegalStateException("All elements must have a @value or children");
        }
    }
}
