package com.davidcryer.trumpquotes.platformindependent.javahelpers;

import java.util.Random;

public final class CoinFlipper {
    private final Random random = new Random();
    public enum Result{HEADS, TAILS}

    public Result flip() {
        final int result = random.nextInt(2);
        if (result == 0) {
            return Result.HEADS;
        } else {
            return Result.TAILS;
        }
    }
}
