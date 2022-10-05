package com.feakin.intellij.parser.ide.formatter

import com.intellij.psi.formatter.FormatterTestCase

class FkFormatterTest : FormatterTestCase() {
    override fun getBasePath(): String {
        return "src/test/testData/formatter"
    }

    override fun getFileExtension(): String {
        return "fkl"
    }

    fun testBasicFormatter() {
        doTextTest("ContextMap TicketBooking { \n   \n}", "ContextMap TicketBooking {}")
        doTextTest(
            """Context Reservation{
Aggregate Reservation;
}""",
            """Context Reservation {
    Aggregate Reservation;
}"""
        )
    }
}