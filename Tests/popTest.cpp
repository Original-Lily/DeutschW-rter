#include <windows.h>
#include <string>

const int ID_EDIT = 101;
const int ID_BUTTON = 102;

HWND hEdit;

LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam) {
    switch (uMsg) {
        case WM_CREATE: {
            // Create an edit control (text input)
            hEdit = CreateWindow("EDIT", "", WS_VISIBLE | WS_CHILD | WS_BORDER,
                                 10, 10, 200, 20, hwnd, (HMENU)ID_EDIT, nullptr, nullptr);

            // Create a button
            CreateWindow("BUTTON", "Submit", WS_VISIBLE | WS_CHILD,
                         10, 40, 80, 25, hwnd, (HMENU)ID_BUTTON, nullptr, nullptr);
            break;
        }
        case WM_COMMAND: {
            if (LOWORD(wParam) == ID_BUTTON) {
                if (HIWORD(wParam) == BN_CLICKED) {
                    // Handle button click event
                    char buffer[256];
                    GetWindowText(hEdit, buffer, sizeof(buffer));

                    // Display the entered text in a message box
                    MessageBox(hwnd, buffer, "User Input", MB_OK | MB_ICONINFORMATION);
                }
            }
            break;
        }
        case WM_CLOSE: {
            DestroyWindow(hwnd);
            break;
        }
        case WM_DESTROY: {
            PostQuitMessage(0);
            break;
        }
        default: {
            return DefWindowProc(hwnd, uMsg, wParam, lParam);
        }
    }
    return 0;
}

int main() {
    HINSTANCE hInstance = GetModuleHandle(nullptr);

    WNDCLASS wc = {};
    wc.lpfnWndProc = WindowProc;
    wc.hInstance = hInstance;
    wc.lpszClassName = "MyClass";
    RegisterClass(&wc);

    HWND hwnd = CreateWindowEx(0, "MyClass", "Simple GUI", WS_OVERLAPPEDWINDOW,
                               100, 100, 300, 150, nullptr, nullptr, hInstance, nullptr);

    ShowWindow(hwnd, SW_SHOWDEFAULT);

    MSG msg = {};
    while (GetMessage(&msg, nullptr, 0, 0)) {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    return 0;
}
