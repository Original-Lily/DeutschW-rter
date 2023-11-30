import sys, random, subprocess

def main(selectedKey):
    # Replace 'YourJavaProgram' with the actual Java program file name (without the .java extension)
    java_program = 'Deutsch'

    # Compile the Java program
    compile_command = f'javac {java_program}.java'
    subprocess.run(compile_command, shell=True, check=True)

    # Run the compiled Java program
    run_command = f'java {java_program}'
    subprocess.run(run_command, shell=True, check=True)


selectedKey = random.randint(1,3)
