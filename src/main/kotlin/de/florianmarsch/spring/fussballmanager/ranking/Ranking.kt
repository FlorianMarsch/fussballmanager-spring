package de.florianmarsch.spring.fussballmanager.ranking

import de.florianmarsch.spring.fussballmanager.persistence.Goal

class Ranking {

    var goals : List<Goal> = emptyList()
    var rankedLineUps:List<RankedLineUp> = emptyList()
    var gameday:Int = 0
}