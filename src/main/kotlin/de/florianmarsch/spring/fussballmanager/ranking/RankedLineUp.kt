package de.florianmarsch.spring.fussballmanager.ranking

import de.florianmarsch.spring.fussballmanager.persistence.Gameday
import de.florianmarsch.spring.fussballmanager.persistence.Goal
import de.florianmarsch.spring.fussballmanager.persistence.Player
import de.florianmarsch.spring.fussballmanager.persistence.Trainer
import java.io.Serializable
import javax.persistence.*

class RankedPlayer {
    var name : String?=null
    var events : List<Goal> = emptyList()
}

class RankedLineUp {




        var trainer: Trainer? = null

        var score : Int = 0

        var points : Int = 0


    }