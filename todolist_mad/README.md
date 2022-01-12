# Welcome to To-do List App!

This is basically an app where you can add tasks you have to do and set their priorities.
To-do List intended for people who prefer to manage their time and responsibilities.

![appicon](https://i.imgur.com/PCGB1HS.png)

# Figma:
  https://www.figma.com/file/AXRLwUcCctrWH5yfJVEouv/Untitled?node-id=0%3A1
# About App:

1) ListFragment is a host fragment, where all tasks are displayed as list items. Each item contains information about task: name of the task, date, priority of the task and options to edit and to delete a particular task. At the bottom right corner there is a fab button which navigates to the AddFragment, where user can create the task.

2) AddFragment contains EditText field to enter the task, radio buttons to set priority, imageView of the note and save button to save the note.

![addfragment](https://i.imgur.com/AZFn2vm.png)

3) When “edit” option is selected it navigates to UpdateFragment in order to update the task. The UI of this fragment is similar to AddFragments.

# User interface components:
- custom layouts
- RecyclerView + CardView (List)
- RadioButtons (Priority)
- EditText fields (To do task)
- Buttons (Save/Update/Add)
- TextViews
- ImageViews
- Material design icons (delete/edit)

Because I wanted to display data in a list where data can be changed I decided to implement RecyclerView + CardView. The views in the list are represented by view holder objects. Each view holder is in charge of displaying a single item with a view.The view holder objects are managed by adapter.

![addfragment](https://i.imgur.com/Im7Bd7L.png)

# MVVM architecture(Model-View-ViewModel):
 Separation of concerns is considered as best practice that’s why I decided to build this To-do list following MVVM Architecture.
Where
- Model is a class that has data to use.
- VIew(ui controller) is responsible only for layout structure displayed on the screen and UI related logic.
- ViewModel manages the data and provides it to View. Then, View that receives the state change notification determines whether to apply the change.

Also navigation component architecture and LiveData component  are included, which avoid memory consumptions and help to provide better user experience.

# Room
To work with local storage I used Room Persistence Library. It is a library that provides local data persistence with minimal boilerplate code. In order to work with Room we need 3 components: Dao, Entity and Database itself.
Dao interface contains methods to provide abstract access to the app’s database.
Entity contains data that will be stored in the database. By default, Room creates a column for each field that's defined in the entity.
All the operations are executed on the background thread

Good user experience is always the number one priority that’s why the interface is very nice and simple. And the app itself is very easy to use and understand. Minimalism is the best way to provide good user experience.
