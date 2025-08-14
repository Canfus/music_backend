package dev.music.auth.apps.user.infra.repository

import java.util.Optional
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.JpaRepository

import dev.music.auth.apps.user.model.entity.User

@Repository
interface UserRepository : JpaRepository<User, Long> {

  @Query("""
    select u from User u where u.id = :id
  """)
  fun findUserById(id: Long): Optional<User>

  @Query("""
    select exists (
      select u from User u where u.email = :email
    )
  """)
  fun existsUserByEmail(email: String): Boolean
}