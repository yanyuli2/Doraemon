package com.cofbro.qian.utils

import com.cofbro.hymvvmutils.base.BaseResponse
import com.cofbro.hymvvmutils.base.DataState
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.File
import java.io.IOException


object NetworkUtils {
    private val client = OkHttpClient()

    fun buildClientRequest(url: String): Request {
        val cookies = CacheUtils.cache["cookies"] ?: ""
        return Request.Builder().url(url)
            .addHeader("Accept-Language", "zh-Hans-CN;q=1, zh-Hant-CN;q=0.9")
            .addHeader("cookie", cookies)
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 14_2_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 com.ssreader.ChaoXingStudy/ChaoXingStudy_3_4.8_ios_phone_202012052220_56 (@Kalimdor)_12787186548451577248"
            )
            .build()
    }
    // JSESSIONID=51B25EAF937E0757E100B977F7FACBB7;lv=1;fid=1840;_uid=191970813;uf=b2d2c93beefa90dc93c9630a50f043aeda1b3f5cd81016ee88537765d90e85ee0c42a7f8a90a57eb9f01a45bdf49a560913b662843f1f4ad6d92e371d7fdf6444fff2d625c300bdece71fc6e59483dd394fca3980c580733ee02e425eae127e68d25a207f4287285;_d=1696861493434;UID=191970813;vc=FE33F1E68D32575DE45D383055F41CD9;vc2=D231DAB691B835CBA8EA4C28AA22C80D;vc3=f3AH%2FaTzO0OWmssrxWoMhsseq0wmWlKHlG6BqMC2topAdQtQmni5EKygJ7bbpcAVkrNbRxlKzGrHQFS4lNlUynFjpyH4wah0kKPItVVhsPQVeDkygUN7mptPgrI9lBBLm4Ol%2BhkL17YX5S9KEDLPhin41vdL8qmtsmYoZHzdNyk%3D45b63736ade334d1ab315f83162795b4;cx_p_token=389c80050755ad413b4b9fbfb1c07455;xxtenc=6c17eaa61e93f279ff6c0784a7f75d7a;DSSTASH_LOG=C_38-UN_97-US_191970813-T_1696861493435;route=52ffa9af7a380e114204ed76732d509c;
    // route=44030bc8a3c0af15b8ff79c9243587ed; source=""; spaceFid=1840; spaceRoleId=""; thirdRegist=0; __utma=68824131.692444529.1684077888.1684077888.1684077888.1; __utmc=68824131; __utmz=68824131.1684077888.1.1.utmcsr=passport2.chaoxing.com|utmccn=(referral)|utmcmd=referral|utmcct=/; lv=1; fid=1840; tl=1; _dd191970731=1696859714682; fanyamoocs=CF009EED2A0703198351C072BAF36DBE; _dd191970813=1696859718210; JSESSIONID=782AC9263AF950CC9206731BB9AE74D7; route_mobilelearn=ff6ad8bf549bb7fabcec3f63a89e27e4; _uid=191970731; uf=b2d2c93beefa90dc93c9630a50f043aeda1b3f5cd81016ee4953b6cc0fb961400c42a7f8a90a57ebec4865a2458c6765913b662843f1f4ad6d92e371d7fdf6444fff2d625c300bdece71fc6e59483dd394fca3980c58073334c16dcee04a739d40cc32c0f7c81d22; _d=1696859975779; UID=191970731; vc=A36D3FA84461C0CAF4688A251F03960D; vc2=464A347B391020FA611A3EDB588C71A1; vc3=cVlD6V2OXwxsPLfG%2Fh83Pp8%2FleLwQwSkYnay0mmdy%2BrPn9Aa%2F9XhCKgMmWk6JZdaYmfzQDOsfHXoEqlTMaSYhHOmPRj8jPB2WeTp75Wd1VUeYbmfyb1TKftVS2W%2F50FFAQSaQ3eJcCPcr89o4uGw%2FXAilX6aeQGrLVxO6ck57fU%3Df79d6f53e19dd2e175ec704a14d872c5; cx_p_token=fc00f25cfb9a88f654a01abc68a64590; xxtenc=4f52dce523c9cd8492b6a11267f9eade; DSSTASH_LOG=C_38-UN_97-US_191970731-T_1696859975780; route_widget=ca138d512584d6764e762fa0549299ef
    fun buildServerRequest(url: String): Request {
        val cookies = CacheUtils.cache["cookies"] ?: ""
        return Request.Builder().url(url)
            .addHeader("Accept-Language", "zh-Hans-CN;q=1, zh-Hant-CN;q=0.9")
            .addHeader("Cookie", "source=\"\"; spaceFid=1840; spaceRoleId=\"\"; k8s=904e21e4291765cccbe96edcca0fafd672d9c8a8; route=2fe558bdb0a1aea656e6ca70ad0cad20; lv=1; fid=1840; _uid=191970813; UID=191970813; vc=FE33F1E68D32575DE45D383055F41CD9; vc2=74A6E443CF1DD58EA5028500409E081A; xxtenc=6c17eaa61e93f279ff6c0784a7f75d7a; uf=b2d2c93beefa90dc93c9630a50f043aeda1b3f5cd81016ee88537765d90e85eed80373d8c33a22b8e8295aa74966e5c2913b662843f1f4ad6d92e371d7fdf6444fff2d625c300bdece71fc6e59483dd394fca3980c580733cbd6d59a598485af619d55f25c3dc3b8; _d=1697465883951; vc3=b8QOBvCvXmT0%2F%2BQTcHs8W2h3vYbGBtBDwYNCBCsg3YOAwQTvqrSoYg%2Fq5w4MQUJCsnudFqSE2RLj3mATAxndRNwskv8pbWEJeMGaezzK9K3hqx8XGc0AgV1K88H6kXZmNllYt2591uTYCl7shBh%2FHXBoSlO4TKqo4Z4v9I7GCBU%3Da80205f6d60d6ff12d235de8c33474a3; cx_p_token=8d7658ade3a2fa33491661346593d00e; p_auth_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTE5NzA4MTMiLCJsb2dpblRpbWUiOjE2OTc0NjU4ODM5NTMsImV4cCI6MTY5ODA3MDY4M30.DnYMx7xzNlqQYdR4PYOaAwZg2YMEl1hC_bPkBOJNYT8; DSSTASH_LOG=C_38-UN_97-US_191970813-T_1697465883953; fanyamoocs=CF009EED2A0703198351C072BAF36DBE; thirdRegist=0; jrose=823C7B9179E294E2029410810C89063E.mooc-1132841633-zs9cn; _dd191970813=1697551298299")
            .addHeader("Referer", "https://mooc1-2.chaoxing.com/visit/interaction?s=a5913ee5215774a303a05e9c9358f603")
            .addHeader("Sec-Ch-Ua-Mobile", "?0")
            .addHeader("Sec-Ch-Ua-", "Windows")
            .addHeader("Sec-Fetch-Site", "same-origin")
            .addHeader("navigate", "Sec-Fetch-Mode")
            .addHeader("Sec-Fetch-User", "?1")
            .addHeader("Sec-Ch-Ua", "Google Chrome\";v=\"117\", \"Not;A=Brand\";v=\"8\", \"Chromium\";v=\"117")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")

            //.addHeader("Cookie", "uf=b2d2c93beefa90dc93c9630a50f043aeda1b3f5cd81016ee4953b6cc0fb961400c42a7f8a90a57ebec4865a2458c6765913b662843f1f4ad6d92e371d7fdf6444fff2d625c300bdece71fc6e59483dd394fca398UID=191970731; vc=A36D3FA84461C0CAF4688A251F03960D; vc2=464A347B391020FA611A3EDB588C71A1; vc3=cVlD6V2OXwxsPLfG%2Fh83Pp8%2FleLwQwSkYnay0mmdy%2BrPn9Aa%2F9XhCKgMmWk6JZdaYmfzQDOsfHXoEqlTMaSYhHOmPRj8jPB2WeTp75Wd1VUeYbmfyb1TKftVS2W%2F50FFAQSaQ3eJcCPcr89o4uGw%2FXAilX6aeQGrLVxO6ck57fU%3Df79d6f53e19dd2e175ec704a14d872c5;")
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.2045.60"
            )
            .build()
    }

    fun request(clientRequest: Request): BaseResponse<Response> {
        val call = client.newCall(clientRequest)
        val response = BaseResponse<Response>()
        response.dataState = DataState.STATE_INITIALIZE
        response.data = call.execute()
        return response
    }

    fun request(url: String): BaseResponse<Response> {
        val request = Request.Builder().url(url).build()
        val call = client.newCall(request)
        val response = BaseResponse<Response>()
        response.dataState = DataState.STATE_INITIALIZE
        response.data = call.execute()
        return response
    }

    fun post(url: String, file: File): BaseResponse<Response> {
        val cookies = CacheUtils.cache["cookies"] ?: ""
        val uid = CacheUtils.cache["uid"] ?: ""
        val body: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", file.name,
                RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            )
            .addFormDataPart("puid", uid)
            .build()
        val request = Request.Builder().url(url)
            .addHeader("Accept-Language", "zh-Hans-CN;q=1, zh-Hant-CN;q=0.9")
            .addHeader("cookie", cookies)
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 14_2_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 com.ssreader.ChaoXingStudy/ChaoXingStudy_3_4.8_ios_phone_202012052220_56 (@Kalimdor)_12787186548451577248"
            )
            .post(body).build()
        val call = client.newCall(request)
        val response = BaseResponse<Response>()
        response.dataState = DataState.STATE_INITIALIZE
        response.data = call.execute()
        return response
    }

    fun requestAsync(url: String, onSuccess: (Response) -> Unit = {}, onFailure: () -> Unit = {}) {
        val request = Request.Builder().url(url).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure()
            }

            override fun onResponse(call: Call, response: Response) {
                onSuccess(response)
            }
        })
    }

}