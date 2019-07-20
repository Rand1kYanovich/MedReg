package ru.appricot.medreg.util

class RandomUtil {


    companion object {
        fun getRandomNumber(): String {
            var number = ""
            for (i in 0..32) {
                number += (Math.random() * 10).toInt()
            }
            return number
        }
    }


}