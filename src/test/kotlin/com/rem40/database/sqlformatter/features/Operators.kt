package com.rem40.database.sqlformatter.features

import com.rem40.database.sqlformatter.SqlFormatter
import com.rem40.database.sqlformatter.expect
import org.spekframework.spek2.style.specification.Suite

fun Suite.supportsOperators(formatter: SqlFormatter.Formatter, operators: List<String>) {
  with(formatter) {
    operators.forEach { op ->
      it("supports $op operator") { expect(format("foo${op}bar")).toBe("foo $op bar") }
    }
  }
}
