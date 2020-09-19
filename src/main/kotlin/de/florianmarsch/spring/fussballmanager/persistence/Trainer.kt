package de.florianmarsch.spring.fussballmanager.persistence

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
class Trainer() :Serializable {


    @Column
    var name: String? = null

    @Id
    var number: Int? = null



}