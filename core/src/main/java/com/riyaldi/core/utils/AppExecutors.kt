package com.riyaldi.core.utils

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class AppExecutors @VisibleForTesting constructor(
        private val diskIO: Executor
) {
    companion object;

    @Inject
    constructor() : this(
            Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

}