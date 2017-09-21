package np.com.ngimasherpa.tech_companies_in_nepal.preview;

import android.support.annotation.NonNull;

/**
 * Created by ngima on 9/21/17.
 */

public class PreviewUtils {

    private TextCrawler textCrawler;
    private LinkPreviewCallback linkPreviewCallback;
    private String url;

    public PreviewUtils(@NonNull LinkPreviewCallback linkPreviewCallback, @NonNull String url) {
        this.textCrawler = new TextCrawler();
        this.linkPreviewCallback = linkPreviewCallback;
        this.url = url;
    }

    public void makePreview() {
        if (linkPreviewCallback == null || url == null || url.isEmpty()) return;
        textCrawler.makePreview(linkPreviewCallback, url);
    }

    public void onDestroy() {
        textCrawler.cancel();
    }
}
