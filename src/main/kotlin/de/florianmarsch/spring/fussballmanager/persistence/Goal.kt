package de.florianmarsch.spring.fussballmanager.persistence

import java.util.*
import javax.persistence.*

@Entity
class Goal() {

    @Id
    var id: String? = UUID.randomUUID().toString()

    @Column(nullable = false)
    var event: String? = null

    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    var player: Player? = null

    @Column(nullable = false)
    var gameday: Gameday? = null



}