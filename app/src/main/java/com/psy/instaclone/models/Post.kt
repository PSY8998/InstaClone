package com.psy.instaclone.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class Post(
    val id: Long,
    val user: User,
    val imageUrl: String,
    val likesCount: Int,
    val commentsCount: Int,
    val postedAt: Long,
    val isLiked: Boolean,
    val isBookmarked: Boolean
)

data class User(
    val id: Long,
    val name: String,
    val imageUrl: String
)

object PostsProvider {

    val livePosts = MutableStateFlow(posts())

    fun likePost(id: Long) {
        livePosts.update {
            it.map { post ->
                if (id == post.id) {
                    post.copy(
                        isLiked = true,
                        likesCount = post.likesCount + 1
                    )
                } else
                    post
            }
        }
    }

    fun unlikePost(id: Long) {
        livePosts.update {
            it.map { post ->
                if (id == post.id) {
                    post.copy(
                        isLiked = false,
                        likesCount = post.likesCount - 1
                    )
                } else
                    post
            }
        }
    }

    fun bookmark(id: Long) {
        livePosts.update {
            it.map { post ->
                if (id == post.id) {
                    post.copy(isBookmarked = true)
                } else
                    post
            }
        }
    }

    fun unBookmark(id: Long) {
        livePosts.update {
            it.map { post ->
                if (id == post.id) {
                    post.copy(isBookmarked = false)
                } else
                    post
            }
        }
    }

    fun posts(): List<Post> {
        val users = users()
        return listOf<Post>(
            Post(
                id = 1,
                user = users[1],
                imageUrl = "https://source.unsplash.com/random/400x300?1",
                likesCount = 103,
                commentsCount = 22,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            ),
            Post(
                id = 2,
                user = users[2],
                imageUrl = "https://source.unsplash.com/random/400x300?2",
                likesCount = 22,
                commentsCount = 1,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            ),
            Post(
                id = 3,
                user = users[3],
                imageUrl = "https://source.unsplash.com/random/400x300?3",
                likesCount = 103,
                commentsCount = 22,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            ),
            Post(
                id = 4,
                user = users[4],
                imageUrl = "https://source.unsplash.com/random/400x300?4",
                likesCount = 103,
                commentsCount = 22,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            ),
            Post(
                id = 5,
                user = users[5],
                imageUrl = "https://source.unsplash.com/random/400x300?5",
                likesCount = 103,
                commentsCount = 22,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            ),
            Post(
                id = 6,
                user = users[6],
                imageUrl = "https://source.unsplash.com/random/400x300?6",
                likesCount = 103,
                commentsCount = 22,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            ),
            Post(
                id = 7,
                user = users[7],
                imageUrl = "https://source.unsplash.com/random/400x300?7",
                likesCount = 103,
                commentsCount = 22,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            ),
            Post(
                id = 8,
                user = users[7],
                imageUrl = "https://source.unsplash.com/random/400x300?8",
                likesCount = 103,
                commentsCount = 22,
                postedAt = System.currentTimeMillis(),
                isLiked = false,
                isBookmarked = false
            )
        )
    }

    fun users() = listOf<User>(
        User(
            id = 1,
            name = "John",
            imageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
        ),
        User(
            id = 2,
            name = "Harvey",
            imageUrl = "https://randomuser.me/api/portraits/men/2.jpg"
        ),
        User(
            id = 3,
            name = "Jacob",
            imageUrl = "https://randomuser.me/api/portraits/men/3.jpg"
        ),
        User(
            id = 4,
            name = "Sam",
            imageUrl = "https://randomuser.me/api/portraits/men/4.jpg"
        ),
        User(
            id = 5,
            name = "Tyson",
            imageUrl = "https://randomuser.me/api/portraits/men/5.jpg"
        ),
        User(
            id = 6,
            name = "Sara",
            imageUrl = "https://randomuser.me/api/portraits/men/6.jpg"
        ),
        User(
            id = 7,
            name = "Rahul",
            imageUrl = "https://randomuser.me/api/portraits/men/7.jpg"
        ),
        User(
            id = 8,
            name = "Shyaam",
            imageUrl = "https://randomuser.me/api/portraits/men/8.jpg"
        )

    )
}

