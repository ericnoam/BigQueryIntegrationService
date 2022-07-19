package com.gearsofleo.cloudrun

import com.google.cloud.bigquery.BigQueryException
import com.google.cloud.bigquery.BigQueryOptions
import com.google.cloud.bigquery.QueryJobConfiguration
import com.google.cloud.bigquery.TableId


object BigQueryService {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests.
    val bigquery = BigQueryOptions.getDefaultInstance().service

    fun saveQueryToTable(
        projectId: String,
        datasetName: String,
        tableName: String,
        destinationDataset: String,
        destinationTableId: String
    ): Boolean {
        try {
            val query = "SELECT * FROM $projectId.$datasetName.$tableName"

            // Identify the destination table
            val destinationTable = TableId.of(destinationDataset, destinationTableId)

            // Build the query job
            val queryConfig = QueryJobConfiguration.newBuilder(query).setDestinationTable(destinationTable).build()

            // Execute the query.
            kotlin.runCatching {  bigquery.query(queryConfig) }

            // The results are now saved in the destination table.
            return true
        } catch (e: BigQueryException) {
            println("Saved query did not run \n$e")
        } catch (e: InterruptedException) {
            println("Saved query did not run \n$e")
        }

        return false
    }

    fun runQuery(
        projectId: String,
        datasetName: String,
        tableName: String
    ): Boolean {
        try {
            //unified-welder-349221.jaffle_shop.customers
            val query = "SELECT * FROM $projectId.$datasetName.$tableName"
            // For more information on Job see:
            // https://googleapis.dev/java/google-cloud-clients/latest/index.html?com/google/cloud/bigquery/package-summary.html
            val results = bigquery.query(QueryJobConfiguration.of(query))
            results
                .iterateAll()
                .forEach { row -> row.forEach { `val` -> System.out.printf("%s\n", `val`.toString()) } }
        } catch (e: BigQueryException) {
            println("Table extraction job was interrupted. \n$e")
            return false
        } catch (e: InterruptedException) {
            println("Table extraction job was interrupted. \n$e")
            return false
        }

        return true
    }
}