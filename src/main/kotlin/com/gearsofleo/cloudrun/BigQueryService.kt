package com.gearsofleo.cloudrun

import com.google.cloud.bigquery.BigQueryException
import com.google.cloud.bigquery.BigQueryOptions
import com.google.cloud.bigquery.QueryJobConfiguration
import com.google.cloud.bigquery.TableId
import com.google.cloud.bigquery.TableResult


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
    ): Result<TableResult> {
        val query = "SELECT * FROM $projectId.$datasetName.$tableName"

        // Identify the destination table
        val destinationTable = TableId.of(destinationDataset, destinationTableId)

        // Build the query jobß
        val queryConfig = QueryJobConfiguration.newBuilder(query).setDestinationTable(destinationTable).build()

        // Execute the query.
        return kotlin.runCatching { bigquery.query(queryConfig) }
    }

    fun runQuery(
        projectId: String,
        datasetName: String,
        tableName: String
    ): Result<TableResult> {

            //unified-welder-349221.jaffle_shop.customers
            val query = "SELECT * FROM $projectId.$datasetName.$tableName"
            // For more information on Job see:
            // https://googleapis.dev/java/google-cloud-clients/latest/index.html?com/google/cloud/bigquery/package-summary.html
            return kotlin.runCatching { bigquery.query(QueryJobConfiguration.of(query)) }
    }
}