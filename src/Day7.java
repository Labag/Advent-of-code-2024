import utils.Operators;

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
            if (isValidEquation(equations.get(i), result, false)) {
                sum += result;
            }
        }
        return sum;
    }

    private boolean isValidEquation(List<Integer> numbers, Long result, boolean isProblem2) {
        List<List<Operators>> configurations = getConfigurations(numbers.size()-1);

        for (List<Operators> config : configurations) {
            long value = numbers.getFirst();
            for (int i = 1; i < numbers.size(); i++) {
                if (config.get(i-1) == Operators.mult) {
                    value *= numbers.get(i);
                } else if (config.get(i-1) == Operators.add){
                    value += numbers.get(i);
                } else if (isProblem2 && config.get(i-1) == Operators.concat) {
                        value = Long.parseLong(String.valueOf(value) + numbers.get(i));
                }
            }
            if (value == result) {
                return true;
            }
        }
        return false;
    }

    private List<List<Operators>> getConfigurations(int number) {
        // list with just mult
        List<List<Operators>> configs = new ArrayList<>();
        List<Operators> beginningByMult = new ArrayList<>();
        beginningByMult.add(Operators.mult);
        // list with just add
        List<Operators> beginningByAdd = new ArrayList<>();
        beginningByAdd.add(Operators.add);
        // list with just concat
        List<Operators> beginningByConcat = new ArrayList<>();
        beginningByConcat.add(Operators.concat);

        if (number == 1) {
            configs.add(beginningByMult);
            configs.add(beginningByAdd);
            configs.add(beginningByConcat);
            return configs;
        }

        // get the list of all possible configurations for a size of number-1
        List<List<Operators>> subConfigurations = getConfigurations(number-1);
        // adding all the sub configurations to both the lists beginning by any operator
        for (List<Operators> subConfiguration : subConfigurations) {
            List<Operators> concatenatedListMult = new ArrayList<>(beginningByMult);
            concatenatedListMult.addAll(subConfiguration);
            configs.add(concatenatedListMult);

            List<Operators> concatenatedListAdd = new ArrayList<>(beginningByAdd);
            concatenatedListAdd.addAll(subConfiguration);
            configs.add(concatenatedListAdd);

            List<Operators> concatenatedListConcat = new ArrayList<>(beginningByConcat);
            concatenatedListConcat.addAll(subConfiguration);
            configs.add(concatenatedListConcat);
        }
        return configs;
    }

    public long ResolveProblem2() {
        long sum = 0;
        for (int i = 0; i < equations.size(); i++) {
            Long result = testValues.get(i);
            if (isValidEquation(equations.get(i), result, true)) {
                sum += result;
            }
        }
        return sum;
    }
}
