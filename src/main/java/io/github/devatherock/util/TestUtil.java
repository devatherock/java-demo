package io.github.devatherock.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUtil {

    public static String sayHello() {
        return "Hello";
    }

    public static String reverseWordOrder(String input) {
        char[] stack = new char[input.length()];
        char[] reversed = new char[input.length()];
        char currentCharacter;
        int stackSize = 0;
        int maxIndex = input.length() - 1;

        for (int index = maxIndex; index >= 0; index--) {
            currentCharacter = input.charAt(index);
            stack[stackSize++] = currentCharacter;

            if (currentCharacter == ' ' || index == 0) {
                popAll(stack, reversed, maxIndex - index, stackSize);
                stackSize = 0;
            }
        }

        return new String(reversed);
    }

    private static void popAll(char[] stack, char[] output, int resultIndex, int stackSize) {
        int loopIndex = stackSize;
        if (stack[stackSize - 1] == ' ') {
            output[resultIndex--] = ' ';
            loopIndex = stackSize - 1;
        }

        for (int index = 0; index < loopIndex; index++) {
            output[resultIndex--] = stack[index];
        }
    }

}
