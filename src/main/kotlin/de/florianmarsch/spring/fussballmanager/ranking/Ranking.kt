package de.florianmarsch.spring.fussballmanager.ranking

import de.florianmarsch.spring.fussballmanager.persistence.Goal

class Ranking {

    var totalNumberOfGoals : Int = 0
    var rankedLineUps:List<RankedLineUp> = emptyList()
    var gameday:Int = 0
}