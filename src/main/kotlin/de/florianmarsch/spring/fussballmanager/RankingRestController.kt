package de.florianmarsch.spring.fussballmanager

import de.florianmarsch.spring.fussballmanager.persistence.*
import de.florianmarsch.spring.fussballmanager.ranking.RankedLineUp
import de.florianmarsch.spring.fussballmanager.ranking.Ranking
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*


@RestController
class RankingDataRestController {

	@Value("\${community}")
	private val community: Int? = null

	@Autowired
	lateinit var goalRepo : GoalRepository

	@Autowired
	lateinit var lineUpRepo : LineUpRepository

	@GetMapping("/api/ranking/{gameday}")
	fun  getRanking(@PathVariable(value="gameday") gameday:Int) : Any {

		val goals: List<Goal> = goalRepo.findAll()?.filterNotNull().orEmpty().filter {
			it.gameday?.number == gameday
		}

		val lineups: List<LineUp> = lineUpRepo.findAll()?.filterNotNull().orEmpty().filter {
			it.id?.gameday?.number == gameday
		}
		val rankedLineUps:List<RankedLineUp> = lineups.map {
			val currentLinup = it
			RankedLineUp().apply {
				trainer = it.id?.trainer
				score = goals.filter {
					currentLinup.players.contains(it.player)
				}.map {
					if(it.event == "Goal"){
						1
					}else{
						-1
					}
				}.sum()
			}
		}.sortedBy {
			it.score
		}

		return Ranking().apply{
			this.totalNumberOfGoals = goals.size
			this.rankedLineUps = rankedLineUps
			this.gameday = gameday
		}
	}


}