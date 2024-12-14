import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day4Part2 {

    private static int wordCount, m, n;

    public static void main(String[] args) {
        try{
            wordCount = 0;
            m = n = 140;
            char[][] grid = new char[m][n];
            File file = new File("/Users/parulgarg/Projects/AdventOfCode2024/PuzzleInputs/Day4.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            int t = 0;
            while ((read = br.readLine()) != null) {
                grid[t++] = read.toCharArray();
            }
            br.close();
            countCrossMAS(grid);
            System.out.println(wordCount);
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }

    private static void countCrossMAS(char[][] grid) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'A') {
                    if (isTopToBottomDiagonalValid(grid, i,j) && isBottomToTopDiagonalValid(grid, i, j)) 
                    wordCount++;
                }
            }
        }
    }

    private static boolean isTopToBottomDiagonalValid(char[][] grid, int i, int j) {
        if (i - 1 >= 0 && j - 1 >=0 && i + 1 < m && j + 1 < m) {
            if ((grid[i-1][j-1] == 'M' && grid[i + 1][j+1] == 'S') || 
            (grid[i-1][j-1] == 'S' && grid[i + 1][j+1] == 'M')) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBottomToTopDiagonalValid(char[][] grid, int i, int j) {
        if (i - 1 >= 0 && j - 1 >=0 && i + 1 < m && j + 1 < m) {
            if ((grid[i-1][j+1] == 'M' && grid[i + 1][j-1] == 'S') || 
            (grid[i-1][j+1] == 'S' && grid[i + 1][j-1] == 'M')) {
                return true;
            }
        }
        return false;
    }
}
