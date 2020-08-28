package com.wimank.pbfs.domain.usecase

interface UseCase<R, M> {

    fun getRepository(): R

    fun getMapper(): M

}
