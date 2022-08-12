
![logo](https://user-images.githubusercontent.com/65492308/184339965-b99ff6f9-da53-450a-899b-d8944a688a4d.png)

About App
-------------------------------------------------------

Note App is a simple note taking application 📝. Written in Kotlin and implements android libraries. All Changes are stored in the Room database.


Functionality
-------------------------------------------------------
. Create notes.
. Delete notes onSwipe.
. Update notes
. Dark Mode.

Libraries used
-------------------------------------------------------
. Room
. Viewmodel
. Livedata
. Coroutines
. Material library
. Navigation Components
. viewBinding
. DataStore(Preferences)

Design Pattern
-------------------------------------------------------
. MVVM (Model-View-ViewModel) is one of the architectural patterns which enhances separation of concerns, 
  it allows separating the user interface logic from the business (or the back-end) logic. 
  Its target (with other MVC patterns goal) is to achieve the following principle
  “Keeping UI code simple and free of app logic in order to make it easier to manage

. Lifecycles: It manages activity and fragment lifecycles of our app,
  survives configuration changes, avoids memory leaks and easily loads data into our UI.

. LiveData: It notifies views of any database changes.
  Use LiveData to build data objects that notify views when the underlying database changes.

. Room: It is a SQLite object mapping library. 
  Use it to Avoid boilerplate code and easily convert SQLite table data to Java objects.
  Room provides compile time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.

. ViewModel: It manages UI-related data in a lifecycle-conscious way.
  It stores UI-related data that isn't destroyed on app rotations.

. Repository: The repository depends on a persistent data model and a remote backend data source.


![mvvm-architecture-pattern](https://user-images.githubusercontent.com/65492308/184340019-d7a51cf7-9d1c-42bd-9fb5-3940f2bf5fc6.png)

