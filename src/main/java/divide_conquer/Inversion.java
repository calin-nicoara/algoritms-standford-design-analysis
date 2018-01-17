package divide_conquer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Inversion {

    public static void main(String[] args) throws Exception {
            List<Integer> integers = Files.readAllLines(Paths.get("C:\\Users\\Calin\\Proiecte\\Algoritms\\stanford_design_algoritm\\IntegerArray.txt"))
                    .stream().map(Integer::valueOf).collect(Collectors.toList());
//        List<Integer> integers = Arrays.asList(7, 5, 8, 10, 3, 9, 2);

        InversionResult inversionResult = inversionOptimal(integers);
        System.out.println("Inversions: " + inversionResult.inversion);

//        Integer[] integersArray = new Integer[integers.size()];
//        integers.toArray(integersArray);
//        System.out.println("Brute force: " + inversionsBruteForce(integersArray));
    }

    private static InversionResult inversionOptimal(List<Integer> integers) {

        if (integers.size() == 1 || integers.size() == 0) {
            return new InversionResult(integers, 0L);
        }

        List<Integer> firstList = integers.subList(0, integers.size() / 2);
        List<Integer> secondList = integers.subList(integers.size() / 2, integers.size());

        InversionResult firstResult = inversionOptimal(firstList);
        InversionResult secondResult = inversionOptimal(secondList);

        int inversions = 0;

        List<Integer> orderedList = new ArrayList<>();
        for (int index1 = 0, index2 = 0, currentInversions = 0; index1 < firstResult.sortedList.size() || index2 < secondResult.sortedList.size(); ) {
            if (index1 == firstResult.sortedList.size() || (index2 < secondResult.sortedList.size() && firstResult.sortedList.get(index1) > secondResult.sortedList.get(index2))) {
                orderedList.add(secondResult.sortedList.get(index2));
                currentInversions++;
                ++index2;
            } else {
                orderedList.add(firstResult.sortedList.get(index1));
                ++index1;
                inversions += currentInversions;
            }
        }

        return new InversionResult(orderedList, inversions + firstResult.inversion + secondResult.inversion);
    }

    private static long inversionsBruteForce(final Integer[] ints) {
        AtomicLong count = new AtomicLong(0);

        int n = ints.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ints[i] > ints[j]) {
                    count.incrementAndGet();
                }
            }
        }

        return count.get();
    }
}
