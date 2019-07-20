package ru.appricot.medreg.util

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseUtil {

    companion object {
        private fun getDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference


        fun setPhoneNumber() {
            getDatabase().child("Users").child(SharedUtil.getUser()).child("phoneNumber").setValue(SharedUtil.getUser())
        }

        fun setName(name: String, family: String, patronymic: String) {
            getDatabase().child("Users").child(SharedUtil.getUser()).child("name").setValue("$name $family $patronymic")
        }

        fun setPassword(password: String) {
            getDatabase().child("Users").child(SharedUtil.getUser()).child("password").setValue(password)

        }
    }
}