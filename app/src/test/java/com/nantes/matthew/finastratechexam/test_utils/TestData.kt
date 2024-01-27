package com.nantes.matthew.finastratechexam.test_utils

import com.nantes.matthew.finastratechexam.photos.domain.model.Author
import com.nantes.matthew.finastratechexam.photos.domain.model.Photo
import java.util.Date

object TestData {

    val photos = listOf(
        Photo(
            id = "TestID",
            url = "TestURL",
            likes = 25,
            author = Author(
                id = "TestAuthorID",
                username = "TestUserName",
                name = "TestName",
                profileImage = "TestProfileImage"
            ),
            dateCreated = Date(),
            page = 1
        ),
        Photo(
            id = "TestID123",
            url = "TestURL",
            likes = 3,
            author = Author(
                id = "TestAuthorID",
                username = "TestUserName",
                name = "TestName",
                profileImage = "TestProfileImage"
            ),
            dateCreated = Date(),
            page = 1
        ),
        Photo(
            id = "TestID321",
            url = "TestURL",
            likes = 2,
            author = Author(
                id = "TestAuthorID",
                username = "TestUserName",
                name = "TestName",
                profileImage = "TestProfileImage"
            ),
            dateCreated = Date(),
            page = 1
        ),
        Photo(
            id = "TestID555",
            url = "TestURL",
            likes = 50,
            author = Author(
                id = "TestAuthorID",
                username = "TestUserName",
                name = "TestName",
                profileImage = "TestProfileImage"
            ),
            dateCreated = Date(),
            page = 2
        )
        )



}