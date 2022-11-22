package year2021.code;

import helpers.Helper;

import java.io.IOException;
import java.util.ArrayList;

public class Question6 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2021, 6);
        String[] inp = input[0].split(",");
        ArrayList<Integer> inpNum = new ArrayList<>();
        for (String s : inp) {
            inpNum.add(Integer.parseInt(s));
        }
        System.out.println("Part 1:");
        part1((ArrayList<Integer>) inpNum.clone());
        System.out.println("Part 2:");
        part2((ArrayList<Integer>) inpNum.clone());
    }

    private static void part1(ArrayList<Integer> input) {
        for (int i = 0; i < 80; i++) {
            ArrayList<Integer> newFish = new ArrayList<>();
            for (int j = 0; j < input.size(); j++) {
                Integer curFish = input.get(j);
                curFish--;
                if (curFish < 0) {
                    newFish.add(8);
                    curFish = 6;
                }
                input.set(j, curFish);
            }
            input.addAll(newFish);
        }
        System.out.println(input.size());
    }

    private static void part2(ArrayList<Integer> input) {
        long[][] producesPerDay = new long[9][257];
        producesPerDay[0][1] = 2;
        producesPerDay[1][1] = 1;
        producesPerDay[2][1] = 1;
        producesPerDay[3][1] = 1;
        producesPerDay[4][1] = 1;
        producesPerDay[5][1] = 1;
        producesPerDay[6][1] = 1;
        producesPerDay[7][1] = 1;
        producesPerDay[8][1] = 1;
        for (int i = 2; i <= 256; i++) {
            producesPerDay[0][i] = producesPerDay[6][i-1] + producesPerDay[8][i-1];
            producesPerDay[1][i] = producesPerDay[0][i-1];
            producesPerDay[2][i] = producesPerDay[1][i-1];
            producesPerDay[3][i] = producesPerDay[2][i-1];
            producesPerDay[4][i] = producesPerDay[3][i-1];
            producesPerDay[5][i] = producesPerDay[4][i-1];
            producesPerDay[6][i] = producesPerDay[5][i-1];
            producesPerDay[7][i] = producesPerDay[6][i-1];
            producesPerDay[8][i] = producesPerDay[7][i-1];
        }
        int afterDays = 256;
        long totalFish = 0;
        for (int fish : input) {
            if (fish == 1) {
                totalFish += producesPerDay[1][afterDays];
            } else if (fish == 2) {
                totalFish += producesPerDay[2][afterDays];
            } else if (fish == 3) {
                totalFish += producesPerDay[3][afterDays];
            } else if (fish == 4) {
                totalFish += producesPerDay[4][afterDays];
            } else {
                totalFish += producesPerDay[5][afterDays];
            }
        }
        System.out.println(totalFish);
    }
}
