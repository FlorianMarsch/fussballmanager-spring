package de.florianmarsch.spring.fussballmanager.ranking

import de.florianmarsch.spring.fussballmanager.persistence.Gameday
import de.florianmarsch.spring.fussballmanager.persistence.Goal
import javax.persistence.*

@Entity
data class Ranking (@Id var gameday: Gameday? = null){

    @OneToMany(cascade = [CascadeType.ALL])
    var goals : List<Goal> = mutableListOf()

    @OneToMany(cascade = [CascadeType.ALL])
    var rankedLineUps:List<RankedLineUp> = mutableListOf()

}