import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/*
 * NdArray
 */
public class NdArray<E> extends AbstractCollection<E> {

    private Object[] ndArray;
    private ArrayList<Integer> dimensions;

    NdArray(Integer firstDimLen, Integer... furtherDimLens) throws NegativeArraySizeException {
        // "Illegal dimension size dimLen."
        checkDimenLen(firstDimLen, furtherDimLens);

        dimensions = new ArrayList<Integer>();
        int arraysize = firstDimLen;
        dimensions.add(firstDimLen);

        for (int len : furtherDimLens) {
            if (len == 0) {
                break;
            }
            arraysize *= len;
            dimensions.add(len);
        }

        ndArray = new Object[arraysize];
    }

    private void checkDimenLen(Integer firstDimLen, Integer... furtherDimLens) throws NegativeArraySizeException {
        if (firstDimLen < 0) {
            throw new NegativeArraySizeException(String.format("Illegal dimension size %d.", firstDimLen));
        }
        for (int len : furtherDimLens) {
            if (len < 0) {
                throw new NegativeArraySizeException(String.format("Illegal dimension size %d.", len));
            }
        }
    }

    public E get(int... indices) {
        isDimensionsValid(indices);
        isIndicesValid(indices);

        int index = findIndex(indices);

        return (E) this.ndArray[index];
    }

    public void set(E item, int... indices) {
        isDimensionsValid(indices);
        isIndicesValid(indices);

        int index = findIndex(indices);

        this.ndArray[index] = item;
    }

    private int findIndex(int... indices) {
        int itemIndex = indices[1] + dimensions.get(1) * indices[0];
        for (int i = 2; i < dimensions.size(); ++i) {
            itemIndex = indices[i] + dimensions.get(i) * itemIndex;
        }
        return itemIndex;
    }

    private void isDimensionsValid(int... indices) {
        if (indices.length != dimensions.size()) {
            throw new IllegalArgumentException(String.format("The array has %d dimensions but %d indices were given.",
                    dimensions.size(), indices.length));
        }
    }

    private void isIndicesValid(int... indices) {
        for (int i = 0; i < indices.length; ++i) {
            if (indices[i] >= dimensions.get(i)) {
                throw new IndexOutOfBoundsException(String.format("Illegal index %d for dimension %d of length %d.",
                        indices[i], i + 1, dimensions.get(i)));
            }
        }
    }

    public int[] getDimensions() {
        return dimensions.stream().mapToInt(x -> x).toArray();
    }

    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) Arrays.asList(ndArray).iterator();
    }

    @Override
    public int size() {
        return ndArray.length;
    }

}