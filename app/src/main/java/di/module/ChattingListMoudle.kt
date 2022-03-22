package di.module

@Module
@InstallIn(SingletonComponent::class)
object ChattingListMoudle {

    @Binds
    fun bindChattingListDataSource(
        chattingListRepository: ChattingListRepository
    ): ChattingListRepository = CHattingListRepository()
}