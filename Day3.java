import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day3 {
    public static void main(String[] args) {
        try{
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day3.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            while ((read = br.readLine()) != null) {
                String[] input = read.split("mul\\([0-9][0-9][0-9],[0-9][0-9][0-9]\\)");
                for (String i : input) System.out.println(i);
            }
            br.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    private static string[] getParsedArray(String str, String pattern) {
        string[] ans = new string[];
        int k = 0;
        for (int i = 0; i <= str.length() - 8; i++) {
            if (str.substring(i, i + ))
        }
    }
}
