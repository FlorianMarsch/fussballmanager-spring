package de.florianmarsch.spring.fussballmanager.ranking

import de.florianmarsch.spring.fussballmanager.persistence.Gameday
import de.florianmarsch.spring.fussballmanager.persistence.Goal
import de.florianmarsch.spring.fussballmanager.persistence.Player
import de.florianmarsch.spring.fussballmanager.persistence.Trainer
import java.io.Serializable
import javax.persistence.*


@Embeddable
data class RankedLineUpId(
        @ManyToOne(optional = false, cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
        var gameday : Gameday? = null,

        @ManyToOne(optional = false, cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
                                   var trainer: Trainer? = null) : Serializable

@Entity
data class RankedLineUp(@Id var id:RankedLineUpId? = null) {



    @Column
        var score : Int = 0

    @Column
        var points : Int = 0


    }