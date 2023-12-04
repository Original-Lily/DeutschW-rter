#include <Python.h>

int main(int argc, char* argv[]) {
    // Initialize the Python interpreter
    Py_Initialize();

    // Run your Python script
    FILE* file = fopen("DeutschW-rter.py", "r");
    if (file != nullptr) {
        PyRun_SimpleFile(file, "DeutschW-rter.py");
        fclose(file);
    }

    // Finalize the Python interpreter
    Py_Finalize();

    return 0;
}
