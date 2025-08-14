package dev.music.auth.apps.user.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  @Column
  var username: String? = null,
  @Column(unique = true)
  var email: String,
  @Column
  var password: String,
)