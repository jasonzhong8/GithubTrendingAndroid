package com.xapo.test.githubtrendingandroid.util;

import com.xapo.test.githubtrendingandroid.data.remote.RetrofitClient;
import com.xapo.test.githubtrendingandroid.data.remote.SOService;

/**
 * Created by Jason Zhong on 02/02/2018.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.github.com/search/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
