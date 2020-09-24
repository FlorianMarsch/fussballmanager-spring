package de.florianmarsch.spring.fussballmanager.persistence

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
data class Gameday(
        @Id
        var number: Int? = null
) :Serializable{






}