package de.cosmocode.collections;

import java.util.List;

import junit.framework.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;

import de.cosmocode.collections.utility.list.ListTest;

/**
 * Tests {@link List} compatability of {@link CyclingList}.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
public final class CyclingListTest implements TestListGenerator<String> {
    
    private CyclingListTest() {
        
    }

    public static Test suite() {
        return ListTestSuiteBuilder.using(new CyclingListTest()).
            named(ListTest.class.getSimpleName()).
            withFeatures(
                CollectionSize.ANY,
                CollectionFeature.ALLOWS_NULL_VALUES,
                CollectionFeature.KNOWN_ORDER,
                ListFeature.GENERAL_PURPOSE,
                ListFeature.REMOVE_OPERATIONS
            ).createTestSuite();
    }
    
    @Override
    public List<String> create(Object... elements) {
        final List<String> list = Lists.newArrayList();
        for (Object element : elements) {
            final String string = String.class.cast(element);
            list.add(string);
        }
        return MoreLists.cycle(list);
    }
    
    @Override
    public String[] createArray(int length) {
        return new String[length];
    }
    
    @Override
    public Iterable<String> order(List<String> insertionOrder) {
        return insertionOrder;
    }
    
    @Override
    public SampleElements<String> samples() {
        return new SampleElements<String>("a", "b", "c", "d", "e");
    }
    
}
