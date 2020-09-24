package de.florianmarsch.spring.fussballmanager.persistence

import java.io.Serializable
import java.util.*
import javax.persistence.*


@Entity
data class Player(@Id var name:String?=null){

}

@Embeddable
data class LineUpId(
        @ManyToOne(optional = false, cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
                        var trainer:Trainer? = null,

        @ManyToOne(optional = false, cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
var gameday:Gameday? = null) : Serializable {



}

@Entity
data class LineUp(
        @EmbeddedId
        var id: LineUpId? = null) {


    @ManyToMany( cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var players : List<Player> = mutableListOf()



}