package de.cosmocode.collections;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingList;

/**
 * A {@link List} which supports out-of-range index access.
 * 
 * @since 
 * @author Willi Schoenborn
 *
 * @param <E> generic element type
 */
final class CyclingList<E> extends ForwardingList<E> {

    private final List<E> list;

    CyclingList(List<E> list) {
        this.list = Preconditions.checkNotNull(list, "List");
    }
    
    @Override
    protected List<E> delegate() {
        return list;
    }
    
    private int readIndex(int index) {
        final int size = size();
        if (size == 0) {
            throw new IndexOutOfBoundsException(String.format(
                "index (%s) must not be greater than size (%s)", index, size
            ));
        } else {
            return index < 0 ? readIndex(size + index) : index % size;
        }
    }
    
    private int writeIndex(int index) {
        return isEmpty() ? 0 : readIndex(index);
    }
    
    @Override
    public void add(int index, E element) {
        super.add(writeIndex(index), element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        return elements.isEmpty() ? false : super.addAll(writeIndex(index), elements);
    }

    @Override
    public E get(int index) {
        return super.get(readIndex(index));
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return index == 0 ? listIterator() : super.listIterator(readIndex(index));
    }

    @Override
    public E remove(int index) {
        return super.remove(readIndex(index));
    }

    @Override
    public E set(int index, E element) {
        return super.set(writeIndex(index), element);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        final int start = writeIndex(fromIndex);
        final int end = writeIndex(toIndex);
        if (start > end) {
            return super.subList(end, start);
        } else {
            return super.subList(start, end);
        }
    }
    
}
