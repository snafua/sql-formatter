package com.rem40.database.sqlformatter.features

import com.rem40.database.sqlformatter.SqlFormatter
import com.rem40.database.sqlformatter.expect
import org.spekframework.spek2.style.specification.Suite

fun Suite.supportsAlterTable(formatter: SqlFormatter.Formatter) {
  with(formatter) {
    it("formats ALTER TABLE ... ALTER COLUMN query") {
      val result = format("ALTER TABLE supplier ALTER COLUMN supplier_name VARCHAR(100) NOT NULL;")
      expect(result)
        .toBe(
          """
      ALTER TABLE
        supplier
      ALTER COLUMN
        supplier_name VARCHAR(100) NOT NULL;
                """.trimIndent()
        )
    }
  }
}
