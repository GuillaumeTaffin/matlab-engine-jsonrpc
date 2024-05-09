package com.gt.matlab.junit

import com.mathworks.engine.MatlabEngine

interface MatlabEngineTest {

    companion object {
        @JvmStatic
        val matlabEngine: MatlabEngine = MatlabEngine.startMatlab()
    }

}