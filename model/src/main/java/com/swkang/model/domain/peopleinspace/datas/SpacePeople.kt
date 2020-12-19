package com.swkang.model.domain.peopleinspace.datas

data class SpacePeopleData(
    val message: String,
    val number: Int? = null,
    val people: List<SpacePeople>? = null
) {
    fun getPeoples(): List<SpacePeople> {
        return people ?: emptyList()
    }
}

data class SpacePeople(
    val craft: String,
    val name: String
)