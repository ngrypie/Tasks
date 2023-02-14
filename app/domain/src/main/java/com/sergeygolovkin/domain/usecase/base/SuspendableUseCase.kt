package com.sergeygolovkin.domain.usecase.base

/**
 * Шаблон юзкейса для использования с корутинами.
 * Параметры запуска определяются объектом типа [PARAMS]
 */
internal interface SuspendableUseCase<PARAMS : Any, RESULT : Any> {

    /**
     * Выполнить операцию юзкейса с учетом переданных [params]
     */
    suspend operator fun invoke(params: PARAMS): RESULT
}