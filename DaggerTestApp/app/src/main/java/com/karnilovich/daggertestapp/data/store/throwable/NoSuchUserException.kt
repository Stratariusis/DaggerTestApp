package com.karnilovich.daggertestapp.data.store.throwable

class NoSuchUserException(override val message: String?, override val cause: Throwable?) : Throwable(message, cause) {

    constructor(message: String?) : this(message, null)

    constructor(cause: Throwable?) : this(cause?.toString(), cause)

    constructor() : this(null, null)

}