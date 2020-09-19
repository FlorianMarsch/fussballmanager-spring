package de.florianmarsch.spring.fussballmanager.persistence

import java.io.Serializable
import java.util.*
import javax.persistence.*


@Entity
class Player(){
    @Id var name:String?=null
}

@Embeddable
class LineUpId() : Serializable {

    @Column(nullable = false)
    var trainer:Trainer? = null

    @Column(nullable = false)
    var gameday:Gameday? = null

}

@Entity
class LineUp() {

    @EmbeddedId
    var id: LineUpId? = null

    @OneToMany(cascade = [CascadeType.ALL])
    var players : List<Player> = mutableListOf()



}