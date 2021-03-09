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

    @Test
    fun testRenderLoadCounterPercentData() {
        // Act
        contract.render(MainFragmentUiStatesModel.LoadCounterPercentDataState(50))
        // Assert
        verify(contract, never()).showIni()
        verify(contract).showLoadCounterPercentData(50)
        verify(contract, never()).showLoadError(any())
        verify(contract, never()).showListEmpty()
        verify(contract, never()).showListShow(any())
    }

    @Test
    fun testRenderLoadError() {
        // Act
        contract.render(MainFragmentUiStatesModel.LoadErrorState("Error"))
        // Assert
        verify(contract, never()).showIni()
        verify(contract, never()).showLoadCounterPercentData(any())
        verify(contract).showLoadError("Error")
        verify(contract, never()).showListEmpty()
        verify(contract, never()).showListShow(any())
    }

    @Test
    fun testRenderListEmpty() {
        // Act
        contract.render(MainFragmentUiStatesModel.ListEmptyState)
        // Assert
        verify(contract, never()).showIni()
        verify(contract, never()).showLoadCounterPercentData(any())
        verify(contract, never()).showLoadError(any())
        verify(contract).showListEmpty()
        verify(contract, never()).showListShow(any())
    }

    @Test
    fun testRenderListShow() {
        // Act
        contract.render(MainFragmentUiStatesModel.ListShowState(ArrayList()))
        // Assert
        verify(contract, never()).showIni()
        verify(contract, never()).showLoadCounterPercentData(any())
        verify(contract, never()).showLoadError(any())
        verify(contract, never()).showListEmpty()
        verify(contract).showListShow(any())
    }
}