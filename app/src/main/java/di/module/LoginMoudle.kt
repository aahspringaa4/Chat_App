package di.module

@Module
@InstallIn(SingletonComponent::class)
object LoginMoudle {

    @Binds
    fun bindLoginDataSource(
        loginRepository: LoginRepository
    ): LoginRepository = LoginRepository()
}