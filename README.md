### v.00 - Initial Commit

### v01 - connect to Firebase
* install Firebase
* write the name of a List to DB via `AddListDialog`
* changed icons of `FAB`s
* added titles to the Dialogs

### v02 - read data from and write data to Firebase
* in AddListDialog: creates or edits name of one List
* in ShoppingListFragment: shows that very ListName in a single TextField

### v03 - create a ShoppingList-POJO
* String owner,
* String name

### v04 - use ShoppingList-Pojo to write and read from Firebase

### v05 - store and display the Time and Date the ShoppingList was updated

### v06 - open List DetailScreen
* create new Activity
* explicit Intent to open that Activity, when Active List TextView is clicked
* load Data from DB to display Name of the List in the Title of the Action Bar in Detail Activity

### v07 - Implement EditListName Functionality (Detail Screen)
* Edit Name Button in OptionsMenu
* when clicking on that Button -> show Edit ListName Dialog
* Display current ListName in EditDialog
* update the ListName and LastChangedTimestamp Value in Firebase

### v08 - add Timestamp created to the ShoppingList - Object

### v09 - Create Muliple Lists with unique keys in Firebase
* create a new Unique-ID under activeLists - Node using push
* insert a new shooping list item under that unique node
* display the data in the TextView

### v10 - Show ActiveLists in ListView
* install the library - `compile 'com.firebaseui:firebase-ui:0.2.2'` in app-gradle-File
* make a ListView - `mListView`
* add a layout - `single_activ_list.xml`
* add a firebase reference pointing at the root of data array that needs to be displayed
* create and attach the adapter - extend from FirebaseAdapter
* override populateView() in Adapter

### v11 - fix Edit Functionality

### v12 - removing single ShoppingList via DetailActivity
* delete a Item with the chosen pushID from Firebase
* create Dialog that warns User about Deletion

### v13 - add ShoppingListItem Pojo to Project
* String name
* String owner
* getters

### v14 - add ShoppingItem to a list
 * when user clicks on FAB-Button AddShoppingItemDialog opens
 * When user approves - Item is added to the database
 * ListView in DetailScreen shows ListView of Shopping Items

### v15 - remove all associated ShoppingItems when List gets removed

### v16 - remove EventListeners to prevent Memory Leak

### v17 - edit ShoppingItemName onLongItemClick
* show EditItemDialog when longClicking
* update ItemName in Firebase

### v18 - delete Shopping Item when clicked on TrashIcon
* create ImageButton in Item View
* onClickListener on Image in Adapter to open Dialog
* remove Item from Firebase by Confirming Dialog

# v1.00 - User Authentification

### v1.01 - Copy Login Activities and Layout-Files from GitHub

### v1.02 - Enable Creation of a new User Account
* make LoginActivity the Launcher Activity
* Create Account Activity gets started when User hits "Sign Up"
* create a User Account, when User enters Data in Input Fields and hits "Create Account"
* Check that the password is at least 5 characters long and that the email is in the format of an email (see below for how)

### v1.03 - improved input validation (create Account)
* User name must consist of first and last name
* Helpful Error Message, when Email is already registered in Firebase

### v1.04 - add Login Functionality (EMail & Password)
* successful Login redirects to `MainActivity`
* show helpful Error Messages when Authentification fails

### v1.05 - Create User in Firebase DB and store User Data