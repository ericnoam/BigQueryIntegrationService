package com.gearsofleo.cloudrun

fun main(args: Array<String>) {
    // These values are provided automatically by the Cloud Run Jobs runtime.
    val CLOUD_RUN_TASK_INDEX = System.getenv().getOrDefault("CLOUD_RUN_TASK_INDEX", "0")
    val CLOUD_RUN_TASK_ATTEMPT = System.getenv().getOrDefault("CLOUD_RUN_TASK_ATTEMPT", "0")

    // User-provided environment variables
    val SLEEP_MS = System.getenv().getOrDefault("SLEEP_MS", "0").toInt()
    val FAIL_RATE = System.getenv().getOrDefault("FAIL_RATE", "0.0").toFloat()
    println( "Starting Task #$CLOUD_RUN_TASK_INDEX, Attempt #$CLOUD_RUN_TASK_ATTEMPT" )

    try {
      JobExample.runTask(SLEEP_MS, FAIL_RATE, CLOUD_RUN_TASK_INDEX)
    } catch (e: RuntimeException) {
        System.err.println( "Task #$CLOUD_RUN_TASK_INDEX, Attempt #$CLOUD_RUN_TASK_ATTEMPT failed.")
        // Catch error and denote process-level failure to retry Task
        System.exit(1)
    } catch (e: InterruptedException) {
        System.err.println("Task #$CLOUD_RUN_TASK_INDEX, Attempt #$CLOUD_RUN_TASK_ATTEMPT failed.")
        System.exit(1)
    }
}


