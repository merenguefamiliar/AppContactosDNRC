package com.example.proyecto.data.remote.model

import com.example.proyecto.data.local.ContactEntity

data class ApiResponse(
    val results: List<Results> = emptyList()
)

data class Results(
    val name: ContactName,
    val picture: ContactPicture,
    val location: ContactLocation,
    val phone: String,
    val email: String,
    val cell: String
) {
    fun toEntity(): ContactEntity {
        return ContactEntity(
            name = "${name.first} ${name.last}",
            phone = phone,
            email = email,
            imageUrl = picture.thumbnail
        )
    }
}

data class ContactName(
    val title: String, val first: String, val last: String
)

data class ContactPicture(
    val thumbnail: String
)

data class ContactLocation(
    val city: String, val state: String
)
