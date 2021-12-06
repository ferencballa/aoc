package code;

public class Helper {
    public static int[] StringArrayToInt(String[] input) {
        int[] values = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            values[i] = Integer.parseInt(input[i]);
        }
        return values;
    }
}
