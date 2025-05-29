package com.rem40.database.sqlformatter

import com.rem40.database.sqlformatter.features.supportsOperators
import com.rem40.database.sqlformatter.languages.Dialect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object MySqlFormatterTest :
  Spek({
    val formatter = SqlFormatter.of(Dialect.MySql)

    describe("MySqlFormatter") {
      with(formatter) {
        behavesLikeMariaDbFormatter(formatter)

        describe("additional MySQL operators") { supportsOperators(formatter, listOf("->", "->>")) }
      }
    }
  })
