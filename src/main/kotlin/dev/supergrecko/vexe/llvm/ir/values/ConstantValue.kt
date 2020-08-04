package dev.supergrecko.vexe.llvm.ir.values

import dev.supergrecko.vexe.llvm.internal.util.fromLLVMBool
import dev.supergrecko.vexe.llvm.ir.Opcode
import dev.supergrecko.vexe.llvm.ir.Type
import dev.supergrecko.vexe.llvm.ir.Value
import org.bytedeco.llvm.LLVM.LLVMValueRef
import org.bytedeco.llvm.global.LLVM

public open class ConstantValue internal constructor() : Value() {
    public constructor(llvmRef: LLVMValueRef) : this() {
        ref = llvmRef
    }

    //region Core::Values::Constants::ConstantExpressions
    /**
     * Get the opcode for a constant value
     *
     * @see LLVM.LLVMGetConstOpcode
     */
    public fun getOpcode(): Opcode? {
        val isConst = LLVM.LLVMIsConstant(ref).fromLLVMBool()

        require(isConst) {
            "Value must be constant to retrieve opcode"
        }

        val int: Int? = LLVM.LLVMGetConstOpcode(ref)

        return Opcode.values().firstOrNull { it.value == int }
    }

    /**
     * Perform a cast without changing any bits
     *
     * This requires both this and the destination type to be non-aggregate,
     * first-class types.
     *
     * TODO: Determine that this is not an aggregate type
     *
     * @see LLVM.LLVMConstBitCast
     */
    public fun getBitCast(type: Type): Value {
        val ref = LLVM.LLVMConstBitCast(ref, type.ref)

        return Value(ref)
    }

    /**
     * Attempt to convert using extension, default to bitcast
     *
     * This is an LLVM-C/C++ specific API. It is not a part of the
     * instruction set.
     *
     * @see LLVM.LLVMConstSExtOrBitCast
     * @see LLVM.LLVMConstZExtOrBitCast
     */
    public fun getExtOrBitCast(type: Type, signExtend: Boolean): Value {
        val ref = if (signExtend) {
            LLVM.LLVMConstSExtOrBitCast(ref, type.ref)
        } else {
            LLVM.LLVMConstZExtOrBitCast(ref, type.ref)
        }

        return Value(ref)
    }

    /**
     * Attempt to convert using zero extension, default to bitcast
     *
     * @see LLVM.LLVMConstZExtOrBitCast
     */
    public fun getZExtOrBitCast(type: Type): Value {
        return getExtOrBitCast(type, false)
    }

    /**
     * Attempt to convert using sign extension, default to bitcast
     *
     * @see LLVM.LLVMConstSExtOrBitCast
     */
    public fun getSExtOrBitCast(type: Type): Value {
        return getExtOrBitCast(type, true)
    }

    /**
     * Attempt to truncate, default to bitcast
     *
     * @see LLVM.LLVMConstTruncOrBitCast
     */
    public fun getTruncOrBitCast(type: Type): Value {
        val ref = LLVM.LLVMConstTruncOrBitCast(ref, type.ref)

        return Value(ref)
    }
    //endregion Core::Values::Constants::ConstantExpressions
}
