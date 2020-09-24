package de.florianmarsch.spring.fussballmanager.persistence

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
data class Trainer(
        @Id
        var number: Int? = null
) :Serializable {


    @Column
    var name: String? = null





}