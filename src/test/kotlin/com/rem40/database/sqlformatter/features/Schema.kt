package com.rem40.database.sqlformatter.features

import com.rem40.database.sqlformatter.SqlFormatter
import com.rem40.database.sqlformatter.expect
import org.spekframework.spek2.style.specification.Suite

fun Suite.supportsSchema(formatter: SqlFormatter.Formatter) {
  with(formatter) {
    it("formats simple SET SCHEMA statements") {
      val result = format("SET SCHEMA schema1;")
      expect(result).toBe("""
      SET SCHEMA
        schema1;
                """.trimIndent())
    }
  }
}
