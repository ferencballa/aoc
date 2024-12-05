package helpers;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class Helper {
    public static int[] StringArrayToIntArray(String[] input) {
        int[] values = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            values[i] = Integer.parseInt(input[i]);
        }
        return values;
    }

    public static String[] getInputForYearAndTask(int year, int day) throws IOException {
        String dayString = day < 10 ? "0" + day : "" + day;
        String[] lines = Files.readAllLines(Path.of("src/year" + year + "/input/Question" + dayString + ".txt")).toArray(new String[0]);
        if (lines.length == 0) {
            URL url = new URL("https://adventofcode.com/" + year + "/day/" + day + "/input");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String session_token = Files.readAllLines(Path.of("../aoc_session_token.txt")).toArray(new String[0])[0];
            con.setRequestProperty("Cookie", "session=" + session_token);
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            Reader streamReader;
            if (status == 200) {
                streamReader = new InputStreamReader(con.getInputStream());
                ArrayList<String> response = readStream(streamReader);
                FileWriter myWriter = new FileWriter("src/year" + year + "/input/Question" + dayString + ".txt");
                myWriter.write(StringUtils.join(response, "\r\n"));
                myWriter.close();
            } else {
                streamReader = new InputStreamReader(con.getErrorStream());
                ArrayList<String> response = readStream(streamReader); //Left here for connection debugging purposes
                System.out.println("Get didn't return 200");
                throw new RuntimeException();
            }
            lines = Files.readAllLines(Path.of("src/year" + year + "/input/Question" + dayString + ".txt")).toArray(new String[0]);
        }
        return lines;
    }

    private static ArrayList<String> readStream(Reader streamreader) throws IOException {
        BufferedReader in = new BufferedReader(streamreader);
        String inputLine;
        ArrayList<String> content = new ArrayList<>();
        while ((inputLine = in.readLine()) != null) {
            content.add(inputLine);
        }
        in.close();
        return content;
    }
}
