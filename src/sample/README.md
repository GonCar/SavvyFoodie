README
===========================
Name : Savvy Foodie
===========================

Description
===========================
The application is a java based client application designed with javafx to create a rich user interface environment.
It can be referred as a shipping or e-commence application intending to sell food products.
The application can be used by any user who has Vegetables or Fruits to sell, or on the other hand a buyer looking to buy these items.

The first version ( v1.0.1 ) is more like an information sharing platform between buyers and sellers,
but more functionalities can be added on later coming versions where buying and selling happens on the app.

Installation
===========================
These instructions have been made for the windows. Linux and Mac commands may differ.

We assume that the user has access to and has installed, Java, an IDE.

To run application, the following libraries shall be added:
1. JavaFx
2. JDBC

To run the tests, the following libraries should be added:
1. byte-buddy-agent-1.10.15
2. byte-buddy-1.10.15
3. junit-jupiter-api-5.7.0
4. mockito-core.3.515
5. objenesis-3.1

Usage
===========================
In order to run the application, you need to run main class on the IDE.

Then the javafx set of graphics and media packages will take care of the GUI components popping up a new window.


Documentation
===========================
A report is included which specifies all the development processes and some graphic representation of the application.


Construction of the application
===========================
Here is some explanation for the application's classes.

*main*
Starts the application.

*app_Logic*
Contains the interaction of objects or as a crossroads of storing values in static variables to be used by the Entire application.It is used as a singleton class on some cases.
More Logic can be developed in the future in this class. 

*DB_connection*
It creates connection and all functionalities relating with the database.

*Controller*
Deals with controlling of the main store view with a list of products ie table.fxml.

*SignUpController*
a controller dealing with the signUp.fxml.

*loginController*
a controller dealing with the login.fxml.

*filterOptions*
a controller dealing with the filterOptionsController.fxml.

*AddproductController*
a controller dealing with the addProduct.fxml.

*User*
a class for the use of creating user object.

*Products*
a class for the use of creating product objects.

*style.css*
a css file for the stiling of GUI.

Authors
===========================
The application was created by agile methodology.
Team members have practiced DevOps, so the roles was changeable.
But for the most part, here are the authors and theirs respective roles.

Gonzalo Carrero    --  Developer 
Meron Habtemichael --  Developer
Xin Wang Hansen    --  Test
Geng Yuan          --  Test
Moussa Diaby       --  Support

License
===========================
This is stated in the LICENSE.md