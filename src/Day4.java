import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public String filePath;
    public List<List<String>> charsMatrix;

    public Day4() {
        filePath = "src/inputs/input-4.txt";
        charsMatrix = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> letters = List.of(line.split(""));
                charsMatrix.add(letters);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int ResolveProblem1() {
        int sum = 0;
        for (int i = 0; i < charsMatrix.size(); i++) {
            for (int j = 0; j < charsMatrix.get(i).size(); j++) {
                if (charsMatrix.get(i).get(j).equals("X")) {
                    sum+= findOccurences(i, j);
                }
            }
        }
        return sum;
    }

    // autre solution : je cherche un M autour du X
    // même fonction, un A autour du M
    // récursif un S autour du A
    // direction :
    // 1 2 3
    // 4 / 5
    // 6 7 8

    private int findOccurences(int i, int j) {
        int res = 0;
        boolean right = j+3 < charsMatrix.get(i).size();
        boolean left = j-3 >= 0;
        boolean up = i-3 >= 0;
        boolean down = i+3 < charsMatrix.size();

        if (up) {
            if (charsMatrix.get(i-1).get(j).equals("M")
                    && charsMatrix.get(i-2).get(j).equals("A")
                    && charsMatrix.get(i-3).get(j).equals("S"))
                res++;
        }
        if (down) {
            if (charsMatrix.get(i+1).get(j).equals("M")
                    && charsMatrix.get(i+2).get(j).equals("A")
                    && charsMatrix.get(i+3).get(j).equals("S"))
                res++;
        }
        if (right) {
            if (charsMatrix.get(i).get(j+1).equals("M")
                    && charsMatrix.get(i).get(j+2).equals("A")
                    && charsMatrix.get(i).get(j+3).equals("S"))
                res++;
        }
        if (left) {
            if (charsMatrix.get(i).get(j-1).equals("M")
                    && charsMatrix.get(i).get(j-2).equals("A")
                    && charsMatrix.get(i).get(j-3).equals("S"))
                res++;
        }

        if (left && up) {
            if (charsMatrix.get(i-1).get(j-1).equals("M")
                && charsMatrix.get(i-2).get(j-2).equals("A")
                && charsMatrix.get(i-3).get(j-3).equals("S"))
                res++;
        }

        if (up && right) {
            if (charsMatrix.get(i-1).get(j+1).equals("M")
                && charsMatrix.get(i-2).get(j+2).equals("A")
                && charsMatrix.get(i-3).get(j+3).equals("S"))
                res++;
        }

        if (right && down) {
            if (charsMatrix.get(i+1).get(j+1).equals("M")
                && charsMatrix.get(i+2).get(j+2).equals("A")
                && charsMatrix.get(i+3).get(j+3).equals("S"))
                res++;
        }

        if (down && left) {
            if (charsMatrix.get(i+1).get(j-1).equals("M")
                && charsMatrix.get(i+2).get(j-2).equals("A")
                && charsMatrix.get(i+3).get(j-3).equals("S"))
                res++;
        }
        return res;
    }
}
