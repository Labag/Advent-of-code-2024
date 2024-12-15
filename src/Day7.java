import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    private final List<Long> testValues;
    private final List<List<Integer>> equations;

    public Day7() {
        String filePath = "src/inputs/input-7.txt";
        testValues = new ArrayList<>();
        equations = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(":", 2); // Sépare les chiffres par espace
                testValues.add(Long.valueOf(parts[0].trim()));

                List<Integer> equation = new ArrayList<>();
                String[] numbers = parts[1].trim().split("\\s+");
                for (String number : numbers) {
                    equation.add(Integer.parseInt(number));
                }
                equations.add(equation);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public long ResolveProblem1() {
        long sum = 0;
        for (int i = 0; i < equations.size(); i++) {
            Long result = testValues.get(i);
            if (isValidEquation(equations.get(i), result)) {
                sum += result;
            }
        }
        return sum;
    }

    private boolean isValidEquation(List<Integer> numbers, Long result) {
        // false pour + et true pour *
        List<List<Boolean>> configurations = getConfigurations(numbers.size()-1);

        for (List<Boolean> config : configurations) {
            long value = numbers.getFirst();
            for (int i = 1; i < numbers.size(); i++) {
                if (config.get(i-1)) {
                    value *= numbers.get(i);
                } else {
                    value += numbers.get(i);
                }
            }
            if (value == result) {
                return true;
            }
        }
        return false;
    }

    private List<List<Boolean>> getConfigurations(int number) {
        // list with just false
        List<List<Boolean>> configs = new ArrayList<>();
        List<Boolean> beginningByFalse = new ArrayList<>();
        beginningByFalse.add(false);
        // list with just true
        List<Boolean> beginningByTrue = new ArrayList<>();
        beginningByTrue.add(true);

        if (number == 1) {
            configs.add(beginningByFalse);
            configs.add(beginningByTrue);
            return configs;
        }

        // get the list of all possible configurations for a size of number-1
        List<List<Boolean>> subConfigurations = getConfigurations(number-1);
        // adding all the sub configurations to both the lists beginning by true and false
        for (List<Boolean> subConfiguration : subConfigurations) {
            List<Boolean> concatenatedListFalse = new ArrayList<>(beginningByFalse);
            concatenatedListFalse.addAll(subConfiguration);
            configs.add(concatenatedListFalse);

            List<Boolean> concatenatedListTrue = new ArrayList<>(beginningByTrue);
            concatenatedListTrue.addAll(subConfiguration);
            configs.add(concatenatedListTrue);
        }
        return configs;
    }
}
