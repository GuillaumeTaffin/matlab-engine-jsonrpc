package com.gt.matlab.engine.rpc

import com.googlecode.jsonrpc4j.JsonRpcClient
import com.googlecode.jsonrpc4j.JsonRpcServer
import com.gt.matlab.junit.MatlabEngineTest
import com.gt.matlab.junit.MatlabEngineTest.Companion.matlabEngine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

class EvalTests : MatlabEngineTest {

    @Test
    fun `Eval ver in matlab`() {
        val server: JsonRpcServer = matlabEngineJsonRpcServer(matlabEngine)

        val client = JsonRpcClient()
        val outputStream = ByteArrayOutputStream()
        client.invoke("eval", arrayOf<Any>("ver"), outputStream)

        val requestStream: InputStream = ByteArrayInputStream(outputStream.toByteArray())
        val responseStream = ByteArrayOutputStream()
        server.handleRequest(requestStream, responseStream)



        assertEquals("", responseStream.toString())
    }

}