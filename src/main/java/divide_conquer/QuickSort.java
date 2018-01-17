package divide_conquer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuickSort {

    private static long numberOfComparisons;

    public static void main(String[] args) throws Exception {
        List<Integer> integers = Files.readAllLines(Paths.get("C:\\Users\\Calin\\Proiecte\\Algoritms\\stanford_design_algoritm\\QuickSort.txt"))
                .stream().map(Integer::valueOf).collect(Collectors.toList());

//        quickSortWithPivotFirstElement(integers, 0, integers.size());
        quickSortWithPivotLastElement(integers, 0, integers.size());

        System.out.println(numberOfComparisons);
        System.out.println(integers);
    }

    private static void quickSortWithPivotFirstElement(List<Integer> numbers, int startIndex, int endIndex) {
        if(endIndex - startIndex < 2) {
            return;
        }

        int pivot = numbers.get(startIndex);
        int middle = startIndex;

        for(int index = startIndex+1; index < endIndex; index++) {
            int current = numbers.get(index);

            numberOfComparisons++;
            if(current < pivot) {
                middle++;
                Collections.swap(numbers, index, middle);
            }
        }

        Collections.swap(numbers, startIndex, middle);

        quickSortWithPivotFirstElement(numbers, startIndex, middle);
        quickSortWithPivotFirstElement(numbers, middle+1, endIndex);
    }

    private static void quickSortWithPivotLastElement(List<Integer> numbers, int startIndex, int endIndex) {
        if(endIndex - startIndex < 2) {
            return;
        }

        int pivot = numbers.get(endIndex-1);
        int middle = startIndex;

        for(int index = startIndex; index < endIndex-1; index++) {
            int current = numbers.get(index);

            numberOfComparisons++;
            if(current < pivot) {
                middle++;
                Collections.swap(numbers, index, middle);
            }
        }

        Collections.swap(numbers, endIndex-1, middle);

        quickSortWithPivotLastElement(numbers, startIndex, middle);
        quickSortWithPivotLastElement(numbers, middle+1, endIndex);
    }
}
