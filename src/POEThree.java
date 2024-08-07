
import javax.swing.JOptionPane;


public class POEThree{
    private static boolean isLoggedIn = false;
 
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        while (true) {
            if (!isLoggedIn) {
                // Prompt for login
                JOptionPane.showInputDialog("Enter username:");
                JOptionPane.showInputDialog("Enter password:");

                // Here, you should verify login credentials. For simplicity, we'll assume successful login.
                isLoggedIn = true;
                JOptionPane.showMessageDialog(null, "Login successful.");
            }

            // Display menu options
            String[] options = {"Add tasks", "Show report", "Search for a task by name", "Search tasks by developer", "Delete a task by name", "Quit"};
            int option = JOptionPane.showOptionDialog(null, "Select an option:", "EasyKanban Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0 -> {
                    // Add tasks
                    int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter number of tasks to add:"));

                    for (int i = 0; i < numTasks; i++) {
                        JOptionPane.showInputDialog("Enter task name:");
                        JOptionPane.showInputDialog("Enter task description:");
                        JOptionPane.showInputDialog("Enter developer name:");
                        int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (hours):"));

                        String[] statusOptions = {"To Do", "Done", "Doing"};
                        int statusOption = JOptionPane.showOptionDialog(null, "Select task status:", "Task Status",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, statusOptions, statusOptions[0]);
                        String taskStatus = statusOptions[statusOption];
                        
                        
                    }
                }
                case 1 -> // Show report
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                case 2 -> {
                    // Search for a task by name
                    String searchTaskName = JOptionPane.showInputDialog("Enter task name to search:");
                    Task.searchTaskByName(searchTaskName);
                }
                case 3 -> {
                    // Search tasks by developer
                    String searchDeveloper = JOptionPane.showInputDialog("Enter developer name to search:");
                    Task.searchTasksByDeveloper(searchDeveloper);
                }
                case 4 -> {
                    // Delete a task by name
                    String deleteTaskName = JOptionPane.showInputDialog("Enter task name to delete:");
                    Task.deleteTaskByName(deleteTaskName);
                }
                case 5 -> {
                    // Quit
                    Task.displayAllTasks();
                    JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + Task.returnTotalHours());
                    JOptionPane.showMessageDialog(null, "Exiting application.");
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid option, please try again.");
            }
            
        }
    }
}
