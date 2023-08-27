package com.skeleton.app.core.domain.usecase


interface IUseCase<INPUT, OUTPUT> {
    suspend operator fun invoke(input: INPUT): Result<OUTPUT>
}

interface INoneInputUseCase<OUTPUT> {
    suspend operator fun invoke(): Result<OUTPUT>
}

