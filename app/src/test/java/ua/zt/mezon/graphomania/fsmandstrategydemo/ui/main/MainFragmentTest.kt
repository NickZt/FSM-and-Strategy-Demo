package ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainFragmentTest : TestCase() {
    @Spy
    private lateinit var contract: MockForTestMainFragmentViewStatesRenderContract

    @Test
    fun testRenderInitState() {
        // Setup

        // Act
        contract.render(MainFragmentUiStatesModel.IniState)

        // Assert
        verify(contract).showIni()
        verify(contract, never()).showLoadCounterPercentData(any())
        verify(contract, never()).showLoadError(any())
        verify(contract, never()).showListEmpty()
        verify(contract, never()).showListShow(any())
    }

    fun testRender() {}

    fun testShowInitState() {}

    fun testShowLoadProgress() {}

    fun testShowError() {}

    fun testShowEmptyState() {}

    fun testShowList() {}
}