import sys, random, subprocess

def main(selectedKey):
    # Replace 'YourJavaProgram' with the actual Java program file name (without the .java extension)
    java_program = 'Deutsch'

    # Compile the Java program
    compile_command = f'javac {java_program}.java'
    subprocess.run(compile_command, shell=True, check=True)

    # Run the compiled Java program with selectedKey as a command-line argument
    run_command = f'java {java_program} {selectedKey}'
    subprocess.run(run_command, shell=True, check=True)

pythonCountCommand = 'python GermGetQuestionCount.py 1'
QuestionCountProcess = subprocess.run(pythonCountCommand, shell=True, check=True, stdout=subprocess.PIPE)

# Extract numeric part from the output
QuestionCount = ''.join(c for c in QuestionCountProcess.stdout.decode().strip() if c.isdigit())

try:
    selectedKey = random.randint(1, int(QuestionCount))
    main(selectedKey)
except ValueError:
    print(f"Error: Unable to convert '{QuestionCount}' to an integer.")
