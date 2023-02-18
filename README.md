# Fridge Tracker: How to Build & Run

1) Install Android Studio
https://developer.android.com/studio

2) Open the application and navigate to the device manager

3) Press the "Create device" button and select the Pixel 5 to install the emulated device

4) Select "R" for system image and press finish

5) If you don't already have GitHub Desktop, install the app
https://docs.github.com/en/desktop/installing-and-configuring-github-desktop/installing-and-authenticating-to-github-desktop/installing-github-desktop

6) Clone this repository with URL: "caitlinlopez/COEN174"

7) Link your GitHub account to Android Studio and open the project

8) Press "Build" and then "Make Project", this prepares the project to be run on the virtual Android device and may take a few minutes to complete

9) Open the Device Manager and press the play button for the Pixel 5

10) Press "Run" and then "Run 'app'"


# Unit Testing & Infrastructure

Unit tests are accessible in 'Project' view.

- Go to /app/src/androidTest for UI tests

- Go to /app/src/test [unitTest] for functional tests (smoke test provided here)


# Troubleshooting 

The ```"Couldn’t Delete"``` error can occur when trying to delete a file or folder in your Android project, and it usually happens when the file or folder is locked or in use by another process. 

> Here are a few troubleshooting steps to resolve this following error:

1) Close Android Studio and try deleting the file/folder again: Sometimes the file/folder may be locked or in use by Android Studio itself, so closing the program and trying to delete the file/folder again may help. 

2) Run ```taskkill /im java.exe``` /f in your Android Studio command line terminal

3) Restart your computer: If the file/folder is still locked, restarting your computer may help release any processes that are using it. 

4) Check for other processes that may be using the file/folder: You can use the Task Manager on Windows or Activity Monitor on macOS to check for any processes that may be using the file/folder. If you find any processes that are using the file/folder, try closing them and then deleting the file/folder again. 

5) Manually delete the file/folder: If none of the above steps work, you can try manually deleting the file/folder using a file explorer. To do this, navigate to the file/folder you want to delete, right-click it, and select "Delete". If you get an error message saying that the file/folder is in use, try steps 2 and 3 again to release the lock on the file/folder, then try deleting it manually again. 

6) Run Android Studio as an administrator: If you are still unable to delete the file/folder, try running Android Studio as an administrator. To do this, right-click the Android Studio icon and select ```"Run as administrator"```. Once Android Studio has started, try deleting the file/folder again.
