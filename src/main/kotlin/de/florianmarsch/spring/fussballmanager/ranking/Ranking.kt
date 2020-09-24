package de.florianmarsch.spring.fussballmanager.ranking

import de.florianmarsch.spring.fussballmanager.persistence.Gameday
import de.florianmarsch.spring.fussballmanager.persistence.Goal
import java.io.Serializable
import javax.persistence.*


@Embeddable
data class RankingId (

        @ManyToOne(optional = false, cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
        var gameday: Gameday? = null):Serializable{}

@Entity
data class Ranking (


        @EmbeddedId var id: RankingId? = null){

    @OneToMany(cascade = [CascadeType.ALL])
    var goals : List<Goal> = mutableListOf()

    @OneToMany(cascade = [CascadeType.ALL])
    var rankedLineUps:List<RankedLineUp> = mutableListOf()

}