package com.chamil.bugtrackerapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class TestUtil {
    private TestUtil() {}

    public static String loadResourceAsString(String file, Class<?> className) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(className.getResourceAsStream(file)));
        return bufferedReader.lines().collect(Collectors.joining());
    }
}
