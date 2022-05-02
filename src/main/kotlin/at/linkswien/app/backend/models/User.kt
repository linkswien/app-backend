package at.linkswien.app.backend.models

import jdk.jshell.JShell.Subscription
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

    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,

   @ManyToMany
   @JoinTable(
      name = "userRoles",
      joinColumns = [JoinColumn(name = "userId")],
      inverseJoinColumns = [JoinColumn(name = "roleId")])
   val roles: List<Role>,

   @ManyToMany
   @JoinTable(
      name = "subscriptions",
      joinColumns = [JoinColumn(name = "userId")],
      inverseJoinColumns = [JoinColumn(name = "eventId")])
   val eventSubscriptions: List<Event>
)

