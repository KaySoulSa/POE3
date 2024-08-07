import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public final class Task {
    private static int taskCounter = 0;
    private final static List<Task> tasks = new ArrayList<>();
    
    private final static String[] developers = new String[100];
    private final static String[] taskNames = new String[100];
    private final static String[] taskIDs = new String[100];
    private final static int[] durations = new int[100];
    private final static String[] statuses = new String[100];
    
    private final String taskName;
    private final String taskDescription;
    private final String developerName;
    private final  int duration;
    private final String taskStatus;

    private final String taskID;

    public Task(String taskName, String taskDescription, String developerName, int duration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerName = developerName;
        this.duration = duration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
        
        int index = taskCounter - 1;
        developers[index] = developerName;
        taskNames[index] = taskName;
        taskIDs[index] = taskID;
        durations[index] = duration;
        statuses[index] = taskStatus;
        JOptionPane.showMessageDialog(null, printTaskDetails());
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    public String createTaskID() {
        if (!checkTaskDescription()) {
            return "";
        }
        String initials = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String devInitials = developerName.split(" ")[0].substring(0, 1) + developerName.split(" ")[1].substring(0, 1);
        return initials + ":" + taskCounter++ + ":" + devInitials.toUpperCase();
    }

    public String printTaskDetails() {
        if (!checkTaskDescription()) {
            return "Task Description is too long.";
        }
        return String.format("Task Status: %s\nDeveloper Details: %s\nTask Number: %d\nTask Name: %s\nTask Description: %s\nTask ID: %s\nDuration: %d hrs",
                taskStatus, developerName, taskCounter - 1, taskName, taskDescription, taskID, duration);
    }

    public static int returnTotalHours() {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.duration;
        }
        return totalHours;
    }

    public static void displayDoneTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskCounter; i++) {
            if ("Done".equals(statuses[i])) {
                result.append(String.format("Developer: %s, Task Name: %s, Duration: %d hrs%n", developers[i], taskNames[i], durations[i]));
            }
        }
        JOptionPane.showMessageDialog(null, result.length() > 0 ? result.toString() : "No tasks are marked as Done.");
    }

    public static void displayLongestDurationTask() {
        int maxIndex = 0;
        for (int i = 1; i < taskCounter; i++) {
            if (durations[i] > durations[maxIndex]) {
                maxIndex = i;
            }
        }
        JOptionPane.showMessageDialog(null, String.format("Developer: %s, Duration: %d hrs", developers[maxIndex], durations[maxIndex]));
    }

    public static void searchTaskByName(String taskName) {
        for (int i = 0; i < taskCounter; i++) {
            if (taskNames[i].equals(taskName)) {
                JOptionPane.showMessageDialog(null, String.format("Task Name: %s, Developer: %s, Status: %s", taskNames[i], developers[i], statuses[i]));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    public static void searchTasksByDeveloper(String developerName) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskCounter; i++) {
            if (developers[i].equals(developerName)) {
                result.append(String.format("Task Name: %s, Status: %s%n", taskNames[i], statuses[i]));
            }
        }
        JOptionPane.showMessageDialog(null, result.length() > 0 ? result.toString() : "No tasks found for this developer.");
    }

    public static void deleteTaskByName(String taskName) {
        for (int i = 0; i < taskCounter; i++) {
            if (taskNames[i].equals(taskName)) {
                // Shift elements to the left
                for (int j = i; j < taskCounter - 1; j++) {
                    taskNames[j] = taskNames[j + 1];
                    developers[j] = developers[j + 1];
                    taskIDs[j] = taskIDs[j + 1];
                    durations[j] = durations[j + 1];
                    statuses[j] = statuses[j + 1];
                }
                taskCounter--;
                JOptionPane.showMessageDialog(null, "Task deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    public static void displayAllTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskCounter; i++) {
            result.append(String.format("Task Status: %s%nDeveloper Details: %s%nTask Number: %d%nTask Name: %s%nTask ID: %s%nDuration: %d hrs%n%n",
                    statuses[i], developers[i], i, taskNames[i], taskIDs[i], durations[i]));
        }
        JOptionPane.showMessageDialog(null, result.length() > 0 ? result.toString() : "No tasks available.");
    }
}
