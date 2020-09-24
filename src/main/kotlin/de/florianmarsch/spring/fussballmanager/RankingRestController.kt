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



}