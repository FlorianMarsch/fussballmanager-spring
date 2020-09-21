package de.florianmarsch.spring.fussballmanager.ranking

import de.florianmarsch.spring.fussballmanager.persistence.*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*


@RestController
class RankingService {

	@Autowired
	lateinit var goalRepo : GoalRepository

	@Autowired
	lateinit var lineUpRepo : LineUpRepository


	fun createRanking(gameday: Gameday): Ranking {
		val goals: List<Goal> = goalRepo.findByGameday(gameday)?.filterNotNull().orEmpty()

		val lineups: List<LineUp> = lineUpRepo.findById_Gameday(gameday)?.filterNotNull().orEmpty()

		val players: List<Player> = lineups.flatMap {
			it.players
		}

		val relevantEvents = goals.filter {
			players.contains(it.player)
		}

		val pointsPerScore: Float = if (relevantEvents.isEmpty()) {
			0f
		} else {
			100f / relevantEvents.size
		}

		val rankedLineUps: List<RankedLineUp> = lineups.map {
			val currentLinup = it
			RankedLineUp(
					RankedLineUpId(
							gameday, it.id?.trainer)
			).apply {


				score = goals.filter {
					currentLinup.players.contains(it.player)
				}.map {
					if (it.event == "Goal") {
						1
					} else {
						-1
					}
				}.sum().coerceAtLeast(0)
				points = (pointsPerScore * score).toInt()
			}
		}.sortedByDescending {
			it.score
		}

		return Ranking().apply {
			this.goals = relevantEvents
			this.rankedLineUps = rankedLineUps
			this.gameday = gameday
		}
	}


}