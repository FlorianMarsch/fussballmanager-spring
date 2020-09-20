package de.florianmarsch.spring.fussballmanager.persistence

import de.florianmarsch.spring.fussballmanager.persistence.LineUp
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
interface LineUpRepository : PagingAndSortingRepository<LineUp?, LineUpId?>{

    @EntityGraph(attributePaths = ["players","id.trainer"])
    fun findById_Gameday(gameday: Gameday): List<LineUp>

}