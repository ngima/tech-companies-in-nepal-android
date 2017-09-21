package np.com.ngimasherpa.tech_companies_in_nepal.preview;

import android.content.res.Resources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ngima on 9/21/17.
 */

public class GetFavicon {

    public static Observable<String> getFavicon(final String url) {
        return Observable.fromCallable(new Callable<Document>() {
            @Override
            public Document call() throws Exception {
                return Jsoup.connect(url).execute().parse();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Document, String>() {
                    @Override
                    public String apply(Document document) throws Exception {
                        return document.select("link[rel$=icon]").get(0)
                                .attr("href");
                    }
                });
    }
}
