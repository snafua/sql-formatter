package com.rem40.database.sqlformatter.features

import com.rem40.database.sqlformatter.SqlFormatter
import com.rem40.database.sqlformatter.expect
import org.spekframework.spek2.style.specification.Suite

fun Suite.supportsBetween(formatter: SqlFormatter.Formatter) {
  with(formatter) {
    it("formats BETWEEN _ AND _ on single line") {
      expect(format("foo BETWEEN bar AND baz")).toBe("foo BETWEEN bar AND baz")
    }
  }
}
