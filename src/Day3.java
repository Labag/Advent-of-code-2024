import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    String fileContent = "";

    public Day3() {
        String filePath = "src/inputs/input-3.txt";
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            fileContent = builder.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int ResolveProblem1() {
        int sum = 0;
        List<String> muls = findMatches();
        for (String mul : muls) {
            List<String> numbers = extractNumbers(mul);
            sum += Integer.parseInt(numbers.getFirst()) * Integer.parseInt(numbers.get(1));
        }
        return sum;
    }

    private ArrayList<String> extractNumbers(String mul) {
        String regex = "([0-9]+),([0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mul);
        if (matcher.find()) {
            return new ArrayList<>(List.of(matcher.group(1), matcher.group(2)));
        }
        return new ArrayList<>();
    }

    private List<String> findMatches() {
        List<String> matches = new ArrayList<>();
        String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContent);

        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    public int ResolveProblem2() {
        int sum = 0;
        List<String> muls = findMatches2();
        for (String mul : muls) {
            List<String> numbers = extractNumbers(mul);
            sum += Integer.parseInt(numbers.getFirst()) * Integer.parseInt(numbers.get(1));
        }
        return sum;
    }

    private List<String> findMatches2() {
        List<String> matches = new ArrayList<>();
        String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContent);
        String lastInstruction = "do()";
        while (matcher.find()) {
            if (matcher.group().equals("do()") || matcher.group().equals("don't()")) {
                lastInstruction = matcher.group();
            } else {
                if (lastInstruction.equals("do()")) {
                    matches.add(matcher.group());
                }
            }
        }
        return matches;
    }
}
