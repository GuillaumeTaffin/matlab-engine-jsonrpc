import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.googlecode.jsonrpc4j.JsonRpcClient
import com.googlecode.jsonrpc4j.ProxyUtil
import com.gt.matlab.engine.rpc.MatlabEngineApi
import org.junit.jupiter.api.Test

class EvalTests {

    val server = "/Users/guillaumetaffin/projects/matlab-engine-jsonrpc/server/build/install/server/bin/server"
    val matlabroot = "/Applications/MATLAB_R2024a.app"

    @Test
    fun Eval() {

        val process =
            ProcessBuilder(server)
                .start()

        val mapper = jacksonObjectMapper()
        val client = JsonRpcClient(mapper)

        val api = ProxyUtil.createClientProxy(
            javaClass.classLoader,
            MatlabEngineApi::class.java,
            client,
            process.inputStream,
            process.outputStream
        )


        val eval = api.eval("ver")

        println(eval.output)


        println(api.eval("a = 2 + 3").output)


        process.waitFor()


    }
}