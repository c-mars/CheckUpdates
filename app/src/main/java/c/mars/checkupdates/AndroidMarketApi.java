package c.mars.checkupdates;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.model.Market;

import java.util.concurrent.Callable;

import timber.log.Timber;

/**
 * Created by Constantine Mars on 3/2/15.
 */
public class AndroidMarketApi {

    public static void test(String packageName, final Display display) {
        final MarketSession session = new MarketSession();

        session.login("c.mars.test@gmail.com", "testaccount");

        String query = "maps"; //packageName
        Market.AppsRequest appsRequest = Market.AppsRequest.newBuilder()
                .setQuery(query)
                .setStartIndex(0).setEntriesCount(10)
                .setWithExtendedInfo(true)
                .build();

        session.append(appsRequest, new MarketSession.Callback<Market.AppsResponse>() {
            @Override
            public void onResult(Market.ResponseContext context, Market.AppsResponse response) {
                // Your code here
                String text = "apps found: "+response.getAppCount();
                Timber.d(text);
                display.call(text);
                if (response.getAppCount()>0) {
                    text = response.getApp(0).getPackageName() + " ver:" + response.getApp(0).getVersion();
                    Timber.d(text);
                    display.call(text);
                }
            }
        });

        session.flush();

    }
}
