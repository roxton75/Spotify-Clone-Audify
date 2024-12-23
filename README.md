# Spotify Clone - Audify 🎵

## Overview
**Audify** is an Android application that emulates core features of Spotify, such as browsing, managing, and playing audio content. The app also includes a mini-player and trending song categories, providing a simplified experience of a music streaming platform.

## Features
- 🎶 **Trending Songs**: Explore trending and popular songs across various genres.
- 📂 **Browse Local Music**: Access and play songs from the device's external storage.
- 📱 **Modern UI**: User-friendly interface, with a custom navigation bar and media player.

## Screenshots
App Logo:

![Audify Launcher Logo  (1)](https://github.com/user-attachments/assets/b0f246c1-ccf8-49fe-bc38-8560feaf50e3)

Here are some of the Screenshots of the UI:

![image](https://github.com/user-attachments/assets/36cd87d0-4a3f-4920-9c23-0ab9a9a3edf5)
![image](https://github.com/user-attachments/assets/0dba0d28-817e-4e35-8c30-413724af892c)
![image](https://github.com/user-attachments/assets/daac2e87-7130-472a-bd32-02baea088020)
![image](https://github.com/user-attachments/assets/70e2892f-1454-4113-9329-bab15deeb0f5)
![image](https://github.com/user-attachments/assets/88e3de9b-83b9-4e7c-a5d9-fed4b0197e43)

## Technologies Used
- **Language**: Java, Kotlin
- **Development Environment**: Android Studio
- **Libraries**:
  - [Dexter](https://github.com/Karumi/Dexter): For handling runtime permissions.
  - [Firebase Firestore](https://firebase.google.com/docs/firestore): Cloud database for storing user data.
  - [MultiSlidingUpPanel](https://github.com/realgearinc/multi-sliding-up-panel): For creating the multi-sliding panel in the app.

## Project Structure
The project follows a standard Android app structure:
```
- app/
  - manifests/
  - java/com/example/spotifyclone/
    - Categories/  // Trending songs, categories implementation
    - views/       // Views like RootMediaPlayerPanel, RootNavigationBarPanel
  - res/           // Layouts, drawables, etc.
```

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/spotifyclone.git
   ```
   
2. Open the project in Android Studio.
   
3. Build the project and run it on an Android device or emulator.

## Permissions
Audify requires the following permissions to function:
- **Read External Storage**: To access and display audio files stored on the device.
  
Make sure to grant the necessary permissions when prompted.

## How to Use

1. Launch the app and explore the trending music categories.
2. Use the mini-player to control playback.
3. Search for local music stored on your device using the built-in search functionality.
4. Navigate through different categories using the custom navigation bar.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
And it is an OpenSource Project so feel free to make changes and commit

## Contributing
If you'd like to contribute, feel free to fork the repository and submit a pull request.

1. Fork the project.
2. Create a feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some amazing feature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## Contact
If you have any questions, feel free to reach out:
Made with efforts by [rudrx75](https://github.com/roxton75/)

### Instructions:

- Replace placeholder information like `yourusername`, `yourprofile`, and `your-email@example.com` with your actual details.
- Add screenshots by uploading them to your GitHub repository and linking them in the "Screenshots" section.
