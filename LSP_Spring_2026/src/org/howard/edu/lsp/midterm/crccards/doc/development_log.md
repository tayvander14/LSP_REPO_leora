For Question #1, I also used ChatGPT. Below is the complete transcript: 

I am working on my midterm for Large Scale Programming. Here is  question #1.

Question 1 - Implement Classes from CRC Cards (40 points)
The following CRC cards describe a simple Task Management System. Using these CRC cards, implement the required Java classes.
Requirements
•	Each class must be in its own .java file.
•	Use the package org.howard.edu.lsp.midterm.crccards in every Java class.
GitHub Push Requirement:
Push your solution to your GitHub repository early and often during the exam. Students are strongly encouraged to push their work multiple times throughout the exam to avoid losing work due to system failures. Only code that exists in the GitHub repository at the end of the exam will be graded.
•	Your implementation must work with the Driver class provided below.
•	Do not modify the Driver class.
•	TaskManager must select and use an appropriate Java data structure to manage Task objects according to the requirements.
•	All classes and public methods must include Javadoc comments.
•	You must include the required documentation files described below.
•	You may use any standard classes from the Java Collections Framework (for example, List, ArrayList, Map, etc.).
CRC Cards
Class	Responsibilities	Collaborators
Task	• store task information
• update task status
• provide task details	none
TaskManager	• store tasks
• add new tasks
• find a task by ID
• return tasks by status	Task
Part 1 - Implement Task
Fields: taskId, description, status
Constructor:
public Task(String taskId, String description)
Rule: The default status must be "OPEN".
Required methods:
public String getTaskId()
public String getDescription()
public String getStatus()
public void setStatus(String status)
public String toString()
The toString() method must produce output in the following format:
T1 Write report [OPEN]

Where the above is defined by: taskId description [status]
Valid status values include: OPEN, IN_PROGRESS, COMPLETE.
Status comparisons are case-sensitive.
If setStatus() is called with any value other than the valid status values listed above, the task's status must be set to UNKNOWN.
Part 2 - Implement TaskManager
TaskManager manages a collection of Task objects.
You must select and use an appropriate Java data structure to store tasks internally. Your design should support:
•	storing tasks
•	locating a task by its taskId
•	preventing duplicate task IDs
•	retrieving tasks by status
The internal data structure you choose is up to you, provided it supports the required behavior.
Required methods:
public void addTask(Task task)
public Task findTask(String taskId)
public List<Task> getTasksByStatus(String status)
Important note: getTasksByStatus() must return a List<Task> containing all tasks whose status matches the specified value. This return type does not restrict the internal data structure used by TaskManager.
Duplicate Task Rule: TaskManager must not allow duplicate task IDs. If a task with the same taskId already exists, addTask(Task task) must throw IllegalArgumentException.
Additional behavior: If findTask() cannot locate the requested task ID, it must return null.
Javadoc Requirement
All classes and public methods must include Javadoc comments.
•	Each class must include a brief description of the class and author name.
•	Each public method must include a description of what the method does, @param descriptions where applicable, and @return descriptions where applicable.


Documentation Requirements
Documentation files must be placed in package:
org.howard.edu.lsp.midterm.crccards.doc

development_log.md (5 points):
Create a file named development_log.md that documents any external resources used while completing this problem. External resources include but are not limited to: AI tools (ChatGPT, Claude, Gemini, Copilot, etc.), Google searches, websites, online documentation, lecture notes, textbooks, or any other reference material.

For each resource used, provide the following:

• If you used an AI tool: You must include the complete conversation transcript (all prompts and responses) used to help produce your solution. Partial transcripts or summaries are not acceptable.

• If you used websites, documentation, Stack Overflow, articles, or other online materials: You must include the full URL and a brief explanation of how that resource helped you complete the problem.

• If you used books, lecture notes, or other offline references: Cite the source (title, author, and section if applicable) and explain how it helped.

If no external assistance was used, write exactly:
"No external assistance was used."
2. crccards.md (5 points): In 2-3 sentences, explain why TaskManager collaborates with Task, but Task does not collaborate with TaskManager. Your explanation should reference the responsibilities listed in the CRC cards.
Provided Driver Class
Do not modify this class.
package org.howard.edu.lsp.midterm.crccards;

