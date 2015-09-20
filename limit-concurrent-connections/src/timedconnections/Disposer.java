package timedconnections;

/**
 * Functional interface for Disposer functions that are run when a socket thread
 * closes.
 */

@FunctionalInterface
interface Disposer {
    public void dispose();
}
