package ru.appricot.medreg.model.entity

import java.io.Serializable

class Coupon : Serializable {

    var couponNumber = ""
    var direction = ""
    var time = ""
    var doctor = ""
    var date = ""
    var isOpen = false
}