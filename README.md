**BM Movie App**

This is a simple Android app that displays a list of movies fetched from The Movie Database (TMDb) API. It uses Jetpack Compose for the UI, Retrofit for network calls, and Koin for dependency injection. The app follows the MVVM architecture pattern.

**Features**

Now Playing, Popular, and Upcoming movie lists.
View movie details on tap.
Dependency injection with Koin.

**Architecture**

The app is structured using the MVVM (Model-View-ViewModel) architecture pattern:

Model: Represents data classes (e.g., Movie, NowPlayingEntity) and data sources (e.g., MovieRepository).
View: The UI components are built using Jetpack Compose.
ViewModel: Manages UI-related data in a lifecycle-conscious way, interacting with the MovieRepository to fetch data.

**Setup and Installation**

Clone the repository:
git clone https://github.com/HossamScott/BM_MovieApp.git

cd bm-movieapp

Open the project in Android Studio.

Make sure you have the required SDK versions installed (SDK 34 or higher).

Build and run the app on your emulator or physical device.

Dependencies
This app uses the following dependencies:

Jetpack Compose: For building modern UIs.
Retrofit: For making network requests.
Koin: For Dependency Injection.
Coroutines: For handling asynchronous tasks.

**How to Use**

Movie Lists: The app fetches movie data from TMDb and shows three lists: Now Playing, Popular, and Upcoming movies.

Movie Detail: Clicking on a movie takes you to a detail screen where you can view more information such as the poster, overview, and rating.


**Folder Structure**

data: Contains all the data models, database entities, DAO (Data Access Objects), and repositories.
ui: Contains the UI components built with Jetpack Compose.
di: Contains the dependency injection setup using Koin.
util: Contains utility classes and constants.

[![Homepage](https://github.com/user-attachments/assets/2bfc53b1-f7b7-45e6-aa8a-f4a5f85ef0ae "Homepage")](https://github.com/user-attachments/assets/2bfc53b1-f7b7-45e6-aa8a-f4a5f85ef0ae "Homepage")

[![Movie Details](https://github.com/user-attachments/assets/dd4ea5c4-ff5d-4a19-8da2-4dc03e7729dc "Movie Details")](https://github.com/user-attachments/assets/dd4ea5c4-ff5d-4a19-8da2-4dc03e7729dc "Movie Details")
