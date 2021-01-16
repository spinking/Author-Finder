package studio.eyesthetics.authorfinder.data.network.errors

import java.lang.IllegalArgumentException

class ContentTypeError(override val message: String) : IllegalArgumentException(message)