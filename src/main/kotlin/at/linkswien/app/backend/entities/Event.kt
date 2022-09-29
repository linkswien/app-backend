package at.linkswien.app.backend.entities

import java.time.Instant
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "event")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    val type: EventType,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "userId", referencedColumnName = "id")
    val user: User,
    val creationTs: Instant,
    val title: String,
    val headerImg: String,
    val description: String,
    val typeData: String
)