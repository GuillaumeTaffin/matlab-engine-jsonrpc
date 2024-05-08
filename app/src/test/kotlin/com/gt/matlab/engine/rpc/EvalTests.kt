package com.gt.matlab.engine.rpc

import com.mathworks.engine.MatlabEngine
import com.mathworks.matlab.types.Struct
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EvalTests {

    @Test
    fun `Eval ver in matlab`() {
        val matlabEngine = MatlabEngine.startMatlab()
        val engineName = System.getenv("MATLAB_ENGINE_NAME")
        val rest = matlabEngine.feval<Array<Struct>>("ver", *emptyArray())
        assertEquals("matlab", engineName)
    }
}