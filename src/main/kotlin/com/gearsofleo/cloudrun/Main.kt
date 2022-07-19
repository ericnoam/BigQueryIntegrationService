package com.gearsofleo.cloudrun

fun main(args: Array<String>) {
    // These values are provided automatically by the Cloud Run Jobs runtime.
    val CLOUD_RUN_TASK_INDEX = System.getenv().getOrDefault("CLOUD_RUN_TASK_INDEX", "0")
    val CLOUD_RUN_TASK_ATTEMPT = System.getenv().getOrDefault("CLOUD_RUN_TASK_ATTEMPT", "0")
    val projectId = System.getenv("GOOGLE_CLOUD_PROJECT")
    val datasetName = "jaffle_shop"
    val tableName = "customers"
    val destinationDataset = "dataform"
    val result = BigQueryService.runQuery(projectId, datasetName, tableName)

    result.fold(
        { it.iterateAll()
            .forEach { row -> row.forEach { `val` -> System.out.printf("%s\n", `val`.toString()) } }
            it.
        },
        { System.err.println(it.localizedMessage) }
    )

    /*val result = BigQueryService.saveQueryToTable(projectId, datasetName, tableName, destinationDataset, tableName)
    result
        .fold(
            { println("Query run successfully for ${it.totalRows} rows") },
            {
                System.err.println("Task #$CLOUD_RUN_TASK_INDEX, Attempt #$CLOUD_RUN_TASK_ATTEMPT failed.")
                System.err.println(it.localizedMessage)
            }
        )*/
}


