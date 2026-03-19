package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 * Stores tasks, prevents duplicate task IDs, finds tasks by ID, and returns tasks by status.
 *
 * @author Taylor Vander
 */
public class TaskManager {

    private Map<String, Task> tasks;

    /**
     * Constructs a new TaskManager.
     */
    public TaskManager() {
        tasks = new HashMap<>();
    }

    /**
     * Adds a task to the manager.
     * If a task with the same task ID already exists, this method throws an IllegalArgumentException.
     *
     * @param task the task to add
     * @throws IllegalArgumentException if a task with the same task ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID");
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by its task ID.
     *
     * @param taskId the task ID to search for
     * @return the matching Task, or null if no task is found
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns all tasks whose status matches the specified status.
     *
     * @param status the status to match
     * @return a list of tasks with the specified status
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}