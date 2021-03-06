package de.florianmarsch.spring.fussballmanager

import de.florianmarsch.spring.fussballmanager.persistence.*
import de.florianmarsch.spring.fussballmanager.ranking.*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import kotlin.math.absoluteValue


@RestController
class RankingDataRestController {

	@Autowired
	lateinit var rankingRepo : RankingRepository



	@GetMapping("/api/ranking/{gameday}")
	fun  getRanking(@PathVariable(value="gameday") gameday:Int): Ranking {

		val theGameday = Gameday().apply {
			number = gameday
		}
		val findById = rankingRepo.findById(RankingId(theGameday))

		if(findById.isPresent){
			return findById.get()
		}else{
			return Ranking(RankingId(theGameday))
		}

	}


	@GetMapping("/api/ranking")
	fun  getRanking(): Any {

		val result = mutableMapOf<Trainer,RankedLineUp>()


		val findById = rankingRepo.findAll().flatMap {
			it?.rankedLineUps ?: emptyList()
		}.forEach{ranked ->
			val trainer = ranked.id?.trainer
			trainer?.let{
				result.putIfAbsent(trainer, RankedLineUp().apply {
					score = 0
					points = 0
				})
				result.get(trainer)?.let{
					it.apply{
						score = score + ranked.score
						points = points + ranked.points
					}
				}
			}
		}
		return result.keys.map {
			mapOf<String,Any?>(
					"trainer" to it ,
					"score" to	result.get(it)?.score,
					"points" to result.get(it)?.points
			)
		}


	}



}