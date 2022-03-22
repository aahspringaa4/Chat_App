package di

import android.app.Application
import di.module.ChatMoudle
import di.module.ChattingListMoudle
import di.module.LoginMoudle
import di.module.MypageModule

class ChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ChatApplication)

            modules(
                listOf(
                    ChatMoudle,
                    ChattingListMoudle,
                    FriendListMoudle,
                    LoginMoudle,
                    MypageModule
                )
            )
        }
    }
}