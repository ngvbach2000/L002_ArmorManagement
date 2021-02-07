# Armor Management 
Lab 1: Armor Management 
(Java Desktop)

## Program Specifications:
In this assignment, you are required to build an armor management application, in the form of a desktop
application. The program has basic functions: login, add-delete-edit armor and armor’s information. You are
required to use the basic components to design interfaces, use the tabbed pane to organize armor management
on the one screen. File is used to store information.
Program organization must clearly separate functions according to MVC model.

## Features:
1. Create Armor
 - When a user fills all information and click Create, the application starts accepting all the details
of the new Armor and store it into a FILE.
 - The current time is automatically filled to timeOfCreate and not displayed at Create Armor page
 - The program checks the validity of data, if data is not valid then display an error message
 - The armor table must be refreshed after new data has been successfully inserted.

2.  Show All Armors
 - The screen is divided into 2 parts: Main information and Detailed information.
 - Main part: this part lists all available Armor with their information (ID, Classification,
TimeOfCreate, Defense) in the system
 - Detail part: When you click a row on the table, system call findByArmorID method (50 LOC), If
the application finds a match ArmorID. All information of Armor will be shown. The details of the
respective armor are displayed with information as ArmorID(disabled), Classification,
TimeOfCreate, Defense, Description, Status.
 - 04 buttons Get All, Create, Update and Remove button are added to detail part.
 
3.  Edit Armor
  - The user clicks on the Armor that he/she wants to modify on the Armor table.
  - The details of the respective armor are displayed.
  - The user changes the information of the Armor (not allow modify the EmpID). Then user clicks
the Update button.
  - The program checks the validity of data, if data is not valid then display an error message,
otherwise system will update Armor information.
  - The Armor table must be refreshed after new data had been successfully updated.
  
4. Remove Armor
 - The user clicks on the Armor he wants to delete on the Armor table. Then users click the Remove
button.
 - The program must display a message to confirm the deletion. If the user confirms, system will
delete the selected Armor.
 - The Armor table must be refreshed after data has been successfully deleted
 
*. Verify constraint of following data type fields
 - ArmorId: max length is 10, not contains special characters (@, #, $)
 - Classification: max length is 30
 - Description: max length is 300
 - TimeOfCreate: The current time is automatically filled
 - Defense > 0
 
## Connect me via 
1. [Facebook](https://fb.me/ngvbach2000)
2. [Email](mailto:ngvbach2000@gmail.com)

#### © 2020 by @ngvbach2000:cow:
