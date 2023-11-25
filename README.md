<h1>DeutschW-rter</h1>

<h2>Overview</h2>

<p>The DeutschW-rter is a unique program that combines the power of C++ and Python to facilitate comfortable and effective German language learning. This program leverages a combination of interactive pop-ups on your screen and a sophisticated Amazon Relational Database to provide a seamless learning experience.</p>

<h2>Features</h2>

<ul>
    <li><strong>Interactive Learning:</strong> Engage in a dynamic learning experience with pop-up prompts that introduce German words and phrases at a comfortable rate.</li>
    <li><strong>C++ and Python Integration:</strong> The program utilizes the strengths of both C++ and Python to create a robust and user-friendly language learning environment.</li>
    <li><strong>Amazon Relational Database:</strong> Store and manage the words and phrases to be learned in an Amazon RDS, ensuring efficient data organization and retrieval.</li>
</ul>

<h2>Getting Started</h2>

<h3>Prerequisites</h3>

<ol>
    <li>Install C++ compiler (e.g., GCC)</li>
    <li>Install Python (version 3.0 or higher)</li>
    <li>Set up an Amazon RDS instance and obtain necessary credentials</li>
</ol>

<h3>Installation</h3>

<ol>
    <li>Clone this repository:</li>
    <pre><code>git clone https://github.com/your-username/DeutschWoerter.git 
     cd DeutschWoerter</code></pre>
 
<li>Compile the C++ program:</li>
<pre><code>g++ main.cpp -o DeutschWoerter</code></pre>

<li>Install Python dependencies:</li>
<pre><code>pip install -r requirements.txt</code></pre>

<li>Configure the database connection by updating the <code>config.py</code> file with your Amazon RDS credentials.</li>
</ol>

<h2>Usage</h2>

<ol>
    <li>Run the compiled C++ program:</li>
    <pre><code>./DeutschWoerter</code></pre>
    <li>The program will initiate pop-ups on your screen with German words and phrases.</li>
    <li>Interact with the pop-ups to learn and reinforce your German language skills.</li>
</ol>

<h2>Database Management</h2>

<ul>
    <li>To add new words or phrases to the learning database, update the appropriate table in your Amazon RDS instance.</li>
    <li>Customize the learning experience by modifying the content in the database.</li>
</ul>

<h2>Contribution</h2>

<p>Contributions are welcome! If you have ideas for improvements, feel free to open an issue or submit a pull request.</p>