import java.util.List;

public class Driver {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        Task t1 = new Task("T1", "Write report");
        Task t2 = new Task("T2", "Study for exam");
        Task t3 = new Task("T3", "Submit homework");

        manager.addTask(t1);
        manager.addTask(t2);
        manager.addTask(t3);

        t2.setStatus("IN_PROGRESS");

        System.out.println("OPEN TASKS");

        List<Task> openTasks = manager.getTasksByStatus("OPEN");

        for (Task t : openTasks) {
            System.out.println(t.toString());
        }

        System.out.println("\nTESTING DUPLICATE TASK ID");

        try {
            Task duplicate = new Task("T1", "Duplicate task");
            manager.addTask(duplicate);
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate task ID detected.");
        }

        System.out.println("\nTESTING INVALID STATUS");

        Task t4 = new Task("T4", "Invalid status test");
        t4.setStatus("DONE");
        System.out.println(t4.toString());

        System.out.println("\nTESTING FIND TASK");

        Task found = manager.findTask("T2");
        System.out.println(found);

        Task notFound = manager.findTask("T99");

        if (notFound == null) {
            System.out.println("Task not found.");
        }
    }
}
Expected Output
OPEN TASKS
T1 Write report [OPEN]
T3 Submit homework [OPEN]

TESTING DUPLICATE TASK ID
Duplicate task ID detected.

TESTING INVALID STATUS
T4 Invalid status test [UNKNOWN]

TESTING FIND TASK
T2 Study for exam [IN_PROGRESS]
Task not found.



Grading Rubric (40 points)
Component	Points
Task class implementation	10
TaskManager implementation	10
Driver execution and required functionality	5
Javadoc documentation	5
Development log	5
CRC delegation explanation (crccards.md)	5

 

I’ll put together a clean, driver-compatible implementation and the two markdown docs. One thing to flag: the development log requirement mentions a full AI transcript, so I’ll make sure the files are structured correctly and note where you may need to replace placeholder author info.

I’ve started the source tree and am wiring the classes to match the driver exactly, including the duplicate-ID behavior and status handling. After that I’ll verify the code compiles and produces the expected output.

The code compiles and matches the driver’s expected output. I’m packaging the files so you can drop them into your project or inspect them directly.

Thought for 37s
I put the solution together and verified it against the provided driver. The package structure, duplicate-ID handling, status rules, and output all match the expected behavior.

Files:

Task.java

TaskManager.java

crccards.md

development_log.md

Full zipped solution

One important note: the current development_log.md says No external assistance was used. Because this chat used AI help, your course’s logging rule may require you to replace that with the required transcript before submitting.


I don't want you to give me the files. let's start with part 1. 

Thought for a second
Here is Part 1: Task.

package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a single task in the task management system.
 * Stores task information, tracks task status, and provides task details.
 *
 * @author Your Name
 */
public class Task {
    
    private String taskId;
    private String description;
    private String status;
    
    /**
     * Constructs a new Task with the given task ID and description.
     * The default status is set to OPEN.
     *
     * @param taskId the unique identifier for the task
     * @param description the task description
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }
    
    /**
     * Returns the task ID.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }
    
    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the current task status.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Sets the task status.
     * Valid values are OPEN, IN_PROGRESS, and COMPLETE.
     * Any other value sets the status to UNKNOWN.
     *
     * @param status the new status to assign
     */
    public void setStatus(String status) {
        if ("OPEN".equals(status) || "IN_PROGRESS".equals(status) || "COMPLETE".equals(status)) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }
    
