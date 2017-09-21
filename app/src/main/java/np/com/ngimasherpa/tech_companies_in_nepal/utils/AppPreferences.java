package np.com.ngimasherpa.tech_companies_in_nepal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import np.com.ngimasherpa.tech_companies_in_nepal.model.Company;

import static np.com.ngimasherpa.tech_companies_in_nepal.utils.AppPreferences.Keys.COMPANIES;

/**
 * Created by ngima on 6/8/17.
 */

public class AppPreferences {


    private static final String SYNCED = "syned";
    private static final String TAG = "syned";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public AppPreferences(Context pContext) {
        preferences = pContext.getSharedPreferences(TAG, Context
                .MODE_PRIVATE);
        editor = preferences.edit();
        context = pContext;
    }

    public void saveCompanies(@Nullable List<Company> loginResponse) {

        editor.putString(COMPANIES, new Gson().toJson(loginResponse));
        editor.commit();
    }

    @Nullable
    public List<Company> retrieveCompanies() {
        String loginResponse = preferences.getString(COMPANIES, "[]");
        return DataUtils.getObjectFromJson(loginResponse, new TypeToken<List<Company>>() {
        }.getType());
    }

    class Keys {
        public static final String COMPANIES = "companies";
    }
}
