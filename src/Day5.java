import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    public final String filePath;
    private final List<List<Integer>> updates;
    private final List<String> rules;

    public Day5() {
        filePath = "src/inputs/input-5.txt";
        updates = new ArrayList<>();
        rules = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isUpdate = false;
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) {
                    isUpdate = true;
                    continue;
                }

                if (isUpdate) {
                    List<Integer> numbers = Arrays.stream(line.split(","))
                            .map(Integer::parseInt)
                            .toList();
                    updates.add(numbers);
                } else {
                    rules.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int ResolveProblem1() {
        int sum = 0;
        for (List<Integer> update : updates) {
            if (checkUpdate(update)) {
                int test1 = (int) Math.ceil((double) update.size() /2 - 1);
                int test = update.get(test1);
                sum += test;
            }

        }
        return sum;
    }

    private boolean checkUpdate(List<Integer> update) {
        for (int i = 0; i < update.size()-1; i++) {
            for (int j = i+1; j < update.size(); j++) {
                if (existsForbidingRule(update.get(i), update.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean existsForbidingRule(Integer i, Integer j) {
        // we look for the contrary rule
        String lookingFor = j + "|" + i;
        return rules.contains(lookingFor);
    }
}
