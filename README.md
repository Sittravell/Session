# Session
Session help you when want to store user data outside your application, so that when the next time user use your application, you can easily get back his details and perform accordingly. This is commonly used for Login/Logout operations because your application needs to remember if your user is logged in.


## Storage.kt
This file helps us to store data on the user's phone using Shared Preferences. 

To initialize/instantiate the Storage:

`var storage = Storage(activity)`

To store the data in Storage:

`var yourData = "This can be any type of data. Here we are using string"`

`storage.set("Any key you want to use", yourData)`


## Session.kt
Thsi file helps you manage user login and logout operation. You can modify the file to even store extra information.

Since session is an Object/Singleton, we only need to initialize. To intialize Session:

`Session.setup(this) // 'this' refers to the activity you are implementing`

NOTE: To be cautious, you need to initialize Session on every activity you are using

To login the user:

`Session.start("username")`

To logout the user:

`Session.finish()`

## Demo

In the directory above you will find an APK file (Session.apk). Install the demo application with that file. Otherwise, you may find the AndroidProject folder above which consist of the android project (Obviously. Haha, forgive me.) and build it.

This demo is just to show how Session works. In the end you will see that if you close the app , the user stays logged in and their information are not lost.

### Login Screen

To login, you just have to use the credentials at the bottom of the screen. No you can't change them. Once you logged in you are brought to the Home screen. You may try to close app and open it back to see if you are still logged in.

![Screenshot](/LoginScreen.jpeg)

### Home Screen

Here you can store you desired name. Once you stored the name, it will show up on the screen. Try closing the app and returning , the name will still be there and you will still be on the Home screen.

![Screenshot](/HomeScreen.jpeg)

![Screenshot](/StoringName.jpeg)

![Screenshot](/NameStored.jpeg)

## That's All
Hopefully this is helpful :)


