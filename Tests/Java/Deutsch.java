import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Deutsch {
    public static void main(String[] args) {
        // Create and show the initial window
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Execute Python Script GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        // Create components
        JLabel label = new JLabel("Enter some text:");
        JTextField textField = new JTextField(15);
        JButton submitButton = new JButton("Submit");

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the input box
                String inputText = textField.getText();

                // Execute the Python script and open a new window with the script's output
                openNewWindow(execPythonScript(inputText));
            }
        });

        // Set layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Add components to the frame
        frame.add(label);
        frame.add(textField);
        frame.add(submitButton);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static String execPythonScript(String inputText) {
        try {
            // Command to run the Python script with the input text
            String command = "python Deutsch.py 1" + inputText;

            // Create process builder
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                return output.toString();
            } else {
                return "Error executing Python script. Check your script and try again.";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error executing Python script. Check your script and try again.";
        }
    }

    private static void openNewWindow(String scriptOutput) {
        // Create the new window
        JFrame newFrame = new JFrame("Script Output");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(400, 200);

        // Create components for the new window
        JTextArea textArea = new JTextArea(scriptOutput);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Set layout for the new window
        newFrame.setLayout(new BoxLayout(newFrame.getContentPane(), BoxLayout.Y_AXIS));

        // Add components to the new window
        newFrame.add(scrollPane);

        // Center the new window on the screen
        newFrame.setLocationRelativeTo(null);

        // Make the new window visible
        newFrame.setVisible(true);
    }
}
