package ru.appricot.medreg.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class CouponViewModel:ViewModel() {

    var doctors = MutableLiveData<ArrayList<String>>()
    var directions = MutableLiveData<ArrayList<String>>()


    fun getDirectionArray():LiveData<ArrayList<String>>{
        FirebaseDatabase.getInstance().reference.child("Doctors").addChildEventListener(object :ChildEventListener{
            var arrayDirection = ArrayList<String>()
            override fun onCancelled(p0: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {

                if (dataSnapshot.exists()){
                    arrayDirection.add(dataSnapshot.key.toString())
                    directions.value = arrayDirection
                }

            }

            override fun onChildRemoved(p0: DataSnapshot) {}
        })
        return directions
    }

    fun getDoctorsArray(direction: String):LiveData<ArrayList<String>>{
        FirebaseDatabase.getInstance().reference.child("Doctors").child(direction).addChildEventListener(object:ChildEventListener{
            var arrayDoctor = ArrayList<String>()

            override fun onCancelled(p0: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                if (dataSnapshot.exists()){
                    arrayDoctor.add(dataSnapshot.key.toString())
                    doctors.value = arrayDoctor
                }
            }


            override fun onChildRemoved(p0: DataSnapshot) {}

        })
        return doctors
    }
}