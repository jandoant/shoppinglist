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