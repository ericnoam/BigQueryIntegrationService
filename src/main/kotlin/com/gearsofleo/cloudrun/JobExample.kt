package com.gearsofleo.cloudrun

internal object JobExample {

    @Throws(InterruptedException::class)
    fun runTask(sleepTime: Int, failureRate: Float, taskIndex: String) {
        // Simulate work
        if (sleepTime > 0) {
            Thread.sleep(sleepTime.toLong())
        }

        // Simulate errors
        if (failureRate < 0 || failureRate > 1) {
            System.err.println("Invalid FAIL_RATE value: $failureRate. Must be a float between 0 and 1 inclusive.")
            return
        }
        if (Math.random() < failureRate) {
            throw RuntimeException("Task Failed.")
        }
        println("Completed Task #$taskIndex")
    }
}