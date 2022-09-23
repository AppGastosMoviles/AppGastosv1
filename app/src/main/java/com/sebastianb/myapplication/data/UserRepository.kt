package com.sebastianb.myapplication.data

import android.util.Log
import com.google.firebase.FirebaseNetworkException

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sebastianb.myapplication.model.User
import kotlinx.coroutines.tasks.await


class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun registerUser(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            ResourceRemote.Succes(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            Log.e("Register", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("RegisterNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun loginUser(email: String, password: String): ResourceRemote<String?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            ResourceRemote.Succes(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            Log.e("Register", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("RegisterNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }


    suspend fun createUser(user: User): ResourceRemote<String?> {
        return try {
            user.uid?.let { db.collection("users").document(it).set(user).await() }
            ResourceRemote.Succes(data = user.uid)
        } catch (e: FirebaseFirestoreException) {
            Log.e("Register", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("RegisterNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }
    suspend fun loadUser(): ResourceRemote<QuerySnapshot?> {
        return try {
            val result=auth.uid?.let { db.collection("users").get().await() }
            ResourceRemote.Succes(data = result)
        } catch (e: FirebaseFirestoreException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

    suspend fun logOutUser(){
            val result=auth.signOut()
    }


}