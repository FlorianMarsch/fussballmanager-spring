package de.florianmarsch.spring.fussballmanager.persistence

import de.florianmarsch.spring.fussballmanager.persistence.Goal
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
interface GoalRepository : PagingAndSortingRepository<Goal?, String?>{

    fun findByGameday(gameday: Gameday): List<Goal> {
        return findAll()?.filterNotNull().orEmpty().filter {
            it.gameday?.number == gameday.number
        }
    }

    fun findByMatch(match: Int): List<Goal>

}