package divide_conquer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuickSort {

    private static long numberOfComparisons;

    public static void main(String[] args) throws Exception {
        List<Integer> integers = Files.readAllLines(Paths.get("C:\\Users\\Calin\\Proiecte\\Algoritms\\algoritms-standford-design-analysis\\QuickSort.txt"))
                .stream().map(Integer::valueOf).collect(Collectors.toList());

//        List<Integer> integers = Arrays.asList(4, 9, 10, 6, 8, 16, 13, 3);

//        quickSortWithPivotFirstElement(integers, 0, integers.size());
        quickSort(integers, 0, integers.size());

        System.out.println(numberOfComparisons);
        System.out.println(integers);
    }

    private static void quickSort(List<Integer> numbers, int startIndex, int endIndex) {
        if(endIndex - startIndex < 1) {
            return;
        }

        int pivot = getPivotAsMedianElement(numbers, startIndex, endIndex);
        int middle = startIndex+1;

        for(int index = startIndex+1; index < endIndex; index++) {
            int current = numbers.get(index);

            numberOfComparisons++;
            if(current < pivot) {
                Collections.swap(numbers, index, middle);
                middle++;
            }
        }

        Collections.swap(numbers, startIndex, middle-1);

        quickSort(numbers, startIndex, middle-1);
        quickSort(numbers, middle, endIndex);
    }

    private static Integer getPivotAsFirstElement(final List<Integer> numbers,
                                                  final int startIndex, final int endIndex) {
        return numbers.get(startIndex);
    }

    private static Integer getPivotAsLastElement(final List<Integer> numbers, final int startIndex,
                                                 final int endIndex) {
        Collections.swap(numbers, startIndex, endIndex-1);
        return numbers.get(startIndex);
    }

    private static Integer getPivotAsMedianElement(final List<Integer> numbers, final int startIndex,
                                                 final int endIndex) {
        int firstElement = numbers.get(startIndex);
        int lastElement = numbers.get(endIndex-1);

        int numberOfElements = endIndex - startIndex;
        int medianIndex = startIndex + (numberOfElements / 2 + numberOfElements % 2 - 1);
        int median = numbers.get(medianIndex);

        Map<Integer, Integer> indexMappedByValue = new HashMap<>();
        indexMappedByValue.put(firstElement, startIndex);
        indexMappedByValue.put(lastElement, endIndex-1);
        indexMappedByValue.put(median, medianIndex);

        List<Integer> pivotCandidates = Arrays.asList(firstElement, median, lastElement);
        pivotCandidates.sort(Comparator.comparing(Function.identity()));

        int theOne = indexMappedByValue.get(pivotCandidates.get(1));

        Collections.swap(numbers, startIndex, theOne);
        return numbers.get(startIndex);
    }
}
