package com.test.movie.core.location

import android.net.Uri
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.test.movie.ui.utils.getDate

class FirebaseRepository {
    companion object {
        const val PLACES = "places"
        const val IMAGES = "images"
        const val CHILD_IMAGE = "Imagenes"
    }

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val storage by lazy { FirebaseStorage.getInstance() }

    fun saveLocation(locationUser: UserLocation, response: (result: Boolean) -> Unit) {

        try {
            firestore.apply{

                collection(PLACES).document()
                    .set(locationUser)
                    .addOnSuccessListener {
                        response(true)
                    }
                    .addOnFailureListener {
                        response(false)
                    }.addOnCanceledListener {
                        response(false)
                    }
            }
        } catch (e: Exception) {
            response(false)
        }
    }

    fun getLocation(response: (result: UserLocation) -> Unit) {

        firestore.apply{
            val locationsUserRef = collection(PLACES)

            locationsUserRef.addSnapshotListener { snapshots, error ->
                if (error == null) {
                    for (snapshot in snapshots!!.documentChanges) {
                        val location = snapshot.document.toObject(UserLocation::class.java)
                        when (snapshot.type) {
                            DocumentChange.Type.ADDED,
                            DocumentChange.Type.MODIFIED,
                            DocumentChange.Type.REMOVED -> {
                                response(location)
                            }
                            else -> {

                            }
                        }
                    }
                }
            }
        }
    }

    fun uploadImage(image: Uri, isSuccess: (Boolean) -> Unit) {
        val fileName = getDate()
        val file = storage.reference.child(CHILD_IMAGE).child(fileName)
        file.putFile(image).addOnSuccessListener { snapshot ->
            snapshot.storage.downloadUrl.addOnSuccessListener {
                savePathImage(fileName, it.toString(), isSuccess)
            }
        }
    }

    private fun savePathImage(name: String, url: String, isSuccess: (Boolean) -> Unit){
        firestore.collection(IMAGES)
            .document()
            .set(ImageName(name, url))
            .addOnCompleteListener {
                isSuccess(it.isSuccessful)
            }
    }

    fun getImages(response: (list: List<DocumentSnapshot>) -> Unit) {
        firestore.collection(IMAGES).addSnapshotListener { snapshots, error ->
            if(error == null) {
                snapshots?.documents?.let { response(it) }
            }
        }
    }
}