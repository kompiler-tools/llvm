package dev.supergrecko.kllvm.dsl

import dev.supergrecko.kllvm.contracts.Builder
import dev.supergrecko.kllvm.core.typedefs.LLVMType
import dev.supergrecko.kllvm.core.enumerations.LLVMTypeKind
import dev.supergrecko.kllvm.core.types.LLVMVectorType
import dev.supergrecko.kllvm.factories.TypeFactory

/**
 * Builder class to construct a vector type
 *
 * This is a DSL for building [LLVMTypeKind.Vector] types.
 */
public class VectorBuilder(public val size: Int) : Builder<LLVMVectorType> {
    public lateinit var type: LLVMType

    public override fun build(): LLVMVectorType {
        return TypeFactory.vector(type, size)
    }
}
