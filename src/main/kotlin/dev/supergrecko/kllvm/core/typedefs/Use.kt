package dev.supergrecko.kllvm.core.typedefs

import org.bytedeco.llvm.LLVM.LLVMUseRef

public class Use internal constructor() {
    internal lateinit var ref: LLVMUseRef

    internal constructor(use: LLVMUseRef) : this() {
        ref = use
    }
}