    /**
     * Returns a string representation of the task in the format:
     * taskId description [status]
     *
     * @return the formatted task string
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}
This matches the driver’s expectations:

new tasks start as OPEN

valid statuses are case-sensitive

invalid statuses become UNKNOWN

toString() prints exactly like T1 Write report [OPEN]

Next, I can help you with Part 2: TaskManager.


makes sense. now part 2:

Part 2 - Implement TaskManager
TaskManager manages a collection of Task objects.
You must select and use an appropriate Java data structure to store tasks internally. Your design should support:
•	storing tasks
•	locating a task by its taskId
•	preventing duplicate task IDs
•	retrieving tasks by status
The internal data structure you choose is up to you, provided it supports the required behavior.
Required methods:
public void addTask(Task task)
public Task findTask(String taskId)
public List<Task> getTasksByStatus(String status)
Important note: getTasksByStatus() must return a List<Task> containing all tasks whose status matches the specified value. This return type does not restrict the internal data structure used by TaskManager.
Duplicate Task Rule: TaskManager must not allow duplicate task IDs. If a task with the same taskId already exists, addTask(Task task) must throw IllegalArgumentException.
Additional behavior: If findTask() cannot locate the requested task ID, it must return null.
Javadoc Requirement
All classes and public methods must include Javadoc comments.
•	Each class must include a brief description of the class and author name.
•	Each public method must include a description of what the method does, @param descriptions where applicable, and @return descriptions where applicable.


Documentation Requirements
Documentation files must be placed in package:
org.howard.edu.lsp.midterm.crccards.doc

development_log.md (5 points):
Create a file named development_log.md that documents any external resources used while completing this problem. External resources include but are not limited to: AI tools (ChatGPT, Claude, Gemini, Copilot, etc.), Google searches, websites, online documentation, lecture notes, textbooks, or any other reference material.

For each resource used, provide the following:

• If you used an AI tool: You must include the complete conversation transcript (all prompts and responses) used to help produce your solution. Partial transcripts or summaries are not acceptable.

• If you used websites, documentation, Stack Overflow, articles, or other online materials: You must include the full URL and a brief explanation of how that resource helped you complete the problem.

• If you used books, lecture notes, or other offline references: Cite the source (title, author, and section if applicable) and explain how it helped.

If no external assistance was used, write exactly:
"No external assistance was used."
2. crccards.md (5 points): In 2-3 sentences, explain why TaskManager collaborates with Task, but Task does not collaborate with TaskManager. Your explanation should reference the responsibilities listed in the CRC cards.
Provided Driver Class
Do not modify this class.
package org.howard.edu.lsp.midterm.crccards;

import java.util.List;

public class Driver {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        Task t1 = new Task("T1", "Write report");
        Task t2 = new Task("T2", "Study for exam");
        Task t3 = new Task("T3", "Submit homework");

        manager.addTask(t1);
        manager.addTask(t2);
        manager.addTask(t3);

        t2.setStatus("IN_PROGRESS");

        System.out.println("OPEN TASKS");

        List<Task> openTasks = manager.getTasksByStatus("OPEN");

        for (Task t : openTasks) {
            System.out.println(t.toString());
        }

        System.out.println("\nTESTING DUPLICATE TASK ID");

        try {
            Task duplicate = new Task("T1", "Duplicate task");
            manager.addTask(duplicate);
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate task ID detected.");
        }

        System.out.println("\nTESTING INVALID STATUS");

        Task t4 = new Task("T4", "Invalid status test");
        t4.setStatus("DONE");
        System.out.println(t4.toString());

        System.out.println("\nTESTING FIND TASK");

        Task found = manager.findTask("T2");
        System.out.println(found);

        Task notFound = manager.findTask("T99");

        if (notFound == null) {
            System.out.println("Task not found.");
        }
    }
}
Expected Output
OPEN TASKS
T1 Write report [OPEN]
T3 Submit homework [OPEN]

TESTING DUPLICATE TASK ID
Duplicate task ID detected.

TESTING INVALID STATUS
T4 Invalid status test [UNKNOWN]

TESTING FIND TASK
T2 Study for exam [IN_PROGRESS]
Task not found.


Thought for a couple of seconds
Use a HashMap<String, Task> internally. It gives fast lookup by taskId, makes duplicate detection simple, and still lets you collect tasks by status when needed.

package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 * Stores tasks, prevents duplicate task IDs, finds tasks by ID, and returns tasks by status.
 *
 * @author Your Name
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
This works with the driver because:

addTask() rejects duplicate IDs with IllegalArgumentException

findTask() returns null when a task is missing

getTasksByStatus() returns a List<Task> with all matching tasks