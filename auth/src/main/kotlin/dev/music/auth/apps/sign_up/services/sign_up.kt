package dev.music.auth.apps.sign_up.services

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import dev.music.auth.apps.user.model.entity.User
import dev.music.auth.apps.user.infra.repository.UserRepository
import dev.music.auth.apps.sign_up.exceptions.EmailAlreadyExistsException

interface SignUpService {
  fun signUp(email: String, username: String?, rawPassword: String): User
}

@Service
class SignUpServiceImpl(
  private val usersRepository: UserRepository,
  private val passwordEncoder: BCryptPasswordEncoder
) : SignUpService {

  @Transactional
  override fun signUp(email: String, username: String?, rawPassword: String): User {
    if (usersRepository.existsUserByEmail(email)) {
      throw EmailAlreadyExistsException()
    }

    val user = User(
      email = email,
      username = username,
      password = passwordEncoder.encode(rawPassword)
    )

    return usersRepository.save(user)
  }
}