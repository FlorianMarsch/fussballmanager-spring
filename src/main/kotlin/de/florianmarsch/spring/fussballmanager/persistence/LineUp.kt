package de.florianmarsch.spring.fussballmanager.persistence

import java.io.Serializable
import java.util.*
import javax.persistence.*


@Entity
data class Player(@Id var name:String?=null){

}

@Embeddable
data class LineUpId(    @Column(nullable = false)
                        var trainer:Trainer? = null,

                        @Column(nullable = false)
var gameday:Gameday? = null) : Serializable {



}

@Entity
data class LineUp(
        @EmbeddedId
        var id: LineUpId? = null) {


    @OneToMany(cascade = [CascadeType.ALL],
            orphanRemoval = true)
    var players : List<Player> = mutableListOf()



}