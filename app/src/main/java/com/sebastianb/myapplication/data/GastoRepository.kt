package com.sebastianb.myapplication.data

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sebastianb.myapplication.model.Gasto
import kotlinx.coroutines.tasks.await

class GastoRepository {
    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore
    suspend fun createGasto(gasto: Gasto): ResourceRemote<String?> {
        return try {
            val path = auth.uid?.let { db.collection("users").document(it).collection("gastos") }
            val documentGasto = path?.document()
            gasto.id = documentGasto?.id
            documentGasto?.id?.let { path.document(it).set(gasto).await() }

            ResourceRemote.Succes(data = documentGasto?.id)

        } catch (e: FirebaseFirestoreException) {
            Log.e("createGasto", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            Log.e("FirebaseNetwork", e.localizedMessage)
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

    suspend fun recentGasto(): ResourceRemote<QuerySnapshot?> {
        return try {
            val docRef = auth.uid?.let { db.collection("users").document(it).collection("gastos") }
            val result = docRef?.get()?.await()
            ResourceRemote.Succes(data = result)
        } catch (e: FirebaseFirestoreException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

    suspend fun deleteGasto(gasto: Gasto):ResourceRemote<Boolean> {
        return try {
            val docRef = auth.uid?.let{ db.collection("users").document(it).collection("gastos") }
            gasto.id?.let { docRef?.document(it)?.delete() }
            ResourceRemote.Succes(data=true)
        } catch (e: FirebaseFirestoreException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }




}