# HOWTO USE: [python GermGetAnswerID.py bitte] will output the answer_id accompanying the input [1]
import paramiko
import sys

def execute_mysql_command(answer_text):
    # SSH Configuration
    ssh_key_path = "GermDatabaseKey.pem"
    ssh_username = "ubuntu"
    ssh_host = "ec2-51-20-127-220.eu-north-1.compute.amazonaws.com"

    # MySQL Configuration
    mysql_container_name = "mysql"
    mysql_user = "root"
    mysql_password = "my-secret-pw"  # Enter your MySQL password

    # MySQL Commands
    mysql_commands = f"USE GermWords; SELECT COUNT(*) AS question_count FROM Questions;"

    # Establish SSH connection
    try:
        ssh_client = paramiko.SSHClient()
        ssh_client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        private_key = paramiko.RSAKey(filename=ssh_key_path)
        ssh_client.connect(ssh_host, username=ssh_username, pkey=private_key)

        # Build the SSH command to execute MySQL commands within the Docker container
        ssh_command = f"sudo docker exec -i {mysql_container_name} mysql -u {mysql_user} -p{mysql_password} -N -B -e \"{mysql_commands}\""

        # Execute the command and capture the output
        stdin, stdout, stderr = ssh_client.exec_command(ssh_command)

        # Print any errors
        error_output = stderr.read().decode()
        if error_output:
            print(f"Error during execution: {error_output}")

        # Print only the result without column names
        result_output = stdout.read().decode().strip()
        print(result_output)

    except Exception as e:
        print(f"Error: {e}")

    finally:
        # Close SSH connection
        if ssh_client:
            ssh_client.close()

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python GermGetAnswerID.py <answer_text>")
    else:
        answer_text = sys.argv[1]
        execute_mysql_command(answer_text)
