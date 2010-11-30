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
    
    private int realIndex(int index) {
        return index < 0 ? realIndex(size() + index) : index % size();
    }

    @Override
    public void add(int index, E element) {
        super.add(realIndex(index), element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        return super.addAll(realIndex(index), elements);
    }

    @Override
    public E get(int index) {
        return super.get(realIndex(index));
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return super.listIterator(realIndex(index));
    }

    @Override
    public E remove(int index) {
        return super.remove(realIndex(index));
    }

    @Override
    public E set(int index, E element) {
        return super.set(realIndex(index), element);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return super.subList(realIndex(fromIndex), realIndex(toIndex));
    }
    
}
