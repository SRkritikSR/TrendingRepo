package com.example.trendingrepo.fake

import androidx.compose.material3.SnackbarDuration
import com.example.trendingrepos.model.Owner
import com.example.trendingrepos.model.Repos
import com.example.trendingrepos.model.TrendingRepos


object FakeDataSource {

    const val total1=5
    const val id1:Long= 0
    const val fullName1="abc def"
    const val description1="aaa"
    const val ownerId1=0
    const val ownerAvatarUrl1="abc.d"
    const val total2=10
    const val id2:Long= 1
    const val fullName2="uvw xyz"
    const val description2="uuu"
    const val ownerId2=1
    const val ownerAvatarUrl2="wxy.z"
    val trendingReposList=  TrendingRepos(
        total = total1,
        items = listOf(
            Repos(
            id = id1,
            fullName= fullName1,
            description= description1,
            owner = Owner(
                id = ownerId1,
                avatarUrl = ownerAvatarUrl1
            )
        ), Repos(
            id = id2,
            fullName= fullName2,
            description= description2,
            owner = Owner(
                id = ownerId2,
                avatarUrl = ownerAvatarUrl2
            )
        ))
    )

}