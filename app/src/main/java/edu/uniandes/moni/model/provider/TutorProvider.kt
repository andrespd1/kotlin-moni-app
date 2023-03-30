package edu.uniandes.moni.model.provider

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uniandes.moni.model.TutorModel
import edu.uniandes.moni.model.TutoringModel

class TutorProvider {
    val firestore = Firebase.firestore
    val tutores = mutableListOf<TutorModel>()
    fun retriveTutores() {
        firestore.collection("tutors")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var tutorModel =
                        TutorModel(
                            document.data?.get("nombre").toString(),
                            document.data?.get("email").toString(),
                            document.data?.get("rating") as Float,
                            document.data?.get("topic") as TutoringModel
                        )
                    tutores.add(tutorModel)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}