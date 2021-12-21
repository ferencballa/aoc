package year2021.code;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Question21 {
    private static HashMap<String, Wins> winsPerSituation;
    private static long totalChecks = 444356092776315L + 341960390180808L;

    public static void main(String[] args) throws IOException {
        String[] input = Files.readAllLines(Path.of("src/year2021/input/Question21.txt")).toArray(new String[0]);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int pos1 = Integer.parseInt(input[0].split(" ")[4]);
        int pos2 = Integer.parseInt(input[1].split(" ")[4]);
        int dieroll = 0;
        boolean player1turn = true;
        int die = 1;
        int score1 = 0;
        int score2 = 0;
        while (score1 < 1000 && score2 < 1000) {
            if (player1turn) {
                for (int i = 0; i < 3; i++) {
                    pos1 += die;
                    die++;
                    while (die > 100) {
                        die -= 100;
                    }
                    dieroll++;
                }
                while (pos1 > 10) {
                    pos1 -= 10;
                }
                score1 += pos1;
                player1turn = false;
            } else {
                for (int i = 0; i < 3; i++) {
                    pos2 += die;
                    die++;
                    while (die > 100) {
                        die -= 100;
                    }
                    dieroll++;
                }
                while (pos2 > 10) {
                    pos2 -= 10;
                }
                score2 += pos2;
                player1turn = true;
            }
        }
        if (score1 >= 1000) {
            System.out.println(dieroll * score2);
        } else {
            System.out.println(dieroll * score1);
        }
    }

    private static void part2(String[] input) {
        winsPerSituation = new HashMap<>();
        int pos1 = Integer.parseInt(input[0].split(" ")[4]);
        int pos2 = Integer.parseInt(input[1].split(" ")[4]);
        Wins wins1 = getWins(true, 1, pos1, pos2, 1, 0, 0);
        Wins wins2 = getWins(true, 1, pos1, pos2, 2, 0, 0);
        Wins wins3 = getWins(true, 1, pos1, pos2, 3, 0, 0);
        System.out.println(Long.max(wins1.wins1 + wins2.wins1 + wins3.wins1, wins1.wins2 + wins2.wins2 + wins3.wins2));
    }

    private static Wins getWins (boolean player1turn, int playerTurn, int pos1, int pos2, int die, int score1, int score2) {
        String situationString = (player1turn ? "1" : "0") + "," + playerTurn + "," + pos1 + "," + pos2 + "," + die + "," + score1 + "," + score2;
        if (winsPerSituation.containsKey(situationString)) {
            Wins w = winsPerSituation.get(situationString);
            totalChecks -= (w.wins1 + w.wins2);
            //System.out.println(totalChecks);
            return w;
        } else {
            Wins wins = new Wins(0, 0);
            if (player1turn) {
                pos1 += die;
                if (playerTurn == 3) {
                    while (pos1 > 10) {
                        pos1 -= 10;
                    }
                    score1 += pos1;
                    player1turn = false;
                    playerTurn = 1;
                } else {
                    playerTurn++;
                }
            } else {
                pos2 += die;
                if (playerTurn == 3) {
                    while (pos2 > 10) {
                        pos2 -= 10;
                    }
                    score2 += pos2;
                    player1turn = true;
                    playerTurn = 1;
                } else {
                    playerTurn++;
                }
            }
            if (playerTurn != 1 || (score1 < 21 && score2 < 21)) {
                Wins wins1 = getWins(player1turn, playerTurn, pos1, pos2, 1, score1, score2);
                Wins wins2 = getWins(player1turn, playerTurn, pos1, pos2, 2, score1, score2);
                Wins wins3 = getWins(player1turn, playerTurn, pos1, pos2, 3, score1, score2);
                wins.wins1 = wins1.wins1 + wins2.wins1 + wins3.wins1;
                wins.wins2 = wins1.wins2 + wins2.wins2 + wins3.wins2;
            } else {
                if (score1 >= 21) {
                    wins.wins1 = 1;
                } else {
                    wins.wins2 = 1;
                }
            }
            winsPerSituation.put(situationString, wins);
            return wins;
        }
    }
}

class Wins {
    long wins1;
    long wins2;

    public Wins(long w1, long w2) {
        wins1 = w1;
        wins2 = w2;
    }
}
