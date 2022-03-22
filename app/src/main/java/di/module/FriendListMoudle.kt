package di.module

@Module
@InstallIn(SingletonComponent::class)
object FriendListRepository {

    @Binds
    fun bindFriendListDataSource(
        friendListRepository: FriendListRepository
    ): FriendListRepository = FriendListRepository()
}