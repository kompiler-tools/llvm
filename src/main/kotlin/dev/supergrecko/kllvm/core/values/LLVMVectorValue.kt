package dev.supergrecko.kllvm.core.values

import dev.supergrecko.kllvm.core.typedefs.LLVMValue
import org.bytedeco.llvm.LLVM.LLVMValueRef

public class LLVMVectorValue(llvmValue: LLVMValueRef) : LLVMValue(llvmValue)