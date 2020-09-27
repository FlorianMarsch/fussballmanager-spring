package de.florianmarsch.spring.fussballmanager.ranking

import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import de.florianmarsch.spring.fussballmanager.persistence.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager


@RestController
class RankingService {

	@Autowired
	lateinit var goalRepo : GoalRepository

	@Autowired
	lateinit var lineUpRepo : LineUpRepository

	@Autowired
	lateinit var rankingRepo : RankingRepository

	@Autowired
	lateinit var entityManager : EntityManager


	fun remove(goals : List<Goal>){
		rankingRepo.findAll().filterNotNull().forEach {


			val current: MutableList<Goal> = it.goals.toMutableList()
			if(current.removeAll(goals)){
				it.goals = current
				rankingRepo.save(it)
			}
		}
	}

	@GetMapping("/api/ranking")
	fun test(): Any {

		var query = JPAQuery<RankedLineUp>(entityManager)

		return query
				.select(
						QRankedLineUp.rankedLineUp.id.trainer.number,
						QRankedLineUp.rankedLineUp.points.sum(),
						QRankedLineUp.rankedLineUp.score.sum()
				)
				.from(QRankedLineUp.rankedLineUp)
				.groupBy(QRankedLineUp.rankedLineUp.id.trainer.number)
				.fetchResults()
				.results.map {
					mapOf<String,Int?>(
							"number" to it.get(QRankedLineUp.rankedLineUp.id.trainer.number) ,
							"points" to 		it.get(QRankedLineUp.rankedLineUp.points.sum())  ,
							"score" to		it.get(QRankedLineUp.rankedLineUp.score.sum())
					)

				}



	}


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
			this.id = RankingId(gameday)
		}
	}


}