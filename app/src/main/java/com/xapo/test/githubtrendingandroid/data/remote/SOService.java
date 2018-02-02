package com.xapo.test.githubtrendingandroid.data.remote;

import com.xapo.test.githubtrendingandroid.data.model.TrendingRepositoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by Jason Zhong on 02/02/2018.
 */

public interface SOService {
    @GET("repositories?q=trending+android&sort=stars&order=desc")
    Call<TrendingRepositoryResponse> getTrendingRepositories();
}
