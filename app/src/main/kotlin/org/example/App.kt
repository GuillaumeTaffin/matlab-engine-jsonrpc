package org.example

import com.mathworks.engine.MatlabEngine
import com.mathworks.matlab.types.Struct

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    val engine = MatlabEngine.startMatlab()

    val rest = engine.feval<Array<Struct>>("ver", *emptyArray())

    assert(true)
}
