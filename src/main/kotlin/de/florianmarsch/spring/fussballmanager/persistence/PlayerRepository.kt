package de.florianmarsch.spring.fussballmanager.persistence

import de.florianmarsch.spring.fussballmanager.persistence.Player
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : PagingAndSortingRepository<Player?, String?>{

}