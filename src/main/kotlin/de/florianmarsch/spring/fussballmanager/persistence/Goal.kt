package de.florianmarsch.spring.fussballmanager.persistence

import java.util.*
import javax.persistence.*

@Entity
data class Goal(
        @Id
        var id: String? = UUID.randomUUID().toString()
) {



    @Column(nullable = false)
    var event: String? = null

    @ManyToOne(optional = false, cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    var player: Player? = null

    @ManyToOne(optional = false, cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    var gameday: Gameday? = null

    @Column(nullable = false)
    var match: Int? = null

}