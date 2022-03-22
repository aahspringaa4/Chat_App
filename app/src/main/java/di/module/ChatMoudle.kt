package di.module

@Module
@InstallIn(SingletonComponent::class)
object ChatMoudle {

    @Binds
    fun bindChatDataSource(
        chatRepository: ChatRepository
    ): ChatRepository = ChatRepository()
}