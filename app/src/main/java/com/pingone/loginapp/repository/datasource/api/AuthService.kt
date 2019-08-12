package com.pingone.loginapp.repository.datasource.api

import com.pingone.loginapp.data.AccessToken
import com.pingone.loginapp.data.JWKS
import com.pingone.loginapp.data.ServerConfig
import com.pingone.loginapp.data.UserInfo
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.*

interface AuthService {

    @Headers("Content-Type: application/json")
    @GET
    fun getOauthConfig(@Url url: String): Flowable<ServerConfig>

    @Headers("Content-Type: application/json")
    @GET
    fun getInfo(@Url urlToGo: String): Flowable<ResponseBody>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST
    fun obtainAccessTokenPost(
        @Url url: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("redirect_uri") redirectUri: String
    ): Flowable<AccessToken>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST
    fun obtainAccessTokenNone(
        @Url url: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String,
        @Query("client_id") clientId: String,
        @Query("redirect_uri") redirectUri: String
    ): Flowable<AccessToken>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET
    fun obtainAccessTokenBasic(
        @Url url: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("redirect_uri") redirectUri: String
    ): Flowable<AccessToken>

    @Headers("Content-Type: application/json")
    @GET
    fun getUserInfo(
        @Url url: String,
        @Header("Authorization") bearerToken: String
    ): Flowable<UserInfo>

    @Headers("Content-Type: application/json")
    @GET
    fun getJWKS(@Url url: String): Flowable<JWKS>
}
