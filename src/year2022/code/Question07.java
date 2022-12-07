package year2022.code;

import helpers.Helper;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Question07 {
    public static void main(String[] args) throws IOException {
        Q07Part1.run();
        Q07Part2.run();
    }

    static String[] getInput() throws IOException {
        return Helper.getInputForYearAndTask(2022, 7);
    }

    static Point findSize(DirOrFile dirOrFile) {
        int size = 0;
        int totalOfSmallDirs = 0;
        if (!dirOrFile.isDir) {
            return new Point(dirOrFile.size, 0);
        } else {
            for (String key : dirOrFile.children.keySet()) {
                Point rets = findSize(dirOrFile.children.get(key));
                size += rets.x;
                totalOfSmallDirs += rets.y;
            }
            if (size <= 100000) {
                totalOfSmallDirs += size;
            }
        }
        return new Point(size, totalOfSmallDirs);
    }
}

class Q07Part1 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 1:");
        String[] input = Question07.getInput();
        DirOrFile root = new DirOrFile(true, null);
        root.children = new HashMap<>();
        DirOrFile current = root;
        for(int i = 0; i < input.length; i++) {
            String command = input[i];
            if (command.startsWith("$ cd")) {
                if (command.charAt(5) == '/') {
                    current = root;
                } else if (command.length() == 7 && command.startsWith("$ cd ..")) {
                    current = current.parent;
                } else {
                    current = current.children.get(command.substring(5));
                }
            } else if (command.startsWith("$ ls")) {
                //do nothing
            } else if (command.startsWith("dir")) {
                DirOrFile dir = new DirOrFile(true, current);
                dir.children = new HashMap<>();
                current.children.put(command.substring(4), dir);
            } else {
                String[] commandParts = command.split(" ");
                DirOrFile file = new DirOrFile(false, current);
                file.size = Integer.parseInt(commandParts[0]);
                current.children.put(commandParts[1], file);
            }
        }
        Point rets = Question07.findSize(root);
        System.out.println(rets.y);
    }
}

class DirOrFile {
    boolean isDir;
    DirOrFile parent;
    HashMap<String, DirOrFile> children;
    int size;

    public DirOrFile(boolean isDir, DirOrFile parent) {
        this.isDir = isDir;
        this.parent = parent;
    }
}

class Q07Part2 {
    public static void main(String[] args) throws IOException {
        run();
    }

    static void run() throws IOException {
        System.out.println("Part 2:");
        String[] input = Question07.getInput();
        DirOrFile root = new DirOrFile(true, null);
        root.children = new HashMap<>();
        DirOrFile current = root;
        for(int i = 0; i < input.length; i++) {
            String command = input[i];
            if (command.startsWith("$ cd")) {
                if (command.charAt(5) == '/') {
                    current = root;
                } else if (command.length() == 7 && command.startsWith("$ cd ..")) {
                    current = current.parent;
                } else {
                    current = current.children.get(command.substring(5));
                }
            } else if (command.startsWith("$ ls")) {
                //do nothing
            } else if (command.startsWith("dir")) {
                DirOrFile dir = new DirOrFile(true, current);
                dir.children = new HashMap<>();
                current.children.put(command.substring(4), dir);
            } else {
                String[] commandParts = command.split(" ");
                DirOrFile file = new DirOrFile(false, current);
                file.size = Integer.parseInt(commandParts[0]);
                current.children.put(commandParts[1], file);
            }
        }
        Point rets = Question07.findSize(root);
        int available = 70000000 - rets.x;
        int needed = 30000000;
        Point rets2 = findSmallestDirectoryLargerThan(root, needed - available);
        System.out.println(rets2.y);
    }

    private static Point findSmallestDirectoryLargerThan(DirOrFile dirOrFile, int needed) {
        int size = 0;
        int smallestDirLargerThan = Integer.MAX_VALUE;
        if (!dirOrFile.isDir) {
            return new Point(dirOrFile.size, smallestDirLargerThan);
        } else {
            for (String key : dirOrFile.children.keySet()) {
                Point rets = findSmallestDirectoryLargerThan(dirOrFile.children.get(key), needed);
                size += rets.x;
                if (rets.y >= needed) {
                    smallestDirLargerThan = Math.min(smallestDirLargerThan, rets.y);
                }
            }
        }
        if (size >= needed) {
            smallestDirLargerThan = Math.min(smallestDirLargerThan, size);
        }
        return new Point(size, smallestDirLargerThan);
    }
}
