package com.gt.matlab.engine.rpc

import com.mathworks.engine.MatlabEngine

fun main() {

    val engine = MatlabEngine.connectMatlab("toto")
    val server = matlabEngineJsonRpcServer(engine)

    val input = System.`in`

    while (true) {
        if (input.available() != 0) {
            server.handleRequest(input, System.out)
        }
    }


}