package de.florianmarsch.spring.fussballmanager

import de.florianmarsch.spring.fussballmanager.persistence.*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*


@RestController
class ReferenceDataRestController {

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

	@GetMapping("/api/community")
	fun  getCommunity() : Map<String, Int?> {
		return mapOf("number" to community)
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
		val findByMatch = goalRepo.findByMatch(match)?.filterNotNull()
		findByMatch?.let {
			val deleted = it.toMutableList().apply {
				removeAll(goals)
			}
			goalRepo.deleteAll(deleted)
		}
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
		 lineUpRepo.save(lineUp)
	}
}