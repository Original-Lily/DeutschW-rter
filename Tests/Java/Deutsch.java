import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Deutsch {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI(args));
    }

    private static void createAndShowGUI(String[] args) {
        JFrame frame = new JFrame("Python Script Executor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // Rows, columns, horizontal gap, vertical gap

        // Initial label text
        JLabel label = new JLabel(" ");
        JTextField inputTextField = new JTextField();
        JButton submitButton = new JButton("Submit");

        // Add a WindowListener to capture the window open event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // Goal 1: Update label text with the result of running the Python script on window open
                if (args.length > 0) {
                    String result = execPythonQuestionScript(args[0]);
                    label.setText(result);
                }
            }
        });

        submitButton.addActionListener(e -> {
            // Goal 1: Update label text with the result of running the Python question script
            String inputText = inputTextField.getText();
            String result = execPythonQuestionScript(inputText);
            label.setText(result);

            // Goal 2: Open new window with the result of running the Python answer script
            openNewWindow(execPythonAnswerScript(getAnswerIDFromID(Integer.parseInt(args[0]))));
        });

        panel.add(label);
        panel.add(inputTextField);
        panel.add(submitButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Goal 1: Execute Python script to get question text
    private static String execPythonQuestionScript(String inputText) {
        try {
            String command = "python GermGetQuestionText.py " + inputText;
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                return output.toString();
            } else {
                return "Error executing Python question script. Check your script and try again.";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error executing Python question script. Check your script and try again.";
        }
    }

    // Goal 2: Execute Python script to get answer text
private static String execPythonAnswerScript(String answerID) {
    try {
        // Print the input before executing the Python script
        System.out.println("Input to GermGetAnswerText.py: " + answerID);

        String command = "python GermGetAnswerText.py " + answerID;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitCode = process.waitFor();

        if (exitCode == 0) {
            return output.toString();
        } else {
            return ("Error executing Python answer script. Check your script and try again. The answerID inputted was: " + answerID);
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        return "Error executing Python answer script. Check your script and try again.";
    }
}


    // Helper function to get answer ID from input ID
    private static String getAnswerIDFromID(int inputID) {
        try {
            String command = "python GetAnswerIDfromID.py " + inputID;
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                return output.toString().trim(); // Trim any leading or trailing whitespace
            } else {
                return ("Error getting answer ID. Check your script and try again. The inputID you entered is: " + inputID);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error getting answer ID. Check your script and try again.";
        }
    }

    private static void openNewWindow(String scriptOutput) {
        JFrame newFrame = new JFrame("Script Output");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(600, 300);

        JTextArea textArea = new JTextArea(scriptOutput);
        JScrollPane scrollPane = new JScrollPane(textArea);

        newFrame.setLayout(new BorderLayout());
        newFrame.add(scrollPane, BorderLayout.CENTER);

        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
    }
}
