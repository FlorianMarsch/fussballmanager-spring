package de.florianmarsch.spring.fussballmanager.persistence

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
class Gameday() :Serializable{


    @Id
    var number: Int? = null



}