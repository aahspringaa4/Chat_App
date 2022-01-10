package di.module

@Module
@InstallIn(SingletonComponent::class)
object MypageModule {

    @Binds
    fun bindMypageDataSource(
        mypageRepository: MypageRepository
    ): MypageRepository = MypageRepository()
}