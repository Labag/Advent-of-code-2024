import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Day2 {
    public static String filePath;

    public List<List<Integer>> reports;
    public Day2() {
        filePath = "src/inputs/input-2.txt";
        reports = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Sépare les chiffres par espace
                List<Integer> newReport = new ArrayList<>();
                for (String part : parts) {
                    newReport.add(Integer.parseInt(part));
                }
                reports.add(newReport);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int ResolveProblem1() {
        int sum = 0;
        for (List<Integer> report : reports) {
            if (IsValid(report)) {
                sum ++;
            }
        }
        return sum;
    }

    private boolean IsValid(List<Integer> report) {
        boolean isIncreasing;
        if (report.get(0) < report.get(1)) {
            isIncreasing = true;
        } else if (report.get(0) > report.get(1)) {
            isIncreasing = false;
        } else {
            return false; // because two identical values
        }

        int currentLevel = report.get(0);

        for (int i = 1; i < report.size(); i++) {
            // si diff n'appartient pas à [1;3]
            if (abs(currentLevel - report.get(i)) < 1 || abs(currentLevel - report.get(i)) > 3)
                return false;

            // si ça monte ou si ça descend on enchaine
            if (isIncreasing && currentLevel < report.get(i) || !isIncreasing && currentLevel > report.get(i)) {
                currentLevel = report.get(i);
            } else {
                return false;
            }
        }
        return true;
    }
}
