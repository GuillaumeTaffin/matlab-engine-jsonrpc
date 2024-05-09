package com.gt.matlab.engine.rpc

import com.googlecode.jsonrpc4j.JsonRpcServer
import com.mathworks.engine.MatlabEngine
import java.io.StringWriter

interface MatlabEngineApi {

    fun eval(command: String): StandardOuts

}

class MatlabEngineApiImpl(private val engine: MatlabEngine) : MatlabEngineApi {

    override fun eval(command: String): StandardOuts {
        val outputWriter = StringWriter()
        val errorWriter = StringWriter()
        engine.eval(command, outputWriter, errorWriter)
        return StandardOuts(outputWriter.toString(), errorWriter.toString())
    }

}

fun matlabEngineJsonRpcServer(engine: MatlabEngine) =
    JsonRpcServer(MatlabEngineApiImpl(engine), MatlabEngineApi::class.java)

data class StandardOuts(
    val output: String?,
    val error: String?
)