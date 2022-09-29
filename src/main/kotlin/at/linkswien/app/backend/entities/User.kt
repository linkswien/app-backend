package at.linkswien.app.backend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(unique = true)
    @JsonIgnore
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,

   @ManyToMany
   @JoinTable(
      name = "userRole",
      joinColumns = [JoinColumn(name = "userId")],
      inverseJoinColumns = [JoinColumn(name = "roleId")])
   @JsonIgnore
   val roles: List<Role>,

   @ManyToMany
   @JoinTable(
      name = "subscription",
      joinColumns = [JoinColumn(name = "userId")],
      inverseJoinColumns = [JoinColumn(name = "eventId")])
   @JsonIgnore
   val eventSubscriptions: List<Event>
)

