# ProjectPortal_2_GuanhangShi

Guanhang Shi

Implemented a DatabaseAsyncTask inner private class in the ProjectListFragment to list all the project instead of doing so in the onCreate() function.

Implemented DatabaseFavouriteUpdateAsyncTask private inner class in the ProjectDetailFragment to update the isFavourite, the instance of the class is executed after the switch component is changed.

Removed the ProjectMessageEdit fragment, the message is updated in the activity. At first I create an inner class of the intentService in the fragment and try to call it after the submit button is clicked, and I did acitivate the service in the AndroidManifest.xml, but it went an Cannot Instantiate Service Error, so I thought intentService could not be called in a fragment. I removed the fragment and carried everything into the activity, but the inner class intentService could still not able to be instantiate, so I created an individual class of IntentService, and use putExtra() to communicate with it, and it worked.





