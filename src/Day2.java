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

    // pb 2
    public int ResolveProblem2() {
        int sum = 0;
        // nouvelle liste d'éléments contenant une liste de rapports listes de levels
        List<List<List<Integer>>> newReportsList = new ArrayList<>();
        for (List<Integer> report : reports) {
            if (IsValid(report)) {
                sum++;
                continue;
            }
            List<List<Integer>> derivatedReports = GenerateReports(report);
            newReportsList.add(derivatedReports);
        }

        // pour chaque list de reports, on regarde si le premier est valide, si oui on ajt sinon deuxième etc...
        for (List<List<Integer>> generatedReportsList : newReportsList) {
            for (List<Integer> report : generatedReportsList) {
                if (IsValid(report)) {
                    sum++;
                    break;
                }
            }
        }
        return sum;
    }

    private List<List<Integer>> GenerateReports(List<Integer> initialList) {
        List<List<Integer>> generatedReports = new ArrayList<>();

        for (int i = 0; i < initialList.size(); i++) {
            List<Integer> modifiedList = new ArrayList<>(initialList);
            modifiedList.remove((int) i);
            generatedReports.add(modifiedList);
        }
        return generatedReports;
    }
}
