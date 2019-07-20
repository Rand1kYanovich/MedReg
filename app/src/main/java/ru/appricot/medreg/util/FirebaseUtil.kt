package ru.appricot.medreg.util

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseUtil {

companion object {
    private fun getDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference

    var phoneNumber = ""

    fun setPhoneNumber() {
        getDatabase().child(phoneNumber).child("phoneNumber").setValue(phoneNumber)
    }

    fun setName(name: String,family:String,patronymic:String) {
        getDatabase().child(phoneNumber).child("name").setValue("$name $family $patronymic")
    }

    fun setPassword(password: String) {
        getDatabase().child(phoneNumber).child("password").setValue(password)

    }
}
}