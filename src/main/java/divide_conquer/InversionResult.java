package divide_conquer;

import java.util.List;

class InversionResult {
    public List<Integer> sortedList;
    public Long inversion;

    public InversionResult(final List<Integer> sortedList, final Long inversion) {
        this.sortedList = sortedList;
        this.inversion = inversion;
    }
}
