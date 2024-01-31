package org.example;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    private static final int COUNT_DOORS = 3;
    private static final int SUM_ALL_INDEXES = 3;
    private static final int COUNT_ITERATIONS = 1000;
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Map<Integer, Boolean> statistics = new HashMap<>();
        for (int i = 1; i <+ COUNT_ITERATIONS; i++) {
            int indexWinDoor = random.nextInt(COUNT_DOORS); // Дверь за которой приз
            int userChoice = random.nextInt(COUNT_DOORS); // Первоначальный выбор игрока
            int indexLoosingDoor; // Дверь, которая точно ведет к проигрышу

            // если победная дверь и выбранная игроком отличаются
            // тогда остается третья
            if (indexWinDoor != userChoice) {
                indexLoosingDoor = SUM_ALL_INDEXES - indexWinDoor - userChoice;
            } else {
                // Если игрок сразу угадал победную дверь и она не центральная
                // тогда выбираем противпололожную
                if (indexWinDoor != 1) {
                    indexLoosingDoor = SUM_ALL_INDEXES - indexWinDoor - 1;
                } else {
                    // Если правильная дверь и выбранная игроком центральная
                    // то просто имитируем выбор одной из двух по краям
                    indexLoosingDoor = (i % 2 == 0) ? 0 : 2;
                }
            }

            int finalUserChoice = SUM_ALL_INDEXES - userChoice - indexLoosingDoor; // Игрок меняет выбор двери

            boolean gameResult = indexWinDoor == finalUserChoice;
            statistics.put(i, gameResult);
        }
        double countWinGame = statistics.values().stream().filter(Boolean::booleanValue).count();
        System.out.println(countWinGame / COUNT_ITERATIONS);
    }
}