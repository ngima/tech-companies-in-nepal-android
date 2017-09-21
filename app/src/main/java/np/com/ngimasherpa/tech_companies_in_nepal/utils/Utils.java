package np.com.ngimasherpa.tech_companies_in_nepal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.AnyRes;
import android.support.customtabs.CustomTabsIntent;
import android.telephony.TelephonyManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ngima on 6/5/17.
 */

public class Utils {


    public static boolean isThereInternetConnection(Context context) {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    public static void loadWebUrlIn(Context context, String url) {
        CustomTabsIntent intent = new CustomTabsIntent.Builder().addDefaultShareMenuItem().build();
        intent.intent.setPackage("com.android.chrome");
        intent.launchUrl(context, Uri.parse(url));
    }

    public static ArrayList<String> getUniqueList(String str1) {
        String[] words = str1.split(" ");
        Set<String> uniqueWords = new HashSet<String>();

        for (String word : words) {
            uniqueWords.add(word.trim());
        }

        return new ArrayList<>(uniqueWords);
    }
}
