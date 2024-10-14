# Spotify Clone - Audify 🎵

## Overview
**Audify** is an Android application that emulates core features of Spotify, such as browsing, managing, and playing audio content. The app also includes a mini-player and trending song categories, providing a simplified experience of a music streaming platform.

## Features
- 🎶 **Trending Songs**: Explore trending and popular songs across various genres.
- 📂 **Browse Local Music**: Access and play songs from the device's external storage.
- 📱 **Modern UI**: User-friendly interface, with a custom navigation bar and media player.
- 🛠️ **Modular Architecture**: Supports easy extension of music panels using `MultiSlidingUpPanel`.

## Screenshots
[Include some screenshots of your app here.]

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

## Contributing
If you'd like to contribute, feel free to fork the repository and submit a pull request.

1. Fork the project.
2. Create a feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some amazing feature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## Contact
If you have any questions, feel free to reach out:

- **Your Name**: [your-email@example.com](mailto:your-email@example.com)
- [LinkedIn](https://www.linkedin.com/in/yourprofile)

Made with efforts by [rudrx75]

### Instructions:

- Replace placeholder information like `yourusername`, `yourprofile`, and `your-email@example.com` with your actual details.
- Add screenshots by uploading them to your GitHub repository and linking them in the "Screenshots" section.
