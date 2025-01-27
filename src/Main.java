import utils.RunningConfiguration;

public class Main {
    public static void main(String[] args) {
        RunningConfiguration day = RunningConfiguration.day11;

        switch (day) {
            case day1 -> runDay1();
            case day2 -> runDay2();
            case day3 -> runDay3();
            case day4 -> runDay4();
            case day5 -> runDay5();
            case day6 -> runDay6();
            case day7 -> runDay7();
            case day8 -> runDay8();
            case day9 -> runDay9();
            case day10 -> runDay10();
            case day11 -> runDay11();
        }
    }

    private static void runDay1() {
        //  day 1 problem 1
        Day1 day1 = new Day1();
        System.out.println("Day 1-1 :" + day1.ResolveProblem1());

        // day 1 pb 2
        System.out.println("Day 1-2 :" + day1.ResolveProblem2());
    }

    private static void runDay2() {
        // day 2 pb 1
        Day2 day2 = new Day2();
        System.out.println("Day 2-1 :" + day2.ResolveProblem1());

        // day 2 pb 2
        System.out.println("Day 2-2 :" + day2.ResolveProblem2());
    }

    private static void runDay3() {
        // day 3 problem 1
        Day3 day3 = new Day3();
        System.out.println("Day 3-1 :" + day3.ResolveProblem1());

        // day 3 problem 2
        System.out.println("Day 3-2 :" + day3.ResolveProblem2());
    }

    private static void runDay4() {
        // day 4 problem 1
        Day4 day4 = new Day4();
        System.out.println("Day 4-1 :" + day4.ResolveProblem1());

        // day 4 problem 2
        System.out.println("Day 4-2 :" + day4.ResolveProblem2());
    }

    private static void runDay5() {
        // day 5 problem 1
        Day5 day5 = new Day5();
        System.out.println("Day 5-1 :" + day5.ResolveProblem1());

        // day 5 problem 2
        System.out.println("Day 5-2 :" + day5.ResolveProblem2());
    }

    private static void runDay6() {
        // day 6 problem 1
        Day6 day6 = new Day6();
        System.out.println("Day 6-1 :" + day6.ResolveProblem1());

        // day 6 problem 2
        System.out.println("Day 6-2 :" + day6.ResolveProblem2());
    }

    private static void runDay7() {
        // day 7 problem 1
        Day7 day7 = new Day7();
        System.out.println("Day 7-1 :" + day7.ResolveProblem1());

        // day 7 problem 2
        System.out.println("Day 7-2 :" + day7.ResolveProblem2());
    }

    private static void runDay8() {
        // day 8 problem 1
        Day8 day8 = new Day8();
        System.out.println("Day 8-1 :" + day8.ResolveProblem1());

        // day 8 problem 2
        System.out.println("Day 8-2 :" + day8.ResolveProblem2());
    }

    private static void runDay9() {
        // day 9 problem 1
        Day9 day9 = new Day9();
        System.out.println("Day 9-1 :" + day9.ResolveProblem1());

        // day 9 problem 2
        System.out.println("Day 9-2 :" + day9.ResolveProblem2());
    }

    private static void runDay10() {
        // day 10 problem 1
        Day10 day10 = new Day10();
        System.out.println("Day 10-1 :" + day10.ResolveProblem1());

        // day 10 problem 2
        System.out.println("Day 10-2 :" + day10.ResolveProblem2());
    }

    private static void runDay11() {
        // day 11 problem 1
        Day11 day11 = new Day11();
        System.out.println("Day 11-1 :" + day11.ResolveProblem1());

        // day 11 problem 2
        //System.out.println("Day 11-2 :" + day11.ResolveProblem2());
        // problem with complexity
    }

}