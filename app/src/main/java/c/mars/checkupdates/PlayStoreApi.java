package c.mars.checkupdates;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import timber.log.Timber;

/**
 * Created by Constantine Mars on 3/2/15.
 */
public class PlayStoreApi {
    public static long test(String packageName, Display display) {

//        "http://api.playstoreapi.com/v1.1/apps/com.google.android.gm"
        long refreshDate = 0;
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.playstoreapi.com").build();
        Api api = restAdapter.create(Api.class);
        Application application = api.find(packageName);
        refreshDate = application.refreshDate;
        String log = application.packageID + ", date="+application.refreshDate+", version="+application.getSoftwareVersion()+", published="+application.getDatePublished();
        Timber.d(log);
        display.call(log);
        return refreshDate;
    }

    public interface Api {
        @GET("/v1.1/apps/{appPackage}")
        Application find(@Path("appPackage") String appPackage);
    }

    public class Application {
        String packageID;
        String appName;
        long refreshDate;

        List<AdditionalInfo> additionalInfo;

        public String getDatePublished() {
            for (AdditionalInfo info:additionalInfo) {
                if (info.datePublished != null)
                    return info.datePublished;
            }
            return null;
        }
//
        public String getSoftwareVersion() {
            for (AdditionalInfo info:additionalInfo) {
                if (info.softwareVersion != null)
                    return info.softwareVersion;
            }
            return null;
        }

        public class AdditionalInfo {
            String datePublished;
            String softwareVersion;
        }
    }
}
