package org.eldrelore.puzzle.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Abstract executor. new executors will need to implement retrieving the
 * callable collection.
 *
 * @param <T>
 * @param <R>
 */
public abstract class AbstractExecutor<T, R> {

	/**
	 * retrieves the collection of callables to be executed
	 * 
	 * @param requests
	 * @return Collection<Callable<T>>
	 */
	public abstract Collection<Callable<T>> getCallableCollection(Collection<R> requests);

	/**
	 * provided execute method
	 * 
	 * @param requests
	 * @return Collection<T>
	 */
	public Collection<T> execute(Collection<R> requests) {
		ExecutorService executor = Executors.newWorkStealingPool();
		Collection<T> collection = new ArrayList<T>();
		Collection<Callable<T>> callables = getCallableCollection(requests);
		try {
			executor.invokeAll(callables).parallelStream().map(future -> {
				return getCompletedFuture(future);
			}).forEach(t -> {
				collection.add(t);
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return collection;
	}

	/**
	 * 
	 * @param future
	 * @return
	 */
	private T getCompletedFuture(Future<T> future) {
		return getFutureResult(future);
	}

	private T getFutureResult(Future<T> future) {
		T response = null;
		try {
			response = future.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		return response;
	}
}
