# ProjectPortal_2_GuanhangShi

Guanhang Shi

I added some new features into the database, make them useful by reinstalling the app, else the app won't run properly.

Add updateProjectById() method and getFavouriteProject() to update whatever you want to update, and get all favourite projects,
it will return an arraylist. So once the switch component is changed in the project detail page, the db will update the isFavourite
variable after it's changed. Since it's hard to keep boolean value into the db, I used integer 1 means true and 0 means false.

Able to edit text message after click on the Edit button in every project detail fragment, still using updateProjectById() method.

Included ProjectMessageEditActivity and ProjectMessageEdit(Fragment) to implement edit function.

Still have some UI error in the main page, after the checkbox is clicked, the page would not shows properly.



