/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.queue;

import org.duracloud.mill.domain.Task;

/**
 * 
 * @author Daniel Bernstein
 * 
 */
public interface TaskQueue {

    /**
     * puts a task on the queue
     * 
     * @param task
     */
    public void put(Task task);

    /**
     * Blocks until a task is available
     * 
     * @return
     */
    public Task take() throws TimeoutException;

    /**
     * Provides the default visibility value
     *
     * @return
     */
    public Integer getDefaultVisibilityTimeout();

    /**
     * Responsible for robustly extending the visibility timeout.
     * 
     * @param task
     * @param seconds
     * @throws TaskNotFoundException
     */
    public void extendVisibilityTimeout(Task task, Integer seconds)
            throws TaskNotFoundException;

    /**
     * Deletes a task from the queue.
     * 
     * @param task
     */
    public void deleteTask(Task task) throws TaskNotFoundException;
}