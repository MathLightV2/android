package com.example.myapplication

import java.io.Serializable
import java.time.LocalDate

class Memo(
    var texte: String,
    var date: LocalDate
) : Serializable {

}
