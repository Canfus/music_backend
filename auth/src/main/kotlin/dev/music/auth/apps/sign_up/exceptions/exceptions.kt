package dev.music.auth.apps.sign_up.exceptions

import dev.music.auth.common.exceptions.ApplicationException

class EmailAlreadyExistsException : ApplicationException("Email already exists")