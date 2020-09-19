package de.florianmarsch.spring.fussballmanager.persistence

import de.florianmarsch.spring.fussballmanager.persistence.Goal
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
interface TrainerRepository : PagingAndSortingRepository<Trainer?, Int?>{

}