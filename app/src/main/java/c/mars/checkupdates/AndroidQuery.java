package c.mars.checkupdates;

import com.gc.android.market.api.model.Market;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import timber.log.Timber;

/**
 * Created by Constantine Mars on 3/2/15.
 */
public class AndroidQuery {
    public static void test(String packageName, Display display) {
//        https://androidquery.appspot.com/api/market?app=com.aide.ui

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://androidquery.appspot.com").build();
        Api api = restAdapter.create(Api.class);
        Application application = api.findApp(packageName);
        String log = application.name+", app="+application.app+", update="+application.update+", ver="+application.version;
        Timber.d(log);
        display.call(log);
    }

    public interface Api {
        @GET("/api/market")
        public Application findApp(@Query("app") String appPackage);
    }

    public class Application {
        String app;
        String name;
        long update;
        String published;
        String version;
    }
}
