package com.rem40.database.sqlformatter.features

import com.rem40.database.sqlformatter.SqlFormatter
import com.rem40.database.sqlformatter.expect
import org.spekframework.spek2.style.specification.Suite

fun Suite.supportsAlterTableModify(formatter: SqlFormatter.Formatter) {
  with(formatter) {
    it("formats ALTER TABLE ... MODIFY statement") {
      val result = format("ALTER TABLE supplier MODIFY supplier_name char(100) NOT NULL;")
      expect(result)
        .toBe(
          """
      ALTER TABLE
        supplier
      MODIFY
        supplier_name char(100) NOT NULL;
                """.trimIndent()
        )
    }
  }
}
