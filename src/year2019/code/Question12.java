package year2019.code;

import helpers.Helper;

import java.io.IOException;

public class Question12 {
    public static void main(String[] args) throws IOException {
        String[] input = Helper.getInputForYearAndTask(2019, 12);
        System.out.println("Part 1:");
        part1(input);
        System.out.println("Part 2:");
        part2(input);
    }

    private static void part1(String[] input) {
        int[][] positions = new int[4][3];
        for (int p = 0; p < 4; p++) {
            String[] coors = input[p].split(", ");
            positions[p][0] = Integer.parseInt(coors[0].split("=")[1]);
            positions[p][1] = Integer.parseInt(coors[1].split("=")[1]);
            String zWithEnd = coors[2].split("=")[1];
            positions[p][2] = Integer.parseInt(zWithEnd.substring(0, zWithEnd.length()-1));
        }
        int[][] velocities = new int[4][3];
        for (int step = 0; step < 1000; step++) {
            for (int objectMoon = 0; objectMoon < 4; objectMoon++) {
                for (int otherMoon = 0; otherMoon < 4; otherMoon++) {
                    if (objectMoon != otherMoon) {
                        for (int axis = 0; axis < 3; axis++) {
                            if (positions[objectMoon][axis] > positions[otherMoon][axis]) {
                                velocities[objectMoon][axis]--;
                            } else if (positions[objectMoon][axis] < positions[otherMoon][axis]){
                                velocities[objectMoon][axis]++;
                            }
                        }
                    }
                }
            }
            for (int moon = 0; moon < 4; moon++) {
                for (int axis = 0; axis < 3; axis++) {
                    positions[moon][axis] += velocities[moon][axis];
                }
            }
        }
        int[][] energies = new int[4][2];
        for (int moon = 0; moon < 4; moon++) {
            for (int axis = 0; axis < 3; axis++) {
                energies[moon][0] += Math.abs(positions[moon][axis]);
                energies[moon][1] += Math.abs(velocities[moon][axis]);
            }
        }
        System.out.println(energies[0][0]*energies[0][1] + energies[1][0]*energies[1][1] + energies[2][0]*energies[2][1] + energies[3][0]*energies[3][1]);
    }

    private static void part2(String[] input) {

    }
}
