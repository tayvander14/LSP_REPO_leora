/**
 * Name: Taylor Vander
 * @param args
 */

package org.howard.edu.lsp.assigment3;


/**
 * A generic interface that defines transformation behavior.
 *
 * Implementations of this interface apply specific
 * transformation logic to objects of type T.
 *
 */
public interface Transformer<T> {

    /**
     * Transforms an input object and returns a new transformed object.
     */
	    T transform(T input);
}
