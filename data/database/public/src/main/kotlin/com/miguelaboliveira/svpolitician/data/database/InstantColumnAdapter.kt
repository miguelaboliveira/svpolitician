package com.miguelaboliveira.svpolitician.data.database

import com.squareup.sqldelight.ColumnAdapter
import java.time.Instant

public object InstantColumnAdapter : ColumnAdapter<Instant, Long> {
    override fun decode(databaseValue: Long): Instant = Instant.ofEpochSecond(databaseValue)
    override fun encode(value: Instant): Long = value.epochSecond
}
