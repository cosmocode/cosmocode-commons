package de.cosmocode.commons;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class EnumUtility {

    public static <E extends Enum<E>> long asBit(E e) {
        return 1l << e.ordinal();
    }
    
    public static <E extends Enum<E>> E valueOf(Class<E> type, int ordinal) {
        if (ordinal < 0) throw new IllegalArgumentException("Ordinal must not be negative");
        final E[] enums = type.getEnumConstants();
        if (ordinal < enums.length) {
            return enums[ordinal];
        }
        throw new IllegalArgumentException("No enum constant with ordinal " + ordinal);
    }
    
    public static <E extends Enum<E>> long add(long flag, E e) {
        return flag |= asBit(e);
    }
    
    public static <E extends Enum<E>> boolean has(long flag, E e) {
        return (flag & asBit(e)) > 0;
    }
    
    public static <E extends Enum<E>> long remove(long flag, E e) {
        return flag &= ~asBit(e);
    }
    
    public static long empty() {
        return 0x0l;
    }
    
    public static <E extends Enum<E>> Set<E> decode(Class<E> type, long flag) {
        final Set<E> enums = EnumSet.noneOf(type);
        if (flag == 0) return enums;
        
        for (E e : type.getEnumConstants()) {
            if (EnumUtility.has(flag, e)) enums.add(e);
        }
        
        return enums;
    }
    
    public static <E extends Enum<E>> long encode(Set<E> enums) {
        long flag = EnumUtility.empty();
        
        for (E e : enums) {
            flag = EnumUtility.add(flag, e);
        }
        return flag;
    }
    
    public static <E extends Enum<E>> Set<E> union(Collection<E> a, Collection<E> b) {
        final EnumSet<E> union = EnumSet.copyOf(a);
        union.addAll(b);
        return union;
    }
    
}