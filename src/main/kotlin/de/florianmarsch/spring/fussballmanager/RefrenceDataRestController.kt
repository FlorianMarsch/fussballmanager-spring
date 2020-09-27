package de.florianmarsch.spring.fussballmanager

import de.florianmarsch.spring.fussballmanager.persistence.*
import de.florianmarsch.spring.fussballmanager.ranking.RankingRepository
import de.florianmarsch.spring.fussballmanager.ranking.RankingService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.xml.bind.JAXBElement


@RestController
class ReferenceDataRestController {

	var logger: Logger = LoggerFactory.getLogger(ReferenceDataRestController::class.java)

	@Value("\${community}")
	private val community: Int? = null

	@Autowired
	lateinit var goalRepo : GoalRepository

	@Autowired
	lateinit var playerRepo : PlayerRepository

	@Autowired
	lateinit var lineUpRepo : LineUpRepository

	@Autowired
	lateinit var trainerRepo : TrainerRepository

	@Autowired
	lateinit var gamedayRepo : GamedayRepository

	@Autowired
	lateinit var rankingRepository: RankingRepository

	@Autowired
	lateinit var rankingService: RankingService

	@GetMapping("/api/community")
	fun  getCommunity() : Map<String, Int?> {
		return mapOf("number" to community)
	}

	@GetMapping("/api/gamedays")
	fun  getGamedays() : List<Gameday> {
		return gamedayRepo.findAll().filterNotNull()
	}

	@PostMapping("/api/gameday")
	fun  saveGameday(@RequestBody gameday:Gameday) {
		gamedayRepo.save(gameday)
		GlobalScope.async {
			val createRanking = rankingService.createRanking(gameday)
			rankingRepository.save(createRanking)
		}
	}

	@GetMapping("/api/players")
	fun  getPlayers() : List<Player> {
		return playerRepo.findAll().filterNotNull()
	}

	@GetMapping("/api/goals")
	fun  getGoals() : List<Goal> {
		return goalRepo.findAll().filterNotNull()
	}

	@GetMapping("/api/goals/{gameday}")
	fun  getGoalsByGameday(@PathVariable(value="gameday")  gameday:Int) : List<Goal>? {
		return goalRepo.findByGameday(Gameday().apply {
			number = gameday
		})?.filterNotNull()
	}

	@PostMapping("/api/goals")
	fun  saveGoal(@RequestBody goal:Goal) {
		goalRepo.save(goal)
	}

	@PostMapping("/api/goals/{match}")
	fun  saveGoals(@RequestBody goals:List<Goal>, @PathVariable(value="match")  match:Int) {
		logger.debug("Save ${goals.size} Goals for ${match}.")
		val findByMatch = goalRepo.findByMatch(match)?.filterNotNull()
		logger.debug("Already has ${findByMatch.size} Goals for ${match}.")
		findByMatch?.let {
			val deleted = it.filter {
				goals.contains(it).not()
			}

			if(deleted.isEmpty().not()){
				logger.debug("Need to delete ${deleted.size} Goals for ${match}.")
				rankingService.remove(deleted)
				goalRepo.deleteAll(deleted)
			}



		}
		playerRepo.saveAll(goals.map {
			it.player
		})
		goalRepo.saveAll(goals)
	}

	@GetMapping("/api/trainers")
	fun getTrainer() : List<Trainer>  {
		return trainerRepo.findAll().filterNotNull()
	}

	@PostMapping("/api/trainers")
	fun saveTrainer(@RequestBody trainer: Trainer)   {
		trainerRepo.save(trainer)
	}

	@PostMapping("/api/lineUp")
	fun saveLineUp(@RequestBody lineUp: LineUp) {


		val findById: Optional<LineUp?> = lineUpRepo.findById(lineUp.id!!)
		if(findById.isPresent){

			val currentLineUp: LineUp = findById.get()


			val deleted = currentLineUp.players.filter {
				lineUp.players.contains(it).not()
			}
			val new = lineUp.players.filter {
				currentLineUp.players.contains(it).not()
			}
			currentLineUp.players = currentLineUp.players.toMutableList().apply {
				removeAll(deleted)
				addAll(new)
			}
			if(deleted.isEmpty().not() or new.isEmpty().not()){
				lineUpRepo.save(currentLineUp)
			}

		}else{
			lineUpRepo.save(lineUp)
		}


	}
}