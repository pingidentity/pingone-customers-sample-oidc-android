package com.pingone.loginapp.screens.splash

import com.pingone.loginapp.repository.auth.AuthRepository
import com.pingone.loginapp.screens.common.BaseViewModel
import com.pingone.loginapp.screens.common.LoginNavigation
import com.pingone.loginapp.util.oauth.Config
import com.pingone.loginapp.util.schedulers.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    schedulersProvider: SchedulersProvider,
    private val config: Config
) : BaseViewModel(schedulersProvider) {

    fun start() {
        compositeDisposable.add(
            fetchServerConfig().subscribe({ proceedNavigation() })
        )
    }

    private fun proceedNavigation() {
        compositeDisposable.add(
            authRepository.isUserAvailable().subscribe({
                navigation.postValue(if (it) LoginNavigation.Main else LoginNavigation.Login)
            }, {})
        )
    }

    private fun fetchServerConfig(): Completable {
        return config.readAuthConfig()
            .subscribeOn(schedulersProvider.backgroundScheduler)
            .observeOn(schedulersProvider.backgroundScheduler)
            .flatMapCompletable {
                val url = String.format(it.discovery_uri, it.environment_id)
                authRepository.readServerConfig(url)
            }
    }
}