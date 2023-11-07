package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    /**
     * Internal ArrayList of the expected type of elements
     */
    private ArrayList<T> internalArray = new ArrayList<>();
    /**
     * Policy which determines the element which are meant to be returned
     */
    private Predicate<T> filter;

    public IterableWithPolicyImpl (final T[] vec) {
        this(vec, i -> (i == i));
    }

    public IterableWithPolicyImpl (final T[] vec, Predicate<T>filter ) {
        for ( T elem : vec ) {
            this.internalArray.add(elem);
        }
        setIterationPolicy(filter);

    }
    
    /**
     * Generates a custom iterator which visits the internal array, but shows just the
     * elements which respect the policy contained in filter
     */
    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
        
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    /**
     * This class implements a generic Iterator of T type, using an inner class
     */
    class CustomIterator implements Iterator<T> {

        /**
         * is the index by it's possible to iterate the internal array.
         * Starts from zero because of the internal structure of this class.
         */
        private int current = 0;

        /**
         * { @inheritDoc }
         */
        @Override
        public boolean hasNext() {
            while (this.current < internalArray.size()) {
                if (filter.test(internalArray.get(this.current))) {
                    return true;
                }
                else {
                    current++;
                }
            }
            return false;
        }

        /**
         * { @inheritDoc }
         */
        @Override
        public T next() {
            return internalArray.get(this.current++);
        }

    }

    
}
