package com.joshhn.trelloclone.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.joshhn.trelloclone.activities.MainActivity
import com.joshhn.trelloclone.activities.MyProfileActivity
import com.joshhn.trelloclone.activities.SignInActivity
import com.joshhn.trelloclone.activities.SignUpActivity
import com.joshhn.trelloclone.models.User
import com.joshhn.trelloclone.utils.Constants

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User) {

        mFireStore.collection(Constants.USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(getCurrentUserID())
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // Here call a function of base activity for transferring the result to it.
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error writing document",
                    e
                )
            }
    }

    fun loadUserData(activity: Activity){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener {document ->
                val loggedInUser = document.toObject(User::class.java)

                when(activity){
                    is SignInActivity -> {
                        if(loggedInUser != null){
                            activity.signInSuccess(loggedInUser)
                        }
                    }

                    is MainActivity -> {
                        if(loggedInUser != null){
                            activity.updateNavigationUserDetails(loggedInUser)
                        }
                    }
                    is MyProfileActivity -> {
                        if (loggedInUser != null) {
                            activity.setUserDataInUI(loggedInUser)
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                when(activity) {
                    is SignInActivity -> {
                        activity.hideProgressDialog()
                    }

                    is MainActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,
                    "Error writing document",
                    e
                )
            }
    }



     fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }
}