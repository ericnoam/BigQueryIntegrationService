package com.gearsofleo.cloudrun

fun main(args: Array<String>) {
    // These values are provided automatically by the Cloud Run Jobs runtime.
    val CLOUD_RUN_TASK_INDEX = System.getenv().getOrDefault("CLOUD_RUN_TASK_INDEX", "0")
    val CLOUD_RUN_TASK_ATTEMPT = System.getenv().getOrDefault("CLOUD_RUN_TASK_ATTEMPT", "0")
    val projectId = "unified-welder-349221"
    val datasetName = "jaffle_shop"
    val tableName = "customers"
    val destinationDataset = "dataform"
    //val success = BigQueryService.runQuery(projectId, datasetName, tableName)
    val success = BigQueryService.saveQueryToTable (projectId, datasetName, tableName, destinationDataset, tableName)
    if (success) {
        println("Query run successfully.")
    } else {
        System.err.println( "Task #$CLOUD_RUN_TASK_INDEX, Attempt #$CLOUD_RUN_TASK_ATTEMPT failed.")
    }
}


