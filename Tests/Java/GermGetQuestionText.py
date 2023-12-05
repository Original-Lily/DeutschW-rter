# HOWTO USE: [python GermGetQuestionText.py 1] will output the question_text accompanying the value given
import paramiko
import sys

def execute_mysql_command(question_id):
    # SSH Configuration
    ssh_key_path = "GermDatabaseKey.pem"
    ssh_username = "ubuntu"
    ssh_host = "ec2-16-171-235-203.eu-north-1.compute.amazonaws.com"

    # MySQL Configuration
    mysql_container_name = "mysql"
    mysql_user = "root"
    mysql_password = "my-secret-pw"  # Enter your MySQL password

    # MySQL Commands
    mysql_commands = f"USE GermWords; SELECT question_text FROM Questions WHERE question_id = {question_id};"

    # Establish SSH connection
    try:
        ssh_client = paramiko.SSHClient()
        ssh_client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        private_key = paramiko.RSAKey(filename=ssh_key_path)
        ssh_client.connect(ssh_host, username=ssh_username, pkey=private_key)

        # Build the SSH command to execute MySQL commands within the Docker container
        ssh_command = f"sudo docker exec -i {mysql_container_name} /bin/sh -c 'mysql -u {mysql_user} -p{mysql_password} -e \"{mysql_commands}\"'"

        # Execute the command and capture the output
        stdin, stdout, stderr = ssh_client.exec_command(ssh_command)

        # Parse the output to extract only the values from the "answer_text" field
        output_lines = stdout.read().decode().strip().split('\n')
        
        # Skip the first line (column name) and print the result from the "answer_text" field
        if len(output_lines) > 1:
            #print(f"Output for question_id {question_id}:")
            print(output_lines[1])

    except Exception as e:
        print(f"Error: {e}")

    finally:
        # Close SSH connection
        if ssh_client:
            ssh_client.close()

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python GermGetQuestionText.py <question_id>")
    else:
        question_id = int(sys.argv[1])
        execute_mysql_command(question_id)
