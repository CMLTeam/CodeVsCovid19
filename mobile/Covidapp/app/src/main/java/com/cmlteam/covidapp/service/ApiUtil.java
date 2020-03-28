package com.cmlteam.covidapp.service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ApiUtil {

  private static ApiUtil instance;

  private InAppNotificationUtil inAppNotificationUtil;
  private boolean loading = false;
  private ArrayList<Call> calls = new ArrayList<>();

  public static ApiUtil getInstance() {
    if (instance == null) {
      instance = new ApiUtil();
    }
    return instance;
  }

  public void cancelCalls() {
    for (Call call : calls) {
      call.cancel();
    }

    calls.clear();
  }

  public void redeemVoucher(final String contentId, final String redeemCode,
                               final ApiCallback.ObjectCallback<Boolean, Error> callback) {
    getEnduserApi().redeemVoucher(contentId, "roman@test5", redeemCode, new APICallback<Boolean, List<Error>>() {
      @Override
      public void finished(Boolean status) {
        callback.finish(status, null);
      }

      @Override
      public void error(List<Error> errors) {
        System.out.println("VOUCHER REDEMPTION ERROR" + errors.get(0).getDetail());
        callback.finish(null, errors.get(0));
      }
    });
  }

  private void handleError(List<Error> errors) {
    for (Error error : errors) {

//      if (error.getCode().equals("92") && error.getStatus().equals("404")) {
//        if (inAppNotificationUtil != null) {
//          inAppNotificationUtil.showSnackbar(InAppNotificationUtil.ERROR_PASSWORD);
//          return;
//        }
//        return;
//      }
//
//      if (error.getCode().equalsIgnoreCase("10000")) {
//        if (inAppNotificationUtil != null) {
//          inAppNotificationUtil.showSnackbar(InAppNotificationUtil.ERROR_NO_INTERNET);
//          return;
//        }
//      }
//
//      if (error.getStatus() != null) {
//        if (error.getStatus().startsWith("4")) {
//          if (inAppNotificationUtil != null) {
//            inAppNotificationUtil.showSnackbar(InAppNotificationUtil.ERROR_CLIENT);
//            return;
//          }
//        }
//
//        if (error.getStatus().startsWith("5")) {
//          if (inAppNotificationUtil != null) {
//            inAppNotificationUtil.showSnackbar(InAppNotificationUtil.ERROR_SERVER);
//            return;
//          }
//        }
//      }
    }
  }

  public void setInAppNotificationUtil(InAppNotificationUtil inAppNotificationUtil) {
    this.inAppNotificationUtil = inAppNotificationUtil;
  }

  public boolean isLoading() {
    return loading;
  }


  private class HttpRequestTask {

  HttpRequestTask(
  private val latitude: Double, private val longitude: Double, private val duration: Int = 20
          ) : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void?): Boolean {
      try {
        val httpService = HttpService.create()
        response = httpService.getRoutes(longitude, latitude, duration).execute().body()

      } catch (e: InterruptedException) {
        System.err.println("Error: $e ")
        return false
      }
      return true /*companion object {
        fun create(accessToken:String = ""): HttpService {

            val client = OkHttpClient.Builder()
                .addInterceptor { chain -> chain.run {
                    proceed(request()
                        .newBuilder()
                        .addHeader("Authorization",  accessToken)
                        .build())
                }
                }.build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://3.vbb.transport.rest")
                .client(client)
                .build()

            return retrofit.create(HttpService::class.java)
        }
    }*/
    }

    override fun onPostExecute(success: Boolean) {
      httpRequestTask = null
      //showLoading(false)

      if (success) {
        showCirclesData()
        addClusteredGeoJsonSource()
//                drawTrips()
      } else {
        //TODO
      }
    }

    override fun onCancelled() {
      httpRequestTask = null
      //showLoading(false)
    }
  }


}
